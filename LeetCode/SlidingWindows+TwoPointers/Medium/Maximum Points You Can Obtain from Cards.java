/*Problem

Pick k cards from either:

start OR end

Maximize total points.

🔑 Key Idea — Sliding Window (Reverse Thinking)

Instead of picking k cards:
        👉 Remove a subarray of size (n - k) with minimum sum

🧠 Why?
total sum - min subarray = max score
🚀 Java

 */


class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        int total = 0;
        for (int num : cardPoints)
            total += num;

        int windowSize = n - k;

        if (windowSize == 0)
            return total;

        int windowSum = 0;
        int minSum = Integer.MAX_VALUE;

        int left = 0;

        for (int right = 0; right < n; right++) {

            windowSum += cardPoints[right];

            if (right - left + 1 > windowSize) {
                windowSum -= cardPoints[left];
                left++;
            }

            if (right - left + 1 == windowSize) {
                minSum = Math.min(minSum, windowSum);
            }
        }

        return total - minSum;
    }
}


/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Example
[1,2,3,4,5,6,1], k=3

Remove min subarray of size 4 → [1,2,3,4]
Remaining = 5+6+1 = 12
        🔥 Interview Summary
Problem	Pattern
Longest Substring	Sliding Window + Hash
Max Cards	Sliding Window (Reverse Thinking)
💡 Key Takeaways
Longest Substring
Expand → shrink on duplicate
Max Cards
Pick k from ends = remove (n-k) from middle
⚡ Sliding Window Patterns
Type	Example
Variable window	Longest substring
Fixed window	Max cards
AtMost / Exactly	K distinct

 */