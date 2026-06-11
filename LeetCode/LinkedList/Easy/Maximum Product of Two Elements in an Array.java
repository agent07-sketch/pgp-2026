/*Problem

Find:

        (nums[i] - 1) * (nums[j] - 1)

where i != j, and maximize the result.

Example:

nums = [3,4,5,2]

Output = 12

        (5-1)*(4-1)=4*3=12
        🔑 Key Idea

We only need the largest and second largest elements.

🚀 Optimal Java Solution (One Pass)
 */





class Solution {
    public int maxProduct(int[] nums) {

        int firstMax = 0;
        int secondMax = 0;

        for (int num : nums) {

            if (num > firstMax) {
                secondMax = firstMax;
                firstMax = num;
            }
            else if (num > secondMax) {
                secondMax = num;
            }
        }

        return (firstMax - 1) * (secondMax - 1);
    }
}






/*
Complexity
Time  : O(n)
Space : O(1)
Alternative

Sort and take last two:

        Arrays.sort(nums);
return (nums[n-1]-1)*(nums[n-2]-1);

Complexity:

O(n log n)
🔥 Interview Summary
Problem	Pattern
Reverse String	Two Pointers
Maximum Product of Two Elements	Find Top Two Maximums
💡 Key Takeaways
Reverse String
left++, right--
Maximum Product
Track largest and second largest

 */