/*Problem

        Remove duplicate letters so that:

        Each letter appears once
        Result is smallest lexicographical order

        Example:

        Input:  "bcabc"
        Output: "abc"
        🔑 Key Idea — Monotonic Stack + Greedy

        We want:

        smallest lexicographical + unique chars
        🧠 Strategy
        Use stack to build result
        Track:
        frequency of remaining chars
        visited array
        🚀 Java Solution

 */




import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {

        int[] freq = new int[26];
        boolean[] visited = new boolean[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            int idx = c - 'a';
            freq[idx]--;

            if (visited[idx]) continue;

            while (!stack.isEmpty() &&
                    stack.peek() > c &&
                    freq[stack.peek() - 'a'] > 0) {

                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[idx] = true;
        }

        StringBuilder sb = new StringBuilder();

        for (char c : stack)
            sb.append(c);

        return sb.toString();
    }
}




/*
🧠 Why this works
Remove larger chars if they appear later
→ ensures smallest lexicographical result
Complexity
Time  : O(n)
Space : O(1)

 */