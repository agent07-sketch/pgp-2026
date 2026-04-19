/*Key Idea — DP

        Define:

        dp[i] = can form s[0..i)
        🚀 Java Solution (Optimized)

 */

import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {

            for (int j = i - 1; j >= 0; j--) {

                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

/*
🧠 Example
        s = "applepenapple"
dict = ["apple","pen"]

dp:
        [true, false, ..., true]
Complexity
Time  : O(n^2)
Space : O(n)
🔥 Interview Summary
Problem	Pattern
Bracket Challenge	Stack / Counter
Word Break	DP
💡 Key Takeaways
Bracket Challenge
counter is enough for '(' and ')'
Word Break
dp[i] → check all partitions
⚡ Interview Insight
Bracket Problems

If only:

        () only → use counter
multiple brackets → use stack
Word Break Pattern

Used in:

String segmentation
Sentence formation
Dictionary matching

 */