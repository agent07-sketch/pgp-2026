/*Problem

Find the length of the longest substring with all unique characters.

        Example:

Input:  "abcabcbb"
Output: 3  ("abc")
        🔑 Key Idea — Sliding Window + Hashing

Maintain a window [left → right]:

Expand right
If duplicate → shrink from left
 */


class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] freq = new int[256];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            freq[s.charAt(right)]++;

            while (freq[s.charAt(right)] > 1) {
                freq[s.charAt(left)]--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
⚡ Optimized (Using last index)
 */


class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] last = new int[256];
        Arrays.fill(last, -1);

        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            if (last[c] >= left)
                left = last[c] + 1;

            last[c] = right;

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Insight
Never move left backward → ensures O(n)

 */