/*Problem

Koko eats bananas at speed k per hour.
Find minimum k such that all bananas are eaten within h hours.

        🔑 Pattern Recognition

Minimize a value (k)

Can check feasibility

👉 Classic Binary Search on Answer

Search Space
low  = 1
high = max(piles)
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        for (int p : piles)
            high = Math.max(high, p);

        int ans = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canEat(piles, h, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canEat(int[] piles, int h, int k) {

        long hours = 0;

        for (int p : piles) {
            hours += (p + k - 1) / k; // ceil(p/k)
        }

        return hours <= h;
    }
}

/*
Complexity
O(n log max(pile))
 */