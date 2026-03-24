/*Problem

        For each element, count how many elements to its right are smaller.

        Example:

        Input:  [5,2,6,1]
        Output: [2,1,1,0]
        🔑 Key Idea — Merge Sort + Index Tracking

        While merging:

        If left[i] > right[j]

        Then all elements from j → right are smaller than left[i]

        So:

        count[index[i]] += (right - j + 1)
 */

import java.util.*;

class Solution {

    private int[] count;
    private int[] index;

    public List<Integer> countSmaller(int[] nums) {

        int n = nums.length;
        count = new int[n];
        index = new int[n];

        for (int i = 0; i < n; i++)
            index[i] = i;

        mergeSort(nums, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : count)
            result.add(c);

        return result;
    }

    private void mergeSort(int[] nums, int left, int right) {

        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {

        List<Integer> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {

            if (nums[index[i]] <= nums[index[j]]) {
                temp.add(index[j++]);
            } else {
                count[index[i]] += (right - j + 1);
                temp.add(index[i++]);
            }
        }

        while (i <= mid)
            temp.add(index[i++]);

        while (j <= right)
            temp.add(index[j++]);

        for (int k = left; k <= right; k++) {
            index[k] = temp.get(k - left);
        }
    }
}
/*
Complexity
Time  : O(n log n)
Space : O(n)
 */