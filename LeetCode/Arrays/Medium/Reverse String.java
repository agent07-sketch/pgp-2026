/*Problem

Reverse a character array in-place.

        Example:

Input:  ['h','e','l','l','o']
Output: ['o','l','l','e','h']
        🔑 Key Idea — Two Pointers
left → start
right → end
        Swap

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
🔥 Interview Summary
Problem	Pattern
Longest Palindrome	Expand Around Center
Reverse String	Two Pointers
💡 Key Takeaways
Longest Palindrome
Try every center → expand outward
Reverse String
Swap from both ends
⚡ Interview Insight
String Problems Pattern Guide
Pattern	Example
Two Pointers	Reverse string
Expand Center	Palindrome
Sliding Window	Substrings
Hashing	Anagram / Isomorphic

 */