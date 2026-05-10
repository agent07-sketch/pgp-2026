# Building a Full-Stack IoT Platform for Smart Water Softeners: From Device Telemetry to Consumer App

*A comprehensive technical deep-dive into designing, building, and debugging a production IoT platform — covering real-time data pipelines, device management, cloud-to-device commands, consumption analytics, and the hard lessons learned from 30 production bugs.*

---

## Table of Contents

1. [Introduction](#introduction)
2. [The Problem We're Solving](#the-problem)
3. [System Architecture](#architecture)
4. [The Data Pipeline — Ingestion to Aggregation](#data-pipeline)
5. [The Device Service — Backend API Layer](#device-service)
6. [Device Registration and Provisioning](#registration)
7. [Cloud-to-Device Command Center](#command-center)
8. [Real-Time Device Monitoring](#monitoring)
9. [Water Consumption Analytics](#analytics)
10. [Over-the-Air Firmware Updates](#ota)
11. [Production Debugging — 30 Bugs in 2 Weeks](#debugging)
12. [Architecture Decisions and Trade-offs](#decisions)
13. [Key Metrics](#metrics)
14. [Lessons Learned](#lessons)

---

<a name="introduction"></a>
## 1. Introduction

IoT systems are deceptively complex. Connecting a device to the cloud is the easy part. The hard part is building a system that handles messy real-world data, survives device firmware quirks, scales to thousands of devices, and serves a consumer mobile app with sub-second response times — all while letting you push firmware updates without bricking hardware in the field.

This post is a consolidated, end-to-end walkthrough of a production IoT platform for smart water softeners. It covers every layer of the stack: the embedded device telemetry format, the real-time streaming pipeline, the backend API service, the consumption analytics engine, the over-the-air update system, and — critically — what broke when we shipped it to QA.

I've structured this as a single reference document. If you're building an IoT platform, you can use it as an architectural blueprint. If you're debugging one, jump to the [production debugging](#debugging) section.

---

<a name="the-problem"></a>
## 2. The Problem We're Solving

Imagine you've deployed thousands of smart water softeners across India. Each device has an embedded MCU paired with an LTE cellular modem. It measures water consumption, tracks regeneration cycles, monitors salt levels, and detects faults — all running on battery-backed firmware.

Your customers expect a mobile app that:

- Shows real-time device status (is my softener working? how much capacity is left?)
- Displays water consumption history (daily, weekly, monthly trends)
- Lets them control the device remotely (start regeneration, schedule vacation mode, change settings)
- Alerts them when something needs attention (low salt, errors, regeneration due)
- Updates device firmware seamlessly over-the-air

Behind the scenes, you need to:

- **Provision** each device securely with unique X.509 certificates at the factory
- **Ingest** telemetry from devices transmitting over LTE via Azure IoT Hub
- **Process** raw status packets in real-time, extracting 45+ fields from nested JSON
- **Store** data in a dual-layer architecture — Redis for real-time, PostgreSQL for history
- **Aggregate** daily consumption metrics from cumulative device counters
- **Serve** API responses to the mobile app in under 100ms
- **Push** commands back to devices through the cloud
- **Roll out** firmware updates safely using staged deployment strategies

This is the system we built.

---

<a name="architecture"></a>
## 3. System Architecture

### High-Level Data Flow

```
┌──────────────┐      LTE/MQTT      ┌──────────────────┐
│   IoT Device │ ──────────────────► │   Azure IoT Hub  │
│  (MCU + LTE) │ ◄────────────────── │   (Event Hub)    │
│              │       C2D           └────────┬─────────┘
└──────────────┘                              │
                                              │ Telemetry Stream
                                              ▼
                              ┌────────────────────────────┐
                              │  Databricks Spark Streaming │
                              │  (Structured Streaming,     │
                              │   20s micro-batches)        │
                              └──────┬──────┬──────┬───────┘
                                     │      │      │
                          ┌──────────┘      │      └──────────┐
                          ▼                 ▼                  ▼
                   ┌────────────┐   ┌────────────┐   ┌──────────────┐
                   │ Delta Lake │   │   Redis    │   │ Service Bus  │
                   │ (Raw Store)│   │  (Latest   │   │ (Error Queue)│
                   │            │   │   Status)  │   │              │
                   └──────┬─────┘   └─────┬──────┘   └──────────────┘
                          │               │
                          ▼               │
              ┌─────────────────────┐     │
              │ Databricks Scheduled│     │
              │ Job (1:30 AM IST)   │     │
              │ Daily Aggregation   │     │
              └──────────┬──────────┘     │
                         │                │
                         ▼                │
                  ┌────────────┐          │
                  │ PostgreSQL │          │
                  │ (Aggregate │          │
                  │  + History)│          │
                  └──────┬─────┘          │
                         │                │
                         ▼                ▼
              ┌──────────────────────────────────────┐
              │      Device Service (Spring Boot)     │
              │                                       │
              │  ┌─────────┐  ┌──────────┐  ┌──────┐│
              │  │Monitoring│  │ Command  │  │ OTA  ││
              │  │& Status  │  │ Center   │  │Engine││
              │  └─────────┘  └──────────┘  └──────┘│
              └──────────────────┬────────────────────┘
                                 │
                                 ▼
                     ┌───────────────────────┐
                     │     Mobile App        │
                     │   (Android / iOS)     │
                     └───────────────────────┘
```

### Tech Stack

| Layer | Technology |
|-------|-----------|
| Device firmware | Custom MCU + LTE modem (Cat-M1) |
| Cloud messaging | Azure IoT Hub (MQTT/AMQP) |
| Stream processing | Databricks, PySpark Structured Streaming |
| Raw data storage | Azure Data Lake Gen2, Delta Lake |
| Real-time cache | Azure Redis Cache (SSL, connection-pooled) |
| Database | Azure PostgreSQL (Citus) with Flyway migrations |
| Backend API | Java 17, Spring Boot 3.0 |
| Security | BouncyCastle (X.509 certificates), vendor auth headers |
| Infrastructure | Terraform, Helm, Kubernetes |
| CI/CD | Bitbucket Pipelines |
| Inter-service comms | Retrofit2 REST clients |

---

<a name="data-pipeline"></a>
## 4. The Data Pipeline — Ingestion to Aggregation

### 4.1 The Device Telemetry Packet

Each water softener transmits a status packet containing 45+ fields as nested JSON:

```json
{
  "mtype": "status",
  "model": {"pm": 1},
  "imei": "863059072853965",
  "imsi": "404453400320623",
  "psno": "1020002099061712",
  "pd_cat": "ews",
  "rf": -57,
  "setting": {
    "hard": 2000,
    "cap": 3000,
    "n_people": 5
  },
  "cltrs": {
    "flow": 0,
    "totl": 2240,
    "dly": 0,
    "p_flow": 6100,
    "cap_used": 0,
    "cap_rem": 3000
  },
  "time": {
    "regn_t": "04:30",
    "regn_day": 25,
    "rins_t": "04:30",
    "rins_day": 25,
    "days_used": 24,
    "vac_date": "02-05-26"
  },
  "status": {
    "s_alarm": 1,
    "hol": 0,
    "im_regn": 0,
    "d_regn": 0,
    "im_rins": 0,
    "d_rins": 0
  },
  "error_codes": [28],
  "ver": {
    "ctrl": {"hw": "1.0.C", "sw": "1.6.0"},
    "iot": {"hw": "1.0.0", "sw": "1.0.0"}
  }
}
```

Key fields and their semantics:

| Field | Path | Range | Meaning |
|-------|------|-------|---------|
| Salt alarm | `status.s_alarm` | 0 or 1 | 0 = healthy, 1 = low/critical |
| Holiday mode | `status.hol` | 0 or 1 | 0 = OFF, 1 = ON |
| Immediate regen | `status.im_regn` | 0 or 1 | 0 = OFF, 1 = regenerating now |
| Capacity remaining | `cltrs.cap_rem` | 0–4,294,967,295 L | Water available before next regen |
| Daily consumption | `cltrs.dly` | 0–65,535 L | Today's water usage |
| Regen time | `time.regn_t` | HH:MM | Scheduled regeneration time |
| Regen interval | `time.regn_day` | 1–60 days | Days between regenerations |
| Days used | `time.days_used` | 0–255 | Days since last regen |
| Error codes | `error_codes` | Array of 1–100 | Active fault codes |

### 4.2 Streaming Pipeline

The streaming pipeline runs as an always-on Databricks job, consuming from Azure IoT Hub using Spark Structured Streaming with 20-second micro-batches.

**Critical design decision:** We use a single streaming query with `foreachBatch` that routes messages by device type:

```python
def foreach_combined_writer(df, batch_id):
    purifier_batch = df.filter(col("pd_cat").isNull() | (col("pd_cat") != "ews"))
    softener_batch = df.filter(col("pd_cat") == "ews")

    purifier_df = transform_purifier_msg(purifier_batch, config)
    _write_device_batch(df=purifier_df, ...)

    softener_df = transform_softener_msg(softener_batch, config)
    _write_device_batch(df=softener_df, ...)
```

Why a single query? Both device types share the same Event Hub consumer group. Two independent queries would compete for the same partition offsets, causing message loss.

**The transformation chain** (four stages per message):

1. **Parse** — Cast raw Event Hub body to string, parse JSON using device-specific `StructType` schema
2. **Rename** — Map source field paths (`data.cltrs.totl`) to database columns (`total_consumption`)
3. **Cast** — Convert all fields to target data types (String, Short, Integer)
4. **Validate** — Mark records as invalid if critical fields are null, fill remaining nulls with defaults

**Multi-sink writes** — Each micro-batch writes to three destinations:

| Destination | Purpose | Key format | TTL |
|-------------|---------|------------|-----|
| Redis | Real-time device status lookup | `{serial_no}_{yyyyMMdd}` | 36 hours |
| Delta Lake | Append-only raw data, partitioned by `year/month/day` | Idempotent transaction IDs | Permanent |
| Service Bus | Error queue for downstream processing | — | — |

### 4.3 Daily Aggregation Pipeline

Water softeners report `total_consumption` — a cumulative counter. To compute daily consumption, we use the delta approach:

```
daily_consumption = total_consumption(day N+1) - total_consumption(day N)
```

The aggregation pipeline runs as a scheduled Databricks job at 1:30 AM IST, processing data incrementally:

**Step 1: Get latest status per device per day**
```python
def get_latest_softener_status(status_df, agg_df):
    return (status_df
            .withColumn("agg_date_ist", to_date("enqueue_time_ist"))
            .join(agg_df, ..., "left_outer")
            .filter(col("agg_date_ist") >= coalesce(col("last_agg_date"), lit("1900-01-01"))))
```

This is an incremental processing pattern — each run only processes data from the last aggregated date forward.

**Step 2: Deduplicate to one record per device per day**
```python
window = Window.partitionBy("product_serial_no", "mac_id", "agg_date_ist") \
               .orderBy(col("enqueue_time_ist").desc())
return status_df.withColumn("row_number", row_number().over(window)) \
                .where(col("row_number") == 1)
```

**Step 3: Compute daily consumption as delta**
```python
window = Window.partitionBy("product_serial_no", "mac_id").orderBy("agg_date_ist")
return input_df.withColumn("daily_consumption",
    abs(coalesce(lead("total_consumption").over(window) - col("total_consumption"), lit(0))))
```

**Step 4: Upsert to PostgreSQL and Delta Lake**

PostgreSQL uses `ON CONFLICT` for idempotent upserts:
```sql
INSERT INTO water_softener_aggregate (...) VALUES (...)
ON CONFLICT ON CONSTRAINT softener_aggregate_pkey
DO UPDATE SET daily_consumption = excluded.daily_consumption, updated_at = excluded.updated_at;
```

Delta Lake uses the `MERGE` operation for the same purpose.

### 4.4 Data Model

**Raw status table** (`water_softener_iot_device_status`):

| Field | Type | Description |
|-------|------|-------------|
| mac_id | String | Device identifier (PK) |
| iot_hub_enqueued_time | BigInt | Event Hub timestamp (PK) |
| product_serial_no | String | Product serial number |
| total_consumption | Integer | Cumulative water consumption |
| daily_consumption | Integer | Device-reported daily consumption |
| salt_level | Short | Salt alarm flag (0=OK, 1=low) |
| error_codes | Integer[] | Active error codes |
| + 35 additional fields | — | Settings, timers, versions, status flags |

**Aggregate table** (`water_softener_aggregate`):

| Field | Type | Description |
|-------|------|-------------|
| mac_id + product_serial_no + agg_date_ist | Composite PK | One row per device per day |
| daily_consumption | Integer | Computed delta consumption |
| year, month, day | Short | Partitioning fields |

---

<a name="device-service"></a>
## 5. The Device Service — Backend API Layer

The Device Service is a Spring Boot application that acts as the central nervous system between IoT hardware, the data layer, and the consumer mobile app.

### Service architecture

```
Device Service (Spring Boot 3.0, Java 17)
│
├── Registration & Provisioning
│   ├── X.509 certificate generation
│   ├── Azure IoT Hub device identity
│   └── Vendor authentication (AOP)
│
├── Onboarding
│   ├── Product-to-device mapping
│   ├── Asset service integration
│   └── Firmware version sync
│
├── EWS Command Center
│   ├── Unified command dispatcher (30+ commands)
│   ├── Cloud Gateway C2D messaging
│   └── Per-command parameter builder
│
├── Device Monitoring
│   ├── Device status (Redis + PostgreSQL)
│   ├── Salt monitoring (salt level, regeneration media)
│   ├── Softener status (capacity, soft water available)
│   ├── Regeneration status (schedule, countdown, force regen)
│   └── Action cards (prioritized UI cards)
│
├── Water Analytics
│   ├── Weekly consumption (Redis + PostgreSQL merge)
│   ├── Monthly consumption (MTD aggregation)
│   ├── Typical usage comparison (EMA algorithm)
│   └── Peak usage period (2-hour time slot analysis)
│
└── OTA Firmware Updates
    ├── CUG / AB / Full rollout strategies
    ├── Device eligibility filtering
    ├── SAS URL generation for secure download
    └── Rate-limited batch dispatch
```

### Data access pattern

The API serves data from two sources, merged at the service layer:

- **Redis** — Today's live device status, updated every 20 seconds by the streaming pipeline
- **PostgreSQL** — Historical aggregates (daily, weekly, monthly consumption)

For the current week's consumption chart, both sources are merged: historical days from PostgreSQL, today's live value from Redis.

```java
// Fetch weekly consumption from Postgres
Map<String, Integer> consumption = softenerMonitoringRepository
    .fetchConsumption(device.getMacId(), productSerialNumber, startDate);

// Merge today's Redis data (overrides Postgres for today)
if (page == 0 && redisData.isPresent()) {
    consumption.put(now.format(DATE_FORMATTER), todayConsumption);
}
```

---

<a name="registration"></a>
## 6. Device Registration and Provisioning

Every IoT device needs a unique cryptographic identity before it can communicate with the cloud.

### Registration flow

When a device powers on at the factory, it calls our registration endpoint:

```
POST /devices/{mac_id}/register
Headers: vendor_id, vendor-auth-key
Body: { serialNumber, modelId }
```

The service then:

1. **Validates the vendor** using AOP-based header authentication (`@VendorAuthenticationRequired`)
2. **Generates a unique X.509 certificate** — RSA 2048-bit, SHA256withRSA, 10-year validity
3. **Creates dual thumbprints** (primary + secondary) for zero-downtime certificate rotation
4. **Registers the device on Azure IoT Hub** with SHA-1 fingerprints
5. **Stores the device record** in PostgreSQL with unique constraints on both `mac_id` and `serial_no`
6. **Returns the certificate** (PEM) and private key to the device

If any step fails, the entire transaction rolls back — no orphaned records in the database without a corresponding IoT Hub identity.

### Why dual thumbprints?

Certificate rotation is inevitable. With dual thumbprints, we provision a new certificate while the old one is still valid, allowing the device to transition without connectivity loss.

### Onboarding — linking devices to customers

Registration happens at the factory. Onboarding happens when a customer pairs the device with their mobile app:

```
PUT /onboarding-status
Body: {
    account_id: "uuid",
    iot_device_serial_no: "...",
    product_serial_no: "...",
    is_onboarded: true
}
```

The data model enforces `UNIQUE(account_id, iot_device_serial_no, product_serial_no)` — one device can serve multiple accounts (family members), but each pairing is unique.

---

<a name="command-center"></a>
## 7. Cloud-to-Device Command Center

The mobile app doesn't talk to devices directly. Commands flow through our API, which translates them into C2D messages via Azure IoT Hub.

### The command protocol

We support 30+ numeric command codes:

| Code | Command | Parameters |
|------|---------|------------|
| 3 | Refresh (instant status) | — |
| 6/7 | Enable/disable live data | — |
| 22 | Run health check | — |
| 52/53 | Immediate regeneration ON/OFF | — |
| 56/57 | Immediate rinse ON/OFF | — |
| 60 | Vacation mode | status, day, month, year |
| 62 | Write hardness | hardness (0-3420 PPM) |
| 64 | Write regen time | hour (1-24), minute (1-60) |
| 66 | Write regen days | days (1-60) |
| 74 | Write people count | count (1-99) |
| 45 | OTA firmware update | url, version, checksum |

### Unified command dispatcher

Rather than maintaining 30 separate methods with duplicated boilerplate, we built a single dispatch method:

```java
public CommandAck sendCommand(String macId, CommandRequest request) {
    EwsCommand command = EwsCommand.fromCode(request.getCode());
    String commandName = command != null ? command.getDescription() : "Unknown";

    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("mtype", "cmd");
    payload.put("code", request.getCode());
    payload.put("req_id", reqId);

    // Command-specific parameters via switch
    addCommandParameters(payload, request);

    cloudGatewayClient.sendC2DMessageToIoTDevice(macId, new Message(json), true);
}
```

The `addCommandParameters` method uses a switch on the command code to add only the relevant fields:

```java
private void addCommandParameters(Map<String, Object> payload, CommandRequest request) {
    switch (request.getCode()) {
        case 60 -> {  // Vacation mode
            payload.put("status", request.getStatus());
            if (request.getStatus() == 1) {
                payload.put("date", request.getDay());
                payload.put("month", request.getMonth());
                payload.put("year", request.getYear());
            } else {
                // Firmware requires a date even for OFF — send tomorrow
                LocalDate tomorrow = LocalDate.now().plusDays(1);
                payload.put("date", tomorrow.getDayOfMonth());
                // ...
            }
        }
        case 64, 68 -> {  // Write regen/rinse time
            payload.put("Hr", request.getHour());
            payload.put("Min", request.getMinutes());
        }
        case 62 -> payload.put("hardness", request.getHardness());
        case 74 -> payload.put("People_CNT", request.getPeopleCount());
        // Simple commands (3, 6, 7, 11, 22, 52-59, etc.) — no extra params
        default -> {}
    }
}
```

One method, one logging pattern, one error handler. The controller provides **semantically named endpoints** (`/regeneration/immediate-start`, `/settings/vacation-mode`, `/rinse/reschedule-time`) that all delegate to this dispatcher — giving the frontend readable API contracts without backend duplication.

### API endpoint structure

```
POST /ews/command/{macId}                              — Generic (any command code)

POST /ews/command/{macId}/regeneration/immediate-start  — Code 52
POST /ews/command/{macId}/regeneration/immediate-stop   — Code 53
POST /ews/command/{macId}/regeneration/reschedule-time  — Code 64
POST /ews/command/{macId}/regeneration/reschedule-days  — Code 66

POST /ews/command/{macId}/rinse/immediate-start         — Code 56
POST /ews/command/{macId}/rinse/immediate-stop          — Code 57
POST /ews/command/{macId}/rinse/reschedule-time         — Code 68

POST /ews/command/{macId}/settings/vacation-mode        — Code 60
POST /ews/command/{macId}/settings/hardness             — Code 62
POST /ews/command/{macId}/settings/people-count         — Code 74

GET  /ews/command/{macId}/firmware-version              — Code 51
GET  /ews/command/{macId}/regen-time                    — Code 65
GET  /ews/command/{macId}/capacity                      — Code 63
```

---

<a name="monitoring"></a>
## 8. Real-Time Device Monitoring

### Redis key design

```
Key:    {productSerialNo}_{yyyyMMdd}
Value:  JSON blob (45+ fields)
TTL:    36 hours
```

The Redis repository handles deserialization with defensive parsing:

```java
public Optional<SoftenerIoTDeviceStatus> findByProductSerialNo(String productSerialNo) {
    String key = productSerialNo + "_" + LocalDate.now(IST).format(KEY_DATE_FORMAT);
    Object value = redisTemplate.opsForValue().get(key);
    if (value == null) return Optional.empty();

    // Fix PostgreSQL array format before deserialization
    String json = value.toString()
        .replaceAll("\"error_codes\":\\s*\"\\{([^}]*)\\}\"", "\"error_codes\":[$1]");
    return Optional.of(OBJECT_MAPPER.readValue(json, SoftenerIoTDeviceStatus.class));
}
```

### Device status API

The device status endpoint merges Redis (live state) with PostgreSQL (historical data):

```java
public DeviceStatusResponse fetchDeviceStatus(String productSerialNo) {
    SoftenerIoTDeviceStatus status = softenerRedisRepository
        .findByProductSerialNo(productSerialNo).orElseThrow();

    int remainingDays = status.getRegenerationInterval() - status.getDayUsed();
    Double totalSoftened = softenerAggregateRepository
        .getMTDConsumption(ioTDevice.getMacId(), productSerialNo);

    return DeviceStatusResponse.builder()
        .online(ioTDevice.getOnlineStatus())
        .vacation(status.getHolidayMode() == 1)
        .totalSoftened(totalSoftened)
        .errors(List.of(status.getErrorCodes()))
        .lastRegenerationTime(lastRegenerationTime)
        .nextRegenerationTime(nextRegenerationTime)
        .capacityLeft(status.getCapacityRemaining())
        .vacationEndDate(status.getHolidayMode() == 1 ? status.getVacationEndDate() : null)
        .build();
}
```

### Action cards — prioritized UI alerts

The mobile app shows context-aware action cards based on device state:

```
Priority 1: ERROR    → "Action Needed: Error Detected"     (device offline or error codes)
Priority 2: CRITICAL → "Salt Status: Critical"             (salt alarm active)
Priority 3: HEALTHY  → "Your Softener is Performing Well"  (no issues)
```

Cards are mutually exclusive — the healthy card only shows when there are no errors and no critical alerts. This prevents contradictory messages on the same screen.

### Regeneration status

```java
if (deviceStatus.getHolidayMode() == 1) {
    status = "Paused";           // Vacation mode active
    displayTime = "Vacation Mode ON";
    allowForce = false;
} else if (deviceStatus.getImmediateRegeneration() == 1) {
    status = "Regenerating";     // Actively regenerating
    displayTime = "In Progress";
    allowForce = false;
} else {
    status = "Scheduled";        // Normal scheduled state
    displayTime = nextRegenTime.format(...);
    allowForce = true;
}
```

Note: During active regeneration, we show "In Progress" instead of a countdown timer. The device doesn't report time-remaining for regeneration, so showing a calculated countdown from schedule data would be misleading.

---

<a name="analytics"></a>
## 9. Water Consumption Analytics

### Weekly consumption chart

The API returns a Sunday-to-Saturday consumption map for any week, paginated:

```
GET /ews/water-monitoring/usage/{serialNo}          — Current week (page=0)
GET /ews/water-monitoring/usage/{serialNo}?page=1   — Previous week
GET /ews/water-monitoring/usage/{serialNo}?page=53  — Up to 1 year back
```

For the current week (page=0), today's value comes from Redis; historical days come from PostgreSQL aggregates.

### Typical usage comparison — Exponential Moving Average

Comparing today's consumption against "typical" usage is surprisingly hard with IoT data. Devices go on vacation, get powered off, or produce zero-consumption days for many reasons.

**The problem with simple averages:**

A device on vacation for two weeks produces zeros. A simple "last 7 non-zero days" average reaches back to old data with tiny values. When the user returns to normal usage:

```
Baseline = average([2, 2, 3, 2, 2, 2, 1]) = 2L     (months-old test data)
Today = 38L                                          (normal usage)
Difference = ((38 - 2) / 2) × 100 = 1800%           (absurd)
```

**The solution: EMA with guardrails**

We use an Exponential Moving Average (alpha = 0.25) over the last 14 calendar days:

```
EMA = α × current_value + (1-α) × previous_EMA
```

EMA naturally weights recent data more heavily. With α=0.25:
- Yesterday's data has 25% weight
- 7 days ago has ~13% weight
- 14 days ago has ~2% weight

Combined with three guardrails:

| Guardrail | Rule | Why |
|-----------|------|-----|
| Minimum data points | Need ≥ 3 non-zero days | Prevent unreliable baselines |
| Minimum baseline | EMA must be ≥ 5L | Prevent division by near-zero |
| Percentage cap | Max ±200% | Prevent absurd display values |

```java
// Calculate EMA baseline from non-zero values
double ema = nonZero.get(0);
for (int i = 1; i < nonZero.size(); i++) {
    ema = EMA_ALPHA * nonZero.get(i) + (1 - EMA_ALPHA) * ema;
}

double diffPercent = ((todayConsumption - ema) / ema) * 100.0;

// Cap at ±200%
if (diffPercent > MAX_DIFF_PERCENT) diffPercent = MAX_DIFF_PERCENT;
if (diffPercent < -MAX_DIFF_PERCENT) diffPercent = -MAX_DIFF_PERCENT;
```

**Real-world results with production data:**

| Today's usage | Simple Average | EMA (α=0.25) | Display |
|---|---|---|---|
| 0L | "Lower" (no %) | "Lower than your typical usage" | Same |
| 2L | 1823% Higher (BUG) | 92% Lower | "92% Lower" (correct) |
| 25L | Varies wildly | 3% Lower | "3% Lower" (correct) |
| 40L | Varies | 56% Higher | "56% Higher" (correct) |
| 500L | 24,900% | 200% (capped) | "200% Higher" (capped) |

### Peak usage period

We identify the 2-hour window with the highest water flow in a given week:

```sql
SELECT FLOOR(EXTRACT(HOUR FROM to_timestamp(iot_hub_enqueued_time / 1000.0)
       AT TIME ZONE 'Asia/Kolkata') / 2) * 2 AS hour_bucket
FROM water_softener_iot_device_status
WHERE mac_id = ? AND flow_rate > 0
  AND DATE(...) BETWEEN ? AND ?
GROUP BY hour_bucket
ORDER BY SUM(flow_rate) DESC LIMIT 1
```

Returns time slots like "10AM to 12PM" or "6PM to 8PM".

### Month-to-date consumption

```sql
SELECT COALESCE(SUM(daily_consumption), 0)
FROM water_softener_aggregate
WHERE mac_id = ? AND product_serial_no = ?
  AND year = EXTRACT(YEAR FROM CURRENT_DATE)
  AND month = EXTRACT(MONTH FROM CURRENT_DATE)
```

---

<a name="ota"></a>
## 10. Over-the-Air Firmware Updates

Pushing firmware to devices in the field is one of the riskiest operations in IoT. A bad update can brick hardware that you can't physically access.

### Staged rollout strategies

| Strategy | Target | Use case |
|----------|--------|----------|
| **CUG** (Closed User Group) | Devices flagged `cug_device = true` | Internal testing with real hardware |
| **AB** (A/B testing) | Devices flagged `ab_device = true` | Beta rollout to production subset |
| **FULL** | All eligible devices | General availability |

### Eligibility filtering

Not every device gets the update:

- Device must be **online** (`online_status = true`)
- Device must be **onboarded** (`is_onboarded = true`)
- Current firmware version must be **lower** than target
- **Hardware version** must match (can't flash v2 firmware on v1 hardware)
- **Connection type** must match (WiFi vs LTE may have different firmware)
- **Model and vendor** must match

### OTA flow

1. Upload firmware binary, register metadata (URL, version, checksum, target HW version)
2. OTA service paginates through eligible devices (100 per batch)
3. For each device, generate a **SAS URL** (2-hour expiry) for secure firmware download
4. Send OTA command (code 45) with download URL and MD5 checksum
5. Device downloads, verifies checksum, applies update
6. Track OTA status per device (`iot_ota_status`, `ctrl_ota_status`)

Rate limiting (100 messages/minute) prevents IoT Hub throttling during bulk rollouts.

---

<a name="debugging"></a>
## 11. Production Debugging — 30 Bugs in 2 Weeks

We shipped the first version to QA with three devices in the field. Within two days, there were 30 bugs on a shared spreadsheet. Here are the most instructive ones.

### 11.1 The Command That Reached the Cloud But Not the Device

API returns `201 Created`. Device does nothing.

**Root cause:** Java enums serialized as strings (`"IMMEDIATE_REGENERATION"`) instead of integers (`52`). IoT Hub accepted the message — it's just a broker. The device firmware couldn't parse the string command code.

```java
// Bug: "code": "IMMEDIATE_REGENERATION"
messageData.put(CODE, EwsCommand.IMMEDIATE_REGENERATION);

// Fix: "code": 52
messageData.put(CODE, EwsCommand.IMMEDIATE_REGENERATION.getCode());
```

**Lesson:** A 201 from a message broker doesn't mean the device understood your message. Log the exact payload being sent.

### 11.2 The PostgreSQL Array That Crashed Jackson

`InvalidDefinitionException: Cannot construct instance of java.lang.Integer[]: no String-argument constructor to deserialize from String value ('{28}')`

The device sends `[28]`. PostgreSQL stores it as `{28}` (native array format). The pipeline writes `{28}` to Redis. Jackson can't parse PostgreSQL's array literal as JSON.

**Fix:** One regex before deserialization:
```java
String json = value.toString()
    .replaceAll("\"error_codes\":\\s*\"\\{([^}]*)\\}\"", "\"error_codes\":[$1]");
```

### 11.3 The Conflicting Action Cards

"Your Softener is Performing Well" displayed alongside "Action Needed: Error Detected" on the same screen.

**Root cause:** The healthy card was unconditionally added to every response. Also, `hasErrors` checked `errorCodes != null` — but after the PostgreSQL fix, empty arrays deserialized as `[]` (not null), so `hasErrors` was always true.

**Fix:** Make healthy card exclusive (only when no errors, no critical salt, no vacation). Check `errorCodes.length > 0` instead of `!= null`.

### 11.4 When "0 Means Healthy" and "0 Means Empty"

The action card showed "Salt Status: Critical" for a healthy device. The salt monitoring card showed "Healthy" for a critically low device.

**Root cause:** The device uses `s_alarm` as a binary flag: `0 = healthy, 1 = critical`. But two different services interpreted the value differently:

```java
// Action card: 0 < 1 = true → incorrectly shows "Critical"
boolean lowSalt = status.getSaltLevel() < 1;

// Salt monitoring: 0 == 1 = false → incorrectly shows "Healthy"
if (saltLevel == 1) { /* critical */ }
```

The action card treated `0` (healthy) as critical. The salt monitoring service would miss `0` if it truly meant empty in a different context.

**Lesson:** Document device value semantics in a single, versioned contract shared between firmware, pipeline, and backend teams.

### 11.5 The Midnight Redis Gap

At 12:00 AM IST, the app shows "Unable to map product serial number." Five minutes later, everything works.

**Root cause:** Redis key `{serialNo}_{yyyyMMdd}` rolls over at midnight. The new key doesn't exist until the device sends its next status packet. LTE water softeners transmit on state changes, not on a fixed schedule — so the gap can last minutes to hours.

### 11.6 The 790-Day Capacity Estimate

"Enough soft water for 790 days" — for a device with a 790L tank.

**Root cause:** When `dailyConsumption = 0`, code forced it to 1 to avoid division by zero: `790 / 1 = 790 days`. The device has a fixed regeneration schedule — the capacity-based estimate is meaningless.

### 11.7 The Regeneration Timer That Restarted

Device completes regeneration. App shows a fresh 45-minute countdown.

**Root cause:** During active regeneration, the timer showed time until the *next scheduled regeneration* — not time remaining for the current one. When regeneration completed but the Redis cache hadn't updated yet, the stale `immediateRegeneration = 1` flag created a phantom timer.

**Fix:** Show "In Progress" instead of a synthetic countdown. If the device doesn't report time-remaining, don't calculate it from unrelated schedule data.

### 11.8 The Wrong Rinse Command Code

"Immediate Rinse" was actually sending "Delay Regen OFF."

**Root cause:** `TRIGGER_IMMEDIATE_RINSE(55)` in the enum — but code 55 is "Delay Regen OFF" per the firmware spec. Immediate Rinse is code 56. The device silently accepted the wrong command and turned off delay regeneration instead of starting a rinse cycle.

### Bug pattern taxonomy

| Category | Bugs | Root cause pattern |
|----------|------|--------------------|
| Serialization boundary | #1, #2 | Data format changes silently at system boundaries |
| Semantic interpretation | #4, #8 | Same number means different things in different contexts |
| Missing/stale data | #5, #6, #7 | IoT devices send data irregularly, cache goes stale |
| Mathematical edge cases | 1823% spike, 790 days | Standard math on non-standard data distributions |

---

<a name="decisions"></a>
## 12. Architecture Decisions and Trade-offs

### Why Redis + PostgreSQL (not just one)?

| Concern | Redis only | PostgreSQL only | Both (our choice) |
|---------|-----------|----------------|-------------------|
| Read latency for current status | Sub-ms | 5-20ms | Sub-ms (Redis) |
| Historical queries | No (volatile) | Yes | Yes (PostgreSQL) |
| Weekly/monthly aggregations | Would need sorted sets | Good (SQL aggregates) | Good (PostgreSQL) |
| Cost at scale | High memory cost | High query load | Balanced |
| Data durability | No (cache eviction) | Yes | Yes (PostgreSQL as source of truth) |

### Why Exponential Moving Average for typical usage?

| Approach | Handles vacation gaps? | Handles outliers? | Adapts to changes? |
|----------|----------------------|-------------------|--------------------|
| Simple average (last 7 non-zero) | No — reaches back months | No | No |
| Same-day-last-week | Partially | No | No |
| EMA (α=0.25, 14-day window) | Yes — zeros excluded | Smoothed naturally | Yes — 2-3 week adaptation |
| Median | Yes | Yes | Slow |

### Why a unified command dispatcher?

| Approach | Lines of code | Adding a new command | Consistency |
|----------|--------------|---------------------|-------------|
| 30 individual methods | ~900 (30 × 30 lines) | Copy-paste, modify | Error-prone |
| Unified dispatcher + switch | ~150 | Add enum + switch case | Guaranteed |

### Why date-partitioned Redis keys?

| Approach | Pros | Cons |
|----------|------|------|
| `{serialNo}_{date}` | Natural TTL, clean history separation | Midnight gap |
| `{serialNo}_latest` | No gap | Need manual cleanup, no history separation |
| Both (latest + dated) | No gap + history | Double writes |

We chose date-partitioned keys for simplicity. The midnight gap is a known trade-off, mitigated by the 36-hour TTL (yesterday's key is still available for fallback).

---

<a name="metrics"></a>
## 13. Key Metrics

| Metric | Value |
|--------|-------|
| REST controllers | 12+ |
| API endpoints | 60+ |
| C2D command types | 30+ |
| Telemetry fields per device | 45+ |
| Database migrations | 26+ (Flyway) |
| OTA rollout strategies | 3 (CUG, AB, Full) |
| Certificate validity | 10 years with dual-thumbprint rotation |
| Streaming micro-batch interval | 20 seconds |
| Aggregation job schedule | Daily at 1:30 AM IST |
| Redis TTL | 36 hours |
| API response time | Sub-second (via Redis) |
| Bugs found in QA sprint | 30 |
| Bugs resolved (backend) | 15 |
| Production incidents | 0 (everything caught in QA) |

---

<a name="lessons"></a>
## 14. Lessons Learned

### On IoT architecture

**1. 201 OK does not mean the device got your message.** In web development, API responses are authoritative. In IoT, the response tells you the broker accepted the message — not that the device received, parsed, or executed it. Design for end-to-end observability. Log the exact payload. Monitor device acknowledgements.

**2. Every system boundary is a serialization risk.** Device → IoT Hub → Databricks → PostgreSQL → Redis → Spring Boot — that's five format boundaries. A JSON array `[28]` becomes a PostgreSQL literal `{28}` becomes a Redis string `"{28}"`. Test the full chain with real device data, not mocks.

**3. Your data layer should match your access patterns.** Redis for "what's happening right now." PostgreSQL for "what happened over time." The merge point in the API layer is simple code, but the architectural decision to separate hot and cold data was load-bearing.

### On device data

**4. IoT data has gaps. Design for them.** Devices go offline, enter vacation mode, get factory reset, or transmit only on state changes. Any algorithm that assumes continuous, complete data will break. Use time-bounded windows, handle zeros explicitly, and have fallback strategies for missing data.

**5. Document value semantics once, share everywhere.** `s_alarm = 0` means "healthy" in the firmware spec, but a developer reading `salt_level = 0` reasonably assumes "empty." One field, two interpretations, two bugs. Maintain a single, versioned device contract shared between firmware, pipeline, and backend teams.

**6. Don't synthesize data you don't have.** If the device doesn't report "time remaining for current regeneration," don't calculate it from schedule data. "In Progress" is better than a fake countdown that jumps around and restarts.

### On consumer-facing analytics

**7. Simple math on messy data produces absurd results.** An 1823% usage spike, a 790-day capacity estimate, a negative flow rate. The math is correct, but the UX is broken. Use robust statistics (EMA, median) with caps and thresholds. The right answer for a consumer app is always "sensible," not "precise."

**8. Test with real device data from day one.** Dummy data will miss the empty strings, the Short integer overflows, the midnight key gaps, and the firmware versions with letters in them. Set up a test environment that replays actual device telemetry.

### On production operations

**9. Staged rollouts are non-negotiable for firmware.** CUG first, then AB, then full. We caught firmware bugs in CUG testing that would have bricked thousands of devices. There is no "undo" for a bad firmware push to a device in someone's home.

**10. Rate-limit everything that talks to IoT Hub.** Without rate limiting, a bulk OTA operation triggers hub throttling that blocks all C2D messages — including critical commands from customers trying to control their devices.

---

## Conclusion

Building an IoT platform isn't just connecting devices to the cloud. It's building a system that survives the messy reality of hardware — devices that send empty data, PCBs that get replaced in the field, firmware that needs careful rollout, cellular connections that drop, and customers who expect their app to just work.

The principles that held up:

1. **Defense in depth** — validate at the API boundary, enforce at the database level, default at the model level
2. **Design for eventual consistency** — IoT is asynchronous by nature, embrace it
3. **Cache the hot path** — Redis for real-time, PostgreSQL for truth
4. **Treat every device message as untrusted input** — because it is
5. **Measure twice, cut once** — especially when the "cut" is a firmware update to thousands of devices

---

*Built with Java 17, Spring Boot, Azure IoT Hub, Databricks, Delta Lake, PostgreSQL, Redis, and Terraform. Deployed on Kubernetes.*

*This post consolidates the full architecture — data pipeline, device management platform, and production debugging — into a single reference. If you're building IoT systems, I hope it saves you some of the debugging time it cost me.*

*Connect with me on LinkedIn if you're working on similar systems — always happy to exchange war stories.*