/*Problem

        Find the largest rectangle area in a histogram.

        Example:

        heights = [2,1,5,6,2,3] → Output = 10
        🔑 Key Idea — Monotonic Stack

        For each bar:
        👉 Find Next Smaller Element (NSE) on left & right

        Then:

        width = right[i] - left[i] - 1
        area  = height[i] * width
        🚀 Optimal Java Solution (Single Pass Stack)

 */




import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > h) {

                int height = heights[stack.pop()];

                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();

                int width = right - left - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}



/*
🧠 Why this works
Stack stores increasing heights
When a smaller height comes → compute area
Complexity
Time  : O(n)
Space : O(n)
⚡ Key Insight
Each element pushed & popped once → O(n)

 */