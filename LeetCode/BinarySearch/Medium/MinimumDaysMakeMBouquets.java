/*Problem

Each flower blooms on bloomDay[i].

Make m bouquets with k adjacent flowers.

Find minimum days.

🔑 Pattern Recognition

Minimize days

Can check: “Can we form m bouquets in mid days?”

        👉 Again Binary Search on Answer

Search Space
low  = min(bloomDay)
high = max(bloomDay)
 */

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        long totalNeeded = (long)m * k;
        if (totalNeeded > bloomDay.length) return -1;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int d : bloomDay) {
            low = Math.min(low, d);
            high = Math.max(high, d);
        }

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canMake(bloomDay, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canMake(int[] bloomDay, int m, int k, int day) {

        int flowers = 0;
        int bouquets = 0;

        for (int d : bloomDay) {

            if (d <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }

            if (bouquets >= m)
                return true;
        }

        return false;
    }
}

/*
Complexity
O(n log maxDay)
🔥 Core Template (VERY IMPORTANT)

All extended binary search problems follow this:

        while(low <= high){

int mid = low + (high - low) / 2;

    if(possible(mid)){
ans = mid;        // store answer
high = mid - 1;   // minimize
        } else {
low = mid + 1;
        }
        }
        🧠 Interview Insight

If you see:

minimum / maximum

can we do this in X?

        👉 Immediately think:

Binary Search on Answer

 */