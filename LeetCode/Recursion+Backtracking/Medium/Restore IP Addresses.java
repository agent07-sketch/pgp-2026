/*Problem

        Given a string of digits, return all valid IP addresses.

        Example:

        Input:  "25525511135"
        Output: ["255.255.11.135", "255.255.111.35"]
        🔑 Key Idea — Backtracking with Constraints

        We need:

        Exactly 4 parts
        Each part:
        0 → 255
        no leading zeros (except "0")
        🚀 Java Solution

 */



import java.util.*;

class Solution {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        backtrack(result, s, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(List<String> res, String s, int index, List<String> curr) {

        // 4 parts formed
        if (curr.size() == 4) {
            if (index == s.length()) {
                res.add(String.join(".", curr));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) break;

            String part = s.substring(index, index + len);

            if (isValid(part)) {
                curr.add(part);
                backtrack(res, s, index + len, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isValid(String s) {

        if (s.length() > 1 && s.startsWith("0"))
            return false;

        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
}

/*
Complexity
Time  : O(1) (bounded → max 3^4 possibilities)
Space : O(1)
🧠 Key Insight
At most 4 parts → strong pruning → very efficient

 */