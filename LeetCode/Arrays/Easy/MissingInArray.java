/*Problem

Given array of size n-1 containing numbers from:

        1 to n

Find the missing number.

        🔑 Optimal Idea — Sum Formula

Expected sum:

n * (n + 1) / 2

Missing:

expected - actual

 */


class Solution {
    public int missingNumber(int[] arr, int n) {

        int expected = n * (n + 1) / 2;
        int actual = 0;

        for (int num : arr)
            actual += num;

        return expected - actual;
    }
}
⚠️ Better Approach (No Overflow) — XOR Trick
class Solution {
    public int missingNumber(int[] arr, int n) {

        int xor = 0;

        // XOR all numbers 1 to n
        for (int i = 1; i <= n; i++)
            xor ^= i;

        // XOR array elements
        for (int num : arr)
            xor ^= num;

        return xor;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

 */