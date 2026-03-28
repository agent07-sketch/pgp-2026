/*Problem

Rearrange array such that:

arr[0] >= arr[1] <= arr[2] >= arr[3] ...

Example:

Input:  [1,2,3,4,5]
Output: [2,1,4,3,5]
        🔑 Approach 1 (Optimal — No Sorting)

We swap adjacent pairs:

        (0,1), (2,3), (4,5)...

This guarantees wave form.
*/


class Solution {
    public void convertToWave(int[] arr, int n) {

        for (int i = 0; i < n - 1; i += 2) {

            // swap arr[i] and arr[i+1]
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
    }
}

/*
Why this works
After swap:
arr[0] >= arr[1]
arr[2] >= arr[3]
        ...

No need to check conditions if array is already arbitrary.

Complexity
O(n)
⚠ Alternative (Safer for unsorted constraints)

If strict ordering required:

Sort + swap pairs
 */