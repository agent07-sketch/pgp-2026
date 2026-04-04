/*Problem

Check if s2 contains any permutation of s1.

Example:

Input:  s1 = "ab", s2 = "eidbaooo"
Output: true   ("ba" exists)
        🔑 Key Idea — Sliding Window + Frequency

We need a window of size s1.length() in s2 such that:

frequency(window) == frequency(s1)
🧠 Optimization Trick

Use:

One frequency array
Slide window and adjust counts
*/


class Solution {
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) return false;

        int[] freq = new int[26];

        // fill freq with s1
        for (char c : s1.toCharArray())
            freq[c - 'a']++;

        int left = 0;

        for (int right = 0; right < s2.length(); right++) {

            freq[s2.charAt(right) - 'a']--;

            // if negative → too many chars → shrink
            while (freq[s2.charAt(right) - 'a'] < 0) {
                freq[s2.charAt(left) - 'a']++;
                left++;
            }

            // window size matches
            if (right - left + 1 == s1.length())
                return true;
        }

        return false;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

 */