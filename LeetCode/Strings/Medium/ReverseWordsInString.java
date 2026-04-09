/*Problem

Reverse the order of words in a string (ignore extra spaces).

Example:

Input:  "  hello   world  "
Output: "world hello"
        🔑 Key Idea
Trim spaces
Split words
Reverse order
🚀 Approach 1 — Built-in (Simple)

 */



class Solution {
    public String reverseWords(String s) {

        String[] words = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);

            if (i != 0)
                sb.append(" ");
        }

        return sb.toString();
    }
}
⚡ Optimal Approach — In-place (Interview Preferred)
class Solution {
    public String reverseWords(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        // 1. reverse whole string
        reverse(arr, 0, n - 1);

        // 2. reverse each word
        reverseWords(arr, n);

        // 3. clean spaces
        return cleanSpaces(arr, n);
    }

    private void reverse(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    private void reverseWords(char[] arr, int n) {

        int i = 0, j = 0;

        while (i < n) {

            while (i < j || (i < n && arr[i] == ' ')) i++;

            while (j < i || (j < n && arr[j] != ' ')) j++;

            reverse(arr, i, j - 1);
        }
    }

    private String cleanSpaces(char[] arr, int n) {

        int i = 0, j = 0;

        while (j < n) {

            while (j < n && arr[j] == ' ') j++;

            while (j < n && arr[j] != ' ')
                arr[i++] = arr[j++];

            while (j < n && arr[j] == ' ') j++;

            if (j < n)
                arr[i++] = ' ';
        }

        return new String(arr, 0, i);
    }
}


/*
Complexity
Time  : O(n)
Space : O(1) (in-place version)

 */