/*Problem

Matrix is:

Sorted row-wise (left → right)
Sorted column-wise (top → bottom)

Find if target exists.

        🔑 Optimal Idea — Top Right Start

Start from top-right corner:

        if current > target → move LEFT
if current < target → move
*/


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int r = 0;
        int c = cols - 1;

        while (r < rows && c >= 0) {

            if (matrix[r][c] == target)
                return true;

            else if (matrix[r][c] > target)
                c--;   // move left

            else
                r++;   // move down
        }

        return false;
    }
}

/*
Complexity
Time  : O(m + n)
Space : O(1)

 */