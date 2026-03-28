/*Problem

Find index where:

sum(left) == sum(right)

Example:

Input:  [1,3,5,2,2]
Output: 2  (index of 5)
        ---Optimal Idea (Prefix Sum Trick)

Instead of computing left/right every time:

totalSum = sum of array
        leftSum = 0

For each i:
rightSum = totalSum - leftSum - arr[i]
 */


class Solution {
    public int equilibriumPoint(int[] arr) {

        int totalSum = 0;

        for (int num : arr)
            totalSum += num;

        int leftSum = 0;

        for (int i = 0; i < arr.length; i++) {

            int rightSum = totalSum - leftSum - arr[i];

            if (leftSum == rightSum)
                return i;

            leftSum += arr[i];
        }

        return -1;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
---Interview Summary
Problem	Pattern
Wave Array	Greedy / Swapping
Equilibrium Point	Prefix Sum
---Key Takeaways
Wave Array

Just swap adjacent elements

Or sort + swap for guaranteed pattern

Equilibrium Point

Golden formula:

rightSum = totalSum - leftSum - current

 */