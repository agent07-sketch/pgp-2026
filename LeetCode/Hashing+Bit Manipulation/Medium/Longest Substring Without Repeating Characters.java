/*Problem

        Find the length of the longest substring containing no repeated characters.

        Example:

        Input: "abcabcbb"
        Output: 3

        Substring = "abc"
        🔑 Key Idea — Sliding Window + HashSet

        Maintain:

        left pointer
        right pointer
        Set of characters currently in the window

        When duplicate appears:

        Shrink window from left
        🚀 Java Solution

 */



import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}


/*
Optimized Version (Using Last Seen Index)

 */




class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            if (lastSeen[c] >= left)
                left = lastSeen[c] + 1;

            lastSeen[c] = right;

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}





/*

Complexity
Time  : O(n)
Space : O(1)

 */