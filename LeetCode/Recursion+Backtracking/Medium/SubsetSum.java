class Solution {
    public boolean subsetSum(int[] arr, int target) {

        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        // sum 0 always possible
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= target; j++) {

                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[n][target];
    }
}
