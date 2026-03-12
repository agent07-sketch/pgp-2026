/*Array Duplicates – Find Elements Appearing Twice

        (GFG Problem)

        Problem

        Array contains numbers from:

        1 → n

        Each element appears at most twice.

        Return elements that appear twice.

        Example:

        Input:  [2,3,1,2,3]

        Output: [2,3]
        Optimal Idea (Index Marking)

        Since numbers range 1..n, use indices.

        Steps:

        Take absolute value

        Go to index (value - 1)

        If already negative → duplicate found

        Otherwise mark negative

        Java Solution

 */
import java.util.*;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){

            int index = Math.abs(nums[i]) - 1;

            if(nums[index] < 0)
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
Space : O(1)  (excluding output)
 */