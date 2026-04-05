/*Problem

        Array contains numbers:

        1 ≤ nums[i] ≤ n

        Find all elements that appear twice.

        Example:

        Input:  [4,3,2,7,8,2,3,1]
        Output: [2,3]
        🔑 Key Idea — Index Marking (Sign Flip)

        Since values are in range 1..n, map value → index.

        Steps:

        index = abs(nums[i]) - 1
        If already negative → duplicate
        Else → mark negative

 */


import java.util.*;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0)
                ans.add(index + 1);
            else
                nums[index] = -nums[index];
        }

        return ans;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1) (excluding output)
        🧠 Why this works
We use array itself as a hash map
Sign tells whether visited before

 */