/*Problem

        Find all DNA sequences of length 10 that occur more than once.

        Example:

        Input:
        "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

        Output:
        ["AAAAACCCCC","CCCCCAAAAA"]
        🔑 Key Idea — Sliding Window + HashSet

        Every substring length is fixed:

        10 characters

        Maintain:

        seen → first occurrence
        result → repeated sequences
        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public List<String> findRepeatedDnaSequences(String s) {

        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {

            String curr = s.substring(i, i + 10);

            if (!seen.add(curr)) {
                repeated.add(curr);
            }
        }

        return new ArrayList<>(repeated);
    }
}





/*
Complexity
Time  : O(n)
Space : O(n)
Optimized Version

Can use:

Rolling Hash + Bit Encoding

to achieve lower memory usage.

🔥 Interview Summary
Problem	Pattern
Equal Sum Non-overlapping Pairs	HashMap
Repeated DNA Sequences	Sliding Window + HashSet
💡 Key Takeaways
Equal Pair Sum
Store previous pair for every sum
Repeated DNA
Fixed window size = 10
Use HashSet

 */