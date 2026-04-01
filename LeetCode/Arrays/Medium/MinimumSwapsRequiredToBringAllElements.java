/*Problem

Given array and integer k, find minimum swaps required to bring all elements ≤ k together.

Example:

Input: arr = [2,1,5,6,3], k = 3
Output: 1
        🔑 Key Idea — Sliding Window
Step 1: Count “good” elements
        good = count of elements ≤ k
Step 2: Find minimum “bad” elements in window of size = good

Bad elements:

elements > k
 */


class Solution {
    public int minSwap(int[] arr, int n, int k) {

        int good = 0;

        // count elements <= k
        for (int num : arr) {
            if (num <= k)
                good++;
        }

        // count bad elements in first window
        int bad = 0;
        for (int i = 0; i < good; i++) {
            if (arr[i] > k)
                bad++;
        }

        int ans = bad;

        // sliding window
        for (int i = 0, j = good; j < n; i++, j++) {

            // remove left
            if (arr[i] > k)
                bad--;

            // add right
            if (arr[j] > k)
                bad++;

            ans = Math.min(ans, bad);
        }

        return ans;
    }
}

/*
🧠 Why this works
We want all good elements together
→ choose a window of size = good
→ minimize bad elements inside it

Minimum bad = minimum swaps needed.

Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Rotate Array	Reversal Trick
Min Swaps (≤ k)	Sliding Window
💡 Key Takeaways
Rotate Array
Reverse → Reverse → Reverse
Min Swaps
Fix window size = good
Minimize bad elements

 */