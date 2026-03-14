/*
Given an array of positive integers arr[] of size n, and an integer k. The task is to find the maximum subarray size such that all subarrays of that size have sum less than or equals to k.

Examples :

Input :  arr[] = [1, 2, 3, 4], k = 8.
Output : 2
Explanation: Following are the sum of subarray of size 1 to 4.

Sum of subarrays of size 1: 1, 2, 3, 4.
Sum of subarrays of size 2: 3, 5, 7.
Sum of subarrays of size 3: 6, 9.
Sum of subarrays of size 4: 10.
So, maximum subarray size such that all subarrays of that size have the sum of elements less than 8 is 2.

Input:  arr[] = [1, 2, 10, 4], k = 8.
Output : -1
Explanation: There is an array element (10) with value greater than k, so subarray sum cannot be less than k.

Input :  arr[] = [1, 2, 10, 4], k = 14
Output : 2
*/

// Function to find the maximum subarray
// size such that all subarrays of that
// size have sum less than or equals to k.
import java.util.*;

class GfG {

    // Function to find the maximum subarray
    // size such that all subarrays of that
    // size have sum less than or equals to k.
    static int maxSubarraySize(int[] arr, int k) {
        int n = arr.length;

        // to store the answer
        int ans = -1;

        // generate all possible subarrays
        for (int i = 1; i <= n; i++) {

            // to store the max sum of all
            // possible subarrays of size i
            int maxSum = Integer.MIN_VALUE;

            for (int j = 0; j < n - i + 1; j++) {

                // calculate the sum of the subarray
                int sum = 0;
                for (int l = j; l < j + i; l++) {
                    sum += arr[l];
                }

                // update the max sum
                maxSum = Math.max(maxSum, sum);
            }

            // if the maxSum is less than or equals to k
            if (maxSum <= k) {
                ans = Math.max(ans, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 8;
        System.out.println(maxSubarraySize(arr, k));
    }
}