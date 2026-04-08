/*Problem

Count substrings with exactly K distinct characters.

🔑 Key Trick
Exactly K = AtMost(K) - AtMost(K-1)
🧠 Sliding Window

Count substrings with at most K distinct.

 */


class Solution {

    public int substrCount(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }

    private int atMostK(String s, int k) {

        int[] freq = new int[256];
        int left = 0;
        int count = 0;
        int distinct = 0;

        for (int right = 0; right < s.length(); right++) {

            if (freq[s.charAt(right)] == 0)
                distinct++;

            freq[s.charAt(right)]++;

            while (distinct > k) {
                freq[s.charAt(left)]--;

                if (freq[s.charAt(left)] == 0)
                    distinct--;

                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Sort by Frequency	HashMap / Bucket Sort
Substrings with K Distinct	Sliding Window
💡 Key Takeaways
Frequency Sort
Count → Sort → Build string
K Distinct Substrings
Exactly K = AtMost(K) - AtMost(K-1)
⚡ Interview Gold Insight
Sliding Window Pattern

If question says:

substrings / subarrays + condition

👉 Think:

Sliding Window

 */