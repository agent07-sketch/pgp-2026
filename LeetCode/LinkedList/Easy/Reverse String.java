/*Problem

Reverse a character array in-place.

        Example:

Input:  ['h','e','l','l','o']
Output: ['o','l','l','e','h']
        🔑 Key Idea — Two Pointers
One pointer at the beginning
One pointer at the end
Swap and move inward
🚀 Java Solution
 */




class Solution {
    public void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {

            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }
}






/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Pattern Recognition
Reverse array/string in-place
→ Two Pointers

 */