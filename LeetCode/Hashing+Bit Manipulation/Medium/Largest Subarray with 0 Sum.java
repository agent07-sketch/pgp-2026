/*Problem

        Find the length of the largest subarray whose sum equals 0.

        Example:

        arr = [15,-2,2,-8,1,7,10,23]

        Output = 5

        Subarray:

        [-2,2,-8,1,7]
        🔑 Key Idea — Prefix Sum + HashMap

        If:

        prefixSum(i) == prefixSum(j)

        then:

        sum(i+1 ... j) = 0

        Store the first occurrence of every prefix sum.

        🚀 Java Solution

 */








import java.util.*;

class Solution {

    int maxLen(int arr[], int n) {

        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            sum += arr[i];

            if (sum == 0)
                maxLength = i + 1;

            if (map.containsKey(sum)) {

                maxLength = Math.max(
                        maxLength,
                        i - map.get(sum));
            }
            else {

                map.put(sum, i);
            }
        }

        return maxLength;
    }
}








/*
Complexity
Time  : O(n)
Space : O(n)
🔥 Interview Summary
Problem	Pattern
Count Distinct Elements in Every Window	Sliding Window + HashMap
Largest Subarray with 0 Sum	Prefix Sum + HashMap
💡 Key Takeaways
Distinct Window
Fixed window
+
Frequency map
Largest Zero Sum Subarray
Repeated prefix sum
⇒ zero sum between them
⚡ Pattern Recognition Guide
Sliding Window + HashMap

Used in:

Count Distinct Elements in Every Window
Find All Anagrams
Longest Substring Without Repeating Characters
Subarrays with K Distinct
Prefix Sum + HashMap

Used in:

Largest Subarray with 0 Sum
Subarray Sum Equals K
Binary Subarrays With Sum
Count Nice Subarrays

 */