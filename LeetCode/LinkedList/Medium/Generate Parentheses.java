/*Problem

Generate all valid combinations of n pairs of parentheses.

        Example:

n = 3

Output:
        [
        "((()))",
        "(()())",
        "(())()",
        "()(())",
        "()()()"
        ]
        🔑 Key Idea — Backtracking

Rules:

Add '('

Only if:

        open < n
        Add ')'

        Only if:

        close < open

        This guarantees every generated string is valid.

        🚀 Java Solution

 */

        import java.util.*;

        class Solution {

        public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(result, "", 0, 0, n);

        return result;
        }

        private void backtrack(List<String> result,
        String curr,
        int open,
        int close,
        int n) {

        if (curr.length() == 2 * n) {
        result.add(curr);
        return;
        }

        if (open < n)
        backtrack(result, curr + "(", open + 1, close, n);

        if (close < open)
        backtrack(result, curr + ")", open, close + 1, n);
        }
        }





        /*
        Complexity
        Time  : O(Catalan(n))
        Space : O(n)
        🧠 Pattern Recognition
        Generate all valid combinations
        → Backtracking

         */