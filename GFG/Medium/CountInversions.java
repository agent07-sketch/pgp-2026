/*
Problem

Count pairs (i, j) such that:

i < j AND arr[i] > arr[j]

Example:

Input:  [2, 4, 1, 3, 5]
Output: 3

Pairs: (2,1), (4,1), (4,3)
🔑 Optimal Approach — Merge Sort

While merging:

If left[i] > right[j]

Then all remaining elements in left are greater → count += (mid - i + 1)
 */

class Solution {

    public static long inversionCount(long arr[], int n) {
        return mergeSort(arr, 0, n - 1);
    }

    private static long mergeSort(long[] arr, int left, int right) {

        long count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSort(arr, left, mid);
            count += mergeSort(arr, mid + 1, right);
            count += merge(arr, left, mid, right);
        }

        return count;
    }

    private static long merge(long[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        long[] L = new long[n1];
        long[] R = new long[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        long count = 0;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
                count += (n1 - i);  // KEY STEP
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];

        return count;
    }
}

