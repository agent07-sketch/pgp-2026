/*
Kth Smallest Element in a Sorted Matrix, the optimal interview approach is Binary Search on Answer (not heap). This is a very common pattern in DSA interviews.

Matrix properties:

Each row is sorted

Each column is sorted

Example:

matrix =
        [
        [1, 5, 9],
        [10,11,13],
        [12,13,15]
        ]

k = 8

Sorted order:

        1 5 9 10 11 12 13 13 15

Answer:

        13
Key Idea (Binary Search on Value)

Instead of searching indices, we search the value range.

Range:

low  = matrix[0][0]
high = matrix[n-1][n-1]

For each mid, count how many numbers ≤ mid.

        If:

count < k → move right
count ≥ k → move left
Java Solution
 */

class Solution {

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];

        while (low < high) {

            int mid = low + (high - low) / 2;

            int count = countLessEqual(matrix, mid);

            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    private int countLessEqual(int[][] matrix, int target) {

        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < n) {

            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }

        return count;
    }
}

/*
Complexity
Binary Search Range = log(max-min)
Counting step       = O(n)

Total = O(n log(max-min))

Space:

O(1)
Interview Insight

Many candidates first think of Min Heap:

Heap solution:

O(k log n)

But interviewers prefer Binary Search on Answer because it uses the matrix sorted property.

Pattern This Problem Belongs To

This is the same pattern as:

Problem	Pattern
Kth Smallest Element in Sorted Matrix	Binary Search on Answer
Koko Eating Bananas	Binary Search on Answer
Ship Packages Within D Days	Binary Search on Answer
Aggressive Cows	Binary Search on Answer
 */