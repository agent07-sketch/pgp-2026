/*Problem

Count subarrays with exactly k odd numbers.

        Example:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
        🔑 Key Trick
Exactly K = AtMost(K) - AtMost(K-1)

 */


class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {

        int left = 0, count = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] % 2 == 1)
                k--;

            while (k < 0) {
                if (nums[left] % 2 == 1)
                    k++;
                left++;
            }

            result += right - left + 1;
        }

        return result;
    }
}



/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Why it works
Count all valid subarrays ending at each index
🔥 Interview Summary
Problem	Pattern
Find Anagrams	Fixed Sliding Window
Nice Subarrays	Sliding Window + AtMost Trick
💡 Key Takeaways
Find Anagrams
Fixed window + frequency + count
Nice Subarrays
Exactly K = AtMost(K) - AtMost(K-1)
⚡ Sliding Window Cheat
Pattern	Use Case
Fixed window	Anagrams
Variable window	Longest substring
AtMost trick	K distinct / odd count

 */