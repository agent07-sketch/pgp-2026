/*Problem
Matrix has 1s (soldiers) then 0s (civilians) in each row
A row is weaker if:
fewer soldiers
OR same soldiers but smaller index

👉 Return indices of k weakest rows

🔑 Core Idea

Each row strength = count of 1s

So problem becomes:

Sort rows by (number of 1s, row index)
⚡ Optimization Insight

Counting 1s can be done using Binary Search (since sorted row)

✅ Java Code

 */





class Solution {

    public int[] kWeakestRows(int[][] mat, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] == b[0]) return a[1] - b[1]; // index
                    return a[0] - b[0]; // soldiers
                }
        );

        for (int i = 0; i < mat.length; i++) {
            int soldiers = countOnes(mat[i]);
            pq.offer(new int[]{soldiers, i});
        }

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[1];
        }

        return res;
    }

    // Binary search to count 1s
    private int countOnes(int[] row) {
        int low = 0, high = row.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (row[mid] == 1)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return low; // number of 1s
    }
}





/*
⏱ Complexity
Time: O(m log n + m log m)
Space: O(m)
🚨 Mistakes

❌ Counting linearly instead of binary search
❌ Wrong comparator order
❌ Ignoring tie-breaking (index)

 */