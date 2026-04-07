/*Problem

Given sorted array:

        [max, min, 2nd max, 2nd min, ...]

Example:

Input:  [1,2,3,4,5,6]
Output: [6,1,5,2,4,3]
        🔑 Optimal Idea — In-place Encoding

Store two values in one index:

arr[i] += (newValue % maxElement) * maxElement
Java Solution (O(1) space)

 */


class Solution {
    public void rearrange(long arr[], int n) {

        int maxIdx = n - 1;
        int minIdx = 0;

        long maxElem = arr[n - 1] + 1;

        for (int i = 0; i < n; i++) {

            if (i % 2 == 0) {
                arr[i] += (arr[maxIdx] % maxElem) * maxElem;
                maxIdx--;
            } else {
                arr[i] += (arr[minIdx] % maxElem) * maxElem;
                minIdx++;
            }
        }

        // extract values
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / maxElem;
        }
    }
}

/*
🧠 Why this works
Store:
old value + new value * maxElem

Later:

        new value = arr[i] / maxElem
        Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Valid Parentheses Count	Catalan / DP
Rearrange Alternately	In-place Encoding
💡 Key Takeaways
Valid Parentheses
dp[i] = Σ dp[left] * dp[right]
Rearrange Array
Use modulo trick to store 2 values in one index
⚡ Interview Gold
Catalan Problems Appear As:
Parentheses
BST count
Tree structures
Encoding Trick Appears In:
Rearrangement problems
Hash-in-array tricks
 */