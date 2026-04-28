/*Problem

        Find the longest substring containing at most K distinct characters.

        Example:

        s = "eceba", k = 2 → Output = 3 ("ece")
        🔑 Key Idea — Sliding Window + HashMap

        Maintain:

        window with ≤ k distinct characters
        🚀 Java Solution

 */




import java.util.*;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // shrink if more than k distinct
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0)
                    map.remove(leftChar);

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}



/*
Complexity
Time  : O(n)
Space : O(k)
🧠 Insight
Window grows until invalid → then shrink

 */