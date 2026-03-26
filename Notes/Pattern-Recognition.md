(Your original content — cleaned)

# 🔥 Binary Search on Answer

---

## 🚨 Pattern Recognition

If question says:

- minimum / maximum
- at least / at most
- can we do this in X?

👉 Think: **Binary Search on Answer**

---

## 🔍 Key Clues

| Clue                | Meaning                |
|---------------------|------------------------|
| minimize maximum    | left bias              |
| maximize minimum    | right bias             |
| monotonic behavior  | binary search possible |
| fixed constraints   | greedy check           |

---

## 🧠 Steps

### Step 1: Identify Pattern

- Numeric answer?
- Validity check possible?
- Monotonic behavior?

---

### Step 2: Define Search Space

```java
low = min possible
high = max possible
Step 3: Check Function
boolean possible(int mid)
Step 4: Templates
✅ Minimize
while(low <= high){
    int mid = low + (high - low)/2;

    if(possible(mid)){
        ans = mid;
        high = mid - 1;
    } else {
        low = mid + 1;
    }
}
✅ Maximize
while(low <= high){
    int mid = low + (high - low)/2;

    if(possible(mid)){
        ans = mid;
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
⚡ Golden Line

If you can verify → you can binary search


---

# 📄 Example Problem File  
`problems/medium/koko-eating-bananas.md`

```md
# 🍌 Koko Eating Bananas

## 🧠 Pattern
Binary Search on Answer

---

## ❓ Problem
Find minimum eating speed such that Koko finishes within H hours.

---

## 💡 Idea

- Answer = speed
- Range = [1, max(pile)]
- Check = can finish in H hours?

---

## ✅ Code (Java)

```java
public int minEatingSpeed(int[] piles, int h) {
    int low = 1;
    int high = Arrays.stream(piles).max().getAsInt();
    int ans = high;

    while(low <= high){
        int mid = low + (high - low)/2;

        if(canFinish(piles, h, mid)){
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return ans;
}

private boolean canFinish(int[] piles, int h, int speed){
    int hours = 0;

    for(int p : piles){
        hours += (p + speed - 1) / speed;
    }

    return hours <= h;
}

---

# 📄 Templates (`templates/minimize-template.java`)

```java
public int binarySearchMin(int low, int high) {
    int ans = high;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (possible(mid)) {
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return ans;
}