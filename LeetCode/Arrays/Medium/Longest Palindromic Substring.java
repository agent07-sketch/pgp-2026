/*Problem

Find the longest substring which is a palindrome.

Example:

Input:  "babad"
Output: "bab" or "aba"
        🔑 Key Idea — Expand Around Center

Every palindrome expands from a center:

Odd length → center at 1 char
Even length → center between 2 chars
🚀 Java Solution (Optimal O(n²))

 */


class Solution {
    public String longestPalindrome(String s) {

        if (s == null || s.length() < 2)
            return s;

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = expand(s, i, i);     // odd
            int len2 = expand(s, i, i + 1); // even

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {

        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}

/*
Complexity
Time  : O(n^2)
Space : O(1)
🧠 Why this works
Check all possible centers
Expand outward → longest palindrome
⚡ Advanced (FYI)
Manacher’s Algorithm → O(n)
(Not required in most interviews)

 */