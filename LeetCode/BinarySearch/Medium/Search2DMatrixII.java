/*
Matrix Property

Rows are sorted left → right

Columns are sorted top → bottom

Example

matrix =
[
 [1, 4, 7,11,15],
 [2, 5, 8,12,19],
 [3, 6, 9,16,22],
 [10,13,14,17,24],
 [18,21,23,26,30]
]

target = 5
Key Idea (Start From Top Right)

Start at top-right corner.

Why?

current > target → move LEFT
current < target → move DOWN

Because:

Left values are smaller

Down values are larger
 */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols - 1;

        while(row < rows && col >= 0){

            if(matrix[row][col] == target)
                return true;

            else if(matrix[row][col] > target)
                col--;

            else
                row++;
        }

        return false;
    }
}