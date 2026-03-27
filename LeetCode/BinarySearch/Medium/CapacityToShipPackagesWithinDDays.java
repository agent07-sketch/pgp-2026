/*(Variation / Reinforcement Problem)

        👉 Slight variation to test understanding deeply.

Problem Twist

Given weights, return minimum capacity such that:

Exactly D days used

Same as before but more strict understanding needed

🔑 Insight

Same pattern:

Minimize capacity

Greedy check
*/

class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int low = 0, high = 0;

        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (daysNeeded(weights, mid) <= days) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int daysNeeded(int[] weights, int cap) {

        int days = 1, sum = 0;

        for (int w : weights) {

            if (sum + w > cap) {
                days++;
                sum = w;
            } else {
                sum += w;
            }
        }

        return days;
    }
}

/*
What You Just Learned (Very Important)
Difference in Check Function
Problem	Check Returns
Koko	hours ≤ h
Bouquets	bouquets ≥ m
Shipping	days ≤ D
Speed	time ≤ hour

👉 Same pattern, different interpretation

🔥 Advanced Insight

All these problems reduce to:

f(mid) = monotonic function

Example:

speed ↑ → time ↓
capacity ↑ → days ↓
distance ↑ → feasibility ↓
        🧠 Mental Shortcut

Whenever you see:

        "minimum X such that..."
        "maximum Y such that..."

        👉 Instantly think:

Binary Search on Answer

 */