/*Problem

Generate all valid parentheses combinations for n pairs.

Example:

n = 3
Output:
        ["((()))","(()())","(())()","()(())","()()()"]
        🔑 Key Idea — Backtracking

Rules:

        1. Add '(' if open < n
        2. Add ')' if close < open

 */

        import java.util.*;

        class Solution {

        public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
        }

        private void backtrack(List<String> res, String curr,
        int open, int close, int n) {

        if (curr.length() == 2 * n) {
        res.add(curr);
        return;
        }

        if (open < n)
        backtrack(res, curr + "(", open + 1, close, n);

        if (close < open)
        backtrack(res, curr + ")", open, close + 1, n);
        }
        }

        /*
        Complexity
        Time  : O(2^n) (approx Catalan)
        Space : O(n)
        🧠 Why this works
        We only build VALID sequences
        → prune invalid paths early
        🔥 Interview Summary
        Problem	Pattern
        Pow(x, n)	Binary Exponentiation
        Generate Parentheses	Backtracking
        💡 Key Takeaways
        Pow(x, n)
        Divide exponent by 2
        Square base
        Generate Parentheses
        open < n
        close < open
        ⚡ Interview Insight
        Backtracking Problems

        If problem says:

        generate all combinations

        👉 Think:

        Backtracking

         */