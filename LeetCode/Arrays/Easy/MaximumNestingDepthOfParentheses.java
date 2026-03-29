/*Problem

Find the maximum depth of nested parentheses.

        Example:

Input: "(1+(2*3)+((8)/4))+1"
Output: 3
        🔑 Idea — Counter

        ( → increase depth

        ) → decrease depth

Track maximum
        */



class Solution {
    public int maxDepth(String s) {

        int depth = 0;
        int maxDepth = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            }
            else if (c == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

Interview Summary
Problem	Pattern
Missing in Array	Math / XOR
Max Nesting Depth	Stack Logic (Counter Optimization)

Key Insights
Missing Number

XOR is interview favorite

Avoids overflow issues

Parentheses Depth

You don’t need stack

Just counter is enough

Quick Revision Trick
Missing number → XOR or sum
Parentheses → increment / decrement counter
        */