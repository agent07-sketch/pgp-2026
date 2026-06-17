/*
Problem

        Given a string of digits, return all possible valid IP addresses.

        Example:

        Input:
        "25525511135"

        Output:
        [
        "255.255.11.135",
        "255.255.111.35"
        ]
        🔑 Key Idea — Backtracking

        An IP address has:

        4 parts
        Each part:
        0 to 255
        No leading zeros

        Try all segment lengths:

        1 digit
        2 digits
        3 digits
        🚀 Java Solution
 */







import java.util.*;

class Solution {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(String s,
                           int index,
                           List<String> path,
                           List<String> result) {

        if (path.size() == 4) {

            if (index == s.length()) {
                result.add(String.join(".", path));
            }

            return;
        }

        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length())
                break;

            String part = s.substring(index, index + len);

            if (isValid(part)) {

                path.add(part);

                backtrack(s,
                        index + len,
                        path,
                        result);

                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isValid(String part) {

        if (part.length() > 1 && part.charAt(0) == '0')
            return false;

        int num = Integer.parseInt(part);

        return num >= 0 && num <= 255;
    }
}







/*
Complexity
Time  : O(3⁴) ≈ O(1)
Space : O(1)

(Bounded because there are only 4 segments.)

        🔥 Interview Summary
Problem	Pattern
Minimum Limit of Balls in a Bag	Binary Search on Answer
Restore IP Addresses	Backtracking
💡 Key Takeaways
Balls in a Bag
Search answer space,
not array indices
Restore IP
Generate all possibilities
+
Prune invalid ones
⚡ Pattern Recognition Guide
Binary Search on Answer

Used in:

Koko Eating Bananas
Aggressive Cows
Allocate Pages
Split Array Largest Sum
Minimum Limit of Balls in a Bag
        Backtracking

Used in:

Restore IP Addresses
Generate Parentheses
N Queens
Combination Sum

⭐ Both are high-frequency interview problems:

Minimum Limit of Balls in a Bag → Binary Search
Restore IP Addresses → Backtracking

 */