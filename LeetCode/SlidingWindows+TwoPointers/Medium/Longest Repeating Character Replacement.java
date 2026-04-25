/*Problem

Replace at most k characters so that the substring has all same characters.

        Example:

s = "AABABBA", k = 1 → Output = 4
        🔑 Key Idea — Sliding Window + Max Frequency

We want a window where:

        (window size - maxFreq) ≤ k

👉 Means we can convert all other chars into the most frequent one.

        🚀 Java Solution

 */



class Solution {
    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];
        int left = 0, maxFreq = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            // shrink if invalid
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}


/*
🧠 Insight
We don’t recompute maxFreq on shrink → still works!

        👉 This is a tricky optimization that keeps it O(n)

Complexity
Time  : O(n)
Space : O(1)

 */