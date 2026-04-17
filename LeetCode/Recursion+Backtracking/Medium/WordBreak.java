/*Problem

        Check if string can be segmented into words from dictionary.

        Example:

        Input: s = "leetcode"
        dict = ["leet","code"]
        Output: true
        🔑 Key Idea — DP

        Define:

        dp[i] = can we form substring s[0..i)
        🚀 Java Solution

 */


import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {

            for (int j = 0; j < i; j++) {

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
Complexity
Time  : O(n^2)
Space : O(n)
🧠 Why this works
Try all partitions
If left part valid + right word exists → valid
⚡ Optimization Idea
Use Trie (advanced)
Limit substring checks
🔥 Interview Summary
Problem	Pattern
Count Good Numbers	Math + Fast Power
Word Break	DP (String Partition)
💡 Key Takeaways
Count Good Numbers
Even → 5 choices
Odd → 4 choices
Use fast power
Word Break
dp[i] = true if any valid split exists

 */