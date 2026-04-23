/*Problem

        Find all starting indices of substrings in s that are anagrams of p.

        Example:

        Input:  s = "cbaebabacd", p = "abc"
        Output: [0,6]
        🔑 Key Idea — Fixed Sliding Window + Frequency
        Window size = p.length()
        Maintain frequency count
        Slide window and compare

 */


import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        int[] freq = new int[26];

        // count for p
        for (char c : p.toCharArray())
            freq[c - 'a']++;

        int left = 0, right = 0;
        int count = p.length();

        while (right < s.length()) {

            if (freq[s.charAt(right) - 'a'] > 0)
                count--;

            freq[s.charAt(right) - 'a']--;
            right++;

            if (count == 0)
                result.add(left);

            // maintain window size
            if (right - left == p.length()) {

                if (freq[s.charAt(left) - 'a'] >= 0)
                    count++;

                freq[s.charAt(left) - 'a']++;
                left++;
            }
        }

        return result;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Insight
Window size fixed → track count instead of comparing arrays

 */