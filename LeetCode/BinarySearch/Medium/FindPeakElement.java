/*LeetCode 162 — Find Peak Element

A peak element is an element that is greater than its neighbors.

Example:

Input: [1,2,3,1]
Output: 2

Because:
nums[2] = 3
        3 > 2 and 3 > 1
Key Insight
If:
nums[mid] > nums[mid+1]
Then peak is on left side (including mid).
Else
peak is on right side
This works because array behaves like a mountain slope.

Time Complexity:
O(log n)
*/
class Solution {
    public int findPeakElement(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while(low < high){

            int mid = low + (high - low) / 2;

            if(nums[mid] > nums[mid + 1])
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}

/*
Example
Input: [1,2,1,3,5,6,4]

Possible Output: 5

Because 6 is a peak.
 */