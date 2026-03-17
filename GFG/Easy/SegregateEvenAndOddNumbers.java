/*
Problem

Rearrange array such that:

All even numbers come first

All odd numbers come after

🔑 Optimal Approach — Two Pointers

left → move forward

right → move backward

swap when needed
 */

class Solution {

    public void segregateEvenOdd(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            // move left if even
            while (left < right && arr[left] % 2 == 0)
                left++;

            // move right if odd
            while (left < right && arr[right] % 2 != 0)
                right--;

            // swap
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }
    }
}