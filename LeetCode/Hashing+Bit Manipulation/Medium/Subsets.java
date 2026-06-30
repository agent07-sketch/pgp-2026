/*Problem

        Given an array of unique integers, return all possible subsets (power set).

        Example:

        Input: nums = [1,2,3]

        Output:
        [
        [],
        [1],
        [2],
        [3],
        [1,2],
        [1,3],
        [2,3],
        [1,2,3]
        ]
        🔑 Approach 1 — Backtracking (Most Common)

        For every element, we have two choices:

        Include it
        Exclude it

        This naturally forms a recursion tree.

        🚀 Java Solution (Backtracking)

 */





import java.util.*;

class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int index,
                           int[] nums,
                           List<Integer> current,
                           List<List<Integer>> result) {

        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            current.add(nums[i]);

            backtrack(i + 1, nums, current, result);

            current.remove(current.size() - 1);
        }
    }
}




/*
Complexity
Time  : O(n × 2ⁿ)
Space : O(n)
🔑 Approach 2 — Bit Manipulation

Each subset corresponds to one binary number.

        Example:

nums = [1,2,3]

        000 -> []

        001 -> [1]

        010 -> [2]

        011 -> [1,2]

        100 -> [3]

        101 -> [1,3]

        110 -> [2,3]

        111 -> [1,2,3]
Java Solution (Bitmask)
    */



class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;

        for (int mask = 0; mask < (1 << n); mask++) {

            List<Integer> subset = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0)
                    subset.add(nums[i]);
            }

            result.add(subset);
        }

        return result;
    }
}