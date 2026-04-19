/*Problem

Given a string, check if brackets are properly balanced.

Example:

Input:  "(coder)(byte))"
Output: false

Input:  "(c(oder)) b(yte)"
Output: true
        🔑 Key Idea — Stack / Counter

We only care about ( and ).
 */

        //🚀 Approach 1 — Counter (Optimal)
class Solution {
    public boolean bracketMatch(String s) {

        int count = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                count--;

                if (count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}


/*🧠 Why this works
count < 0 → extra closing bracket
count != 0 → unmatched opening
⚡ Alternative — Stack
 */


class Solution {
    public boolean bracketMatch(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(')
                stack.push(c);

            else if (c == ')') {

                if (stack.isEmpty())
                    return false;

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}

/*
Complexity
Time  : O(n)
Space : O(1) or O(n)

 */