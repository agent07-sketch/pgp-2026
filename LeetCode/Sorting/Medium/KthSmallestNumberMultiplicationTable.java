/*
Problem

You have an m x n multiplication table:

mat[i][j] = i * j   (1-based)

Find the k-th smallest number.

🔑 Key Idea — Binary Search on Answer

Search in range:

low = 1
high = m * n

For a number mid, count how many values ≤ mid.

In each row:

count += min(mid / i, n)
*/

class Solution {
    public int findKthNumber(int m, int n, int k) {

        int low = 1;
        int high = m * n;

        while (low < high) {

            int mid = low + (high - low) / 2;

            int count = countLessEqual(mid, m, n);

            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    private int countLessEqual(int mid, int m, int n) {

        int count = 0;

        for (int i = 1; i <= m; i++) {
            count += Math.min(mid / i, n);
        }

        return count;
    }
}

/*
Complexity
Time  : O(m * log(m*n))
Space : O(1)

 */