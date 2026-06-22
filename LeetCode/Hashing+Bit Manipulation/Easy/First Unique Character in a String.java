/*Problem

Find the index of the first non-repeating character.

Example:

Input: s = "leetcode"
Output: 0

Input: s = "loveleetcode"
Output: 2
        🔑 Key Idea — Frequency Counting
Count occurrences of each character.
Traverse again and return the first character whose frequency is 1.
        🚀 Java Solution

 */








class Solution {
    public int firstUniqChar(String s) {

        int[] freq = new int[26];

        // Count frequencies
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find first unique character
        for (int i = 0; i < s.length(); i++) {

            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }
}






/*
Complexity
Time  : O(n)
Space : O(1)
Alternative

Use HashMap<Character, Integer> when characters are not limited to lowercase English letters.

 */