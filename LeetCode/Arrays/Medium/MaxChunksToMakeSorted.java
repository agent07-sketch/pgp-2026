/*Problem

Split array into maximum chunks such that sorting each chunk individually results in the entire array sorted.

        Example:

Input:  [1,0,2,3,4]
Output: 4
        🔑 Key Insight

If at index i:

max value till i == i

Then we can make a chunk.

Why?

Because all elements before i are ≤ i, so they will be correctly placed after sorting.
 */


class Solution {
    public int maxChunksToSorted(int[] arr) {

        int max = 0;
        int chunks = 0;

        for (int i = 0; i < arr.length; i++) {

            max = Math.max(max, arr[i]);

            if (max == i)
                chunks++;
        }

        return chunks;
    }
}

/*
Example Walkthrough
arr = [1,0,2,3,4]

i=0 → max=1 ❌
i=1 → max=1 == 1 ✅ chunk++
i=2 → max=2 == 2 ✅
i=3 → max=3 == 3 ✅
i=4 → max=4 == 4 ✅

Total chunks = 4
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Valid Anagram	Frequency Array
Max Chunks	Greedy + Prefix Max
💡 Key Takeaways
Valid Anagram
Use freq array → fastest
Max Chunks
maxTillNow == index → split

 */