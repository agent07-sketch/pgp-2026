/*Problem

        Return all unique permutations of an array (may contain duplicates).

        Example:

        Input:  [1,1,2]
        Output:
        [ [1,1,2], [1,2,1], [2,1,1] ]
        🔑 Key Idea — Backtracking + Duplicate Handling

        Steps:

        Sort array
        Use used[] array
        Skip duplicates:
        if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) → skip

 */


import java.util.*;

class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(result, new ArrayList<>(), nums, used);

        return result;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int[] nums, boolean[] used) {

        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) continue;

            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;

            used[i] = true;
            curr.add(nums[i]);

            backtrack(res, curr, nums, used);

            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}



/*
Complexity
Time  : O(n! * n)
Space : O(n)
🧠 Why duplicate check works
Only allow first occurrence unless previous duplicate is used

 */