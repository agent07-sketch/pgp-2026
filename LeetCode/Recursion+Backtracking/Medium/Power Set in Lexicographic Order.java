/*Problem

        Generate all subsets (power set) in sorted (lexicographic) order.

        Example:

        Input:  [1,2,3]
        Output:
        []
        [1]
        [1,2]
        [1,2,3]
        [1,3]
        [2]
        [2,3]
        [3]
        🔑 Key Idea — Backtracking + Sorting

        Steps:

        Sort input
        Generate subsets
        Add current subset at every step
        🚀 Java Solution

 */



import java.util.*;

class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        Arrays.sort(nums); // ensures lexicographic order
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), nums, 0);

        return result;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int[] nums, int start) {

        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {

            curr.add(nums[i]);
            backtrack(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}

/*
Complexity
Time  : O(2^n)
Space : O(n)
🧠 Why sorted first?
Sorting ensures subsets come in lexicographic order

 */