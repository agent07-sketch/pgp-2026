/*Problem

        Return all index pairs:

        (i,j)

        such that:

        words[i] + words[j]

        forms a palindrome.

        Example:

        words =
        ["abcd","dcba","lls","s","sssll"]

        Output:

        [[0,1],[1,0],[3,2],[2,4]]
        🔑 Key Idea — HashMap + Prefix/Suffix

        Store:

        word → index

        For every word:

        Split at every position:

        prefix

        suffix

        Cases:

        Case 1

        If prefix is palindrome

        Search:

        reverse(suffix)
        Case 2

        If suffix is palindrome

        Search:

        reverse(prefix)
        🚀 Java Solution

 */






import java.util.*;

class Solution {

    public List<List<Integer>> palindromePairs(String[] words) {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            for (int cut = 0; cut <= word.length(); cut++) {

                String left = word.substring(0, cut);
                String right = word.substring(cut);

                if (isPalindrome(left)) {

                    String rev =
                            new StringBuilder(right).reverse().toString();

                    Integer idx = map.get(rev);

                    if (idx != null && idx != i) {
                        result.add(Arrays.asList(idx, i));
                    }
                }

                if (cut != word.length() &&
                        isPalindrome(right)) {

                    String rev =
                            new StringBuilder(left).reverse().toString();

                    Integer idx = map.get(rev);

                    if (idx != null && idx != i) {
                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            if (s.charAt(l++) != s.charAt(r--))
                return false;
        }

        return true;
    }
}





/*
Complexity

Let:

N = number of words

        L = average length
Time  : O(N × L²)

Space : O(N)
🔥 Interview Summary
Problem	Pattern
Single Number III	Bit Manipulation (XOR)
Palindrome Pairs	HashMap + String Splitting
💡 Key Takeaways
Single Number III
XOR everything

Find rightmost set bit

Partition

XOR again
Palindrome Pairs
Split word

Palindrome check

Reverse lookup
⚡ Pattern Recognition Guide
XOR Problems

Used in:

Single Number I
Single Number II
Single Number III
Missing Number
Find the Duplicate Number (different approach)
HashMap + Strings

Used in:

Palindrome Pairs
Word Break
Two Sum
Group Anagrams
Replace Words
🎯 Interview Tips
If the problem says:
Every number appears twice except...

        ➡️ Think XOR.

If it says:
Find all string pairs

➡️ Think HashMap + Reverse Lookup.

If it involves:
Palindrome after concatenation

➡️ Try splitting each word into prefix and suffix, then use palindrome checks.

 */