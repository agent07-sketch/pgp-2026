/*Problem

Find:

Sum of all subarrays of an array

Example:

arr = [1,2,3]
Subarrays:
        [1], [2], [3], [1,2], [2,3], [1,2,3]

Sum = 1+2+3 + 3+5 + 6 = 20
        🔑 Key Idea — Contribution Technique

Each element contributes multiple times.

For element at index i:

Contribution = arr[i] * (i + 1) * (n - i)

Why?

        (i + 1) → choices for start
        (n - i) → choices for end

 */


class Solution {
    public long subarraySum(int[] arr) {

        int n = arr.length;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (long) arr[i] * (i + 1) * (n - i);
        }

        return sum;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
 */