/*Problem

Given binary matrix → find largest rectangle of 1’s

🔑 Key Insight

👉 Convert each row into a histogram

Then solve:
        👉 Largest Rectangle in Histogram (stack problem)

🎯 Strategy

For each row:

Build heights[]
Solve histogram max area
✅ Java Code

 */




class Solution {
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;

        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (char[] row : matrix) {

            // Build histogram
            for (int j = 0; j < cols; j++) {
                if (row[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }

            // Solve histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {

            int h = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {

                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}




/*
🔍 Example Visualization

Matrix:

        1 0 1 0 0
        1 0 1 1 1
        1 1 1 1 1

Histogram evolves like:

        [1,0,1,0,0]
        [2,0,2,1,1]
        [3,1,3,2,2]
        ⚡ Complexity
Task Assign
O(n log n)
Max Rectangle
O(rows * cols)
🚨 Common Mistakes
Task Assign

❌ Not sorting
❌ Greedy without binary search
❌ Wrong pill usage

Max Rectangle

❌ Not resetting heights
❌ Stack width calculation wrong

🧠 Pattern Summary
Problem	Pattern
Max Tasks	Binary Search + Greedy
Max Rectangle	Stack + Histogram

 */