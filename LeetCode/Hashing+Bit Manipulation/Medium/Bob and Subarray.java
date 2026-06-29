/*Problem

Given an array and an integer k, count the number of subarrays whose bitwise OR is at least k.

        🔑 Key Idea — Brute Force with Running OR

Unlike sum or XOR, bitwise OR cannot be removed efficiently when shrinking a window, so a simple sliding-window approach doesn't work in general.

Maintain a running OR for each starting index.

🚀 Java Solution

 */



class Solution {

    public int countSubarrays(int[] arr, int k) {

        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {

            int currentOR = 0;

            for (int j = i; j < n; j++) {

                currentOR |= arr[j];

                if (currentOR >= k)
                    count++;
            }
        }

        return count;
    }
}







/*
Complexity
Time  : O(n²)
Space : O(1)
🧠 Example
arr = [1,2,3]
k = 3

Subarrays:

        [1]      OR = 1
        [1,2]    OR = 3 ✓
        [1,2,3]  OR = 3 ✓
        [2]      OR = 2
        [2,3]    OR = 3 ✓
        [3]      OR = 3 ✓

Answer = 4
        🔥 Interview Summary
Problem	Pattern
Count Triplets That Can Form Two Arrays of Equal XOR	Prefix XOR / XOR Observation
Bob and Subarray	Bit Manipulation + Nested Traversal
💡 Key Takeaways
Equal XOR Triplets
XOR(i...k) == 0
        ⇒ Count += (k - i)
Bob and Subarray
Running OR
for every starting index
⚡ Pattern Recognition Guide
Prefix XOR

Used in:

Count Triplets That Can Form Two Arrays of Equal XOR
Subarray XOR Equals K
Single Number
Maximum XOR Problems
Bitwise OR

Used in:

Bob and Subarray
Bitwise ORs of Subarrays
Maximum OR after Operations

 */