/*Problem

Find:

Sum of all submatrices
🔑 Key Idea — 2D Contribution

Each element contributes to multiple submatrices.

For element at (i, j):

Contribution =
mat[i][j] *
        (i + 1) * (j + 1) *
        (n - i) * (m - j)
        🧠 Why?

Choices:

Top-left corner → (i+1)*(j+1)
Bottom-right corner → (n-i)*(m-j)

 */


class Solution {
    public long sumOfSubmatrices(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                long topLeft = (long)(i + 1) * (j + 1);
                long bottomRight = (long)(n - i) * (m - j);

                sum += mat[i][j] * topLeft * bottomRight;
            }
        }

        return sum;
    }
}

/*
Complexity
Time  : O(n * m)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Sum of Subarrays	Contribution Technique
Sum of Submatrices	2D Contribution
💡 Golden Insight (VERY IMPORTANT)

Instead of:

Generate all subarrays/submatrices ❌

Think:

How many times each element contributes ✅
        🧠 Formula Memory Trick
1D
        (i+1)*(n-i)
        2D
        (i+1)*(j+1)*(n-i)*(m-j)
        🚀 You Just Learned a Powerful Pattern

This same idea is used in:

Sum of subarrays
Sum of submatrices
Sum of absolute differences
Range sum problems

 */