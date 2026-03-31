/*Problem

Check if two strings are anagrams:

        Same characters, same frequency

Example:

Input:  s = "anagram", t = "nagaram"
Output: true
        🔑 Optimal Idea — Frequency Count

Instead of sorting, use a frequency array.

 */

class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int f : freq) {
            if (f != 0)
                return false;
        }

        return true;
    }
}
Alternative (Sorting)
import java.util.*;

class Solution {
    public boolean isAnagram(String s, String t) {

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

 */