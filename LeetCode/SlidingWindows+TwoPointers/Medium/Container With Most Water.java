/*Problem

        Given heights, pick two lines to form a container with maximum water.

        Example:

Input:  [1,8,6,2,5,4,8,3,7]
Output: 49
        🔑 Key Idea — Two Pointers (Greedy)

Area:

area = min(height[left], height[right]) * (right - left)

        👉 Always move the pointer with smaller height

 */

class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {

            int h = Math.min(height[left], height[right]);
            int w = right - left;

            maxArea = Math.max(maxArea, h * w);

            // move smaller height
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
}


/*
🧠 Why it works
Moving larger height is useless
Area limited by smaller height
        Complexity
Time  : O(n)
Space : O(1)

 */