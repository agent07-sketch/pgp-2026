/*Problem

        Find all combinations:

        Exactly k numbers
        Sum = n
        Numbers from 1 → 9
        No repetition

        Example:

        Input: k = 3, n = 7
        Output: [[1,2,4]]
        🔑 Key Idea — Backtracking + Pruning

        Conditions:

        1. curr.size() == k
        2. sum == target
        🚀 Java Solution

 */


import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, k, n);
        return result;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int start, int k, int remaining) {

        if (curr.size() == k && remaining == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (curr.size() > k || remaining < 0)
            return;

        for (int i = start; i <= 9; i++) {

            curr.add(i);
            backtrack(res, curr, i + 1, k, remaining - i);
            curr.remove(curr.size() - 1);
        }
    }
}

/*
⚡ Pruning Insight
remaining < 0 → stop
size > k → stop
        Complexity
O(2^9) ≈ constant (small constraints)
🔥 Interview Summary
Problem	Pattern
Combinations	Backtracking
Combination Sum III	Backtracking + Pruning
💡 Key Takeaways
Combinations
start → i+1 (avoid reuse)
Combination Sum III
limit numbers to 1–9
track remaining sum
⚡ Backtracking Template (IMPORTANT)
void backtrack(...) {

    if (base case) {
        add result
        return;
    }

    for (choices) {

        make choice
        backtrack(...)
        undo choice
    }
}

 */