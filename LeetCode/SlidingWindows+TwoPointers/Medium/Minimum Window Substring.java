/*Problem

        Find smallest substring of s containing all chars of t.

        Example:

        s = "ADOBECODEBANC", t = "ABC"
        Output = "BANC"
        🔑 Key Idea — Sliding Window + Frequency Match

        We:

        Expand window → satisfy condition
        Shrink window → minimize
        🚀 Java Solution

 */




import java.util.*;

class Solution {
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) return "";

        int[] freq = new int[128];

        for (char c : t.toCharArray())
            freq[c]++;

        int left = 0, count = t.length();
        int minLen = Integer.MAX_VALUE, start = 0;

        for (int right = 0; right < s.length(); right++) {

            if (freq[s.charAt(right)] > 0)
                count--;

            freq[s.charAt(right)]--;

            // valid window
            while (count == 0) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                freq[s.charAt(left)]++;

                if (freq[s.charAt(left)] > 0)
                    count++;

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" :
                s.substring(start, start + minLen);
    }
}



/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Insight
Expand → satisfy
Shrink → optimize
🔥 Interview Summary
Problem	Pattern
K Distinct Subarrays	Sliding Window + AtMost
Min Window Substring	Sliding Window + Frequency
💡 Key Takeaways
Subarrays with K Distinct
Exactly(K) = AtMost(K) - AtMost(K-1)
Minimum Window Substring
Count how many chars still needed
Shrink when valid

 */