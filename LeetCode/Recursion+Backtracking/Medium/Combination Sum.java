/*Problem

        Find all combinations:

        Sum = target
        Elements can be reused
        Order doesn't matter

        Example:

        Input: candidates = [2,3,6,7], target = 7
        Output: [[2,2,3],[7]]
        🔑 Key Idea — Backtracking (Reuse Allowed)

        Key difference:

        Use same index again → i (not i+1)
        🚀 Java Solution

 */


import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int[] arr, int target, int start) {

        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (target < 0)
            return;

        for (int i = start; i < arr.length; i++) {

            curr.add(arr[i]);

            // reuse allowed → i (not i+1)
            backtrack(res, curr, arr, target - arr[i], i);

            curr.remove(curr.size() - 1);
        }
    }
}


/*
Complexity
Time  : exponential
Space : O(n)
🔥 Interview Summary
Problem	Pattern
Power Set	Backtracking
Combination Sum	Backtracking + Reuse
💡 Key Takeaways
Power Set
At each index → include or exclude
Combination Sum
Reuse allowed → stay at same index
No reuse → move to i+1
        ⚡ Backtracking Pattern Cheat
Problem	Move
Subsets	i + 1
Combinations	i + 1
Combination Sum	i
Permutations	used[]

 */