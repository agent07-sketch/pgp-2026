/*Problem

        Find the length of the longest subarray having sum exactly K.

        Example:

        arr = [10,5,2,7,1,9]
        K = 15

        Output = 4

        Subarray:
        [5,2,7,1]
        🔑 Key Idea — Prefix Sum + HashMap

        If:

        currentSum - K = previousPrefixSum

        then:

        subarray between them has sum K

        Store the first occurrence of every prefix sum.

        🚀 Java Solution

 */




import java.util.*;

class Solution {

    public int longestSubarray(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (sum == k)
                maxLen = i + 1;

            if (map.containsKey(sum - k)) {

                maxLen = Math.max(
                        maxLen,
                        i - map.get(sum - k));
            }

            // Store first occurrence only
            map.putIfAbsent(sum, i);
        }

        return maxLen;
    }
}






/*
Complexity
Time  : O(n)
Space : O(n)
Special Case (Only Positive Numbers)

Use Sliding Window:

        int left = 0, sum = 0;

for(int right = 0; right < n; right++){

sum += arr[right];

        while(sum > k)
sum -= arr[left++];

        if(sum == k)
maxLen = Math.max(maxLen, right-left+1);
}

        ⚠️ This works only when all numbers are positive.

        🔥 Interview Summary
Problem	Pattern
Longest Substring Without Repeating Characters	Sliding Window
Longest Subarray with Sum K	Prefix Sum + HashMap
💡 Key Takeaways
Longest Substring
Duplicate found
→ shrink window
Longest Subarray Sum K
currentSum - K
→ lookup in HashMap
⚡ Pattern Recognition Guide
Sliding Window

Used in:

Longest Substring Without Repeating Characters
Character Replacement
Max Consecutive Ones III
Minimum Window Substring
Prefix Sum + HashMap

Used in:

Longest Subarray with Sum K
Largest Subarray with 0 Sum
Subarray Sum Equals K
Binary Subarrays With Sum

 */