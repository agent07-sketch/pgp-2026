/*Problem

        Find the length of the longest consecutive sequence.

        Example:

        Input:
        [100,4,200,1,3,2]

        Output:
        4

        Sequence:
        1 2 3 4
        🔑 Key Idea — HashSet

        Store every element in a HashSet.

        Only start counting if the current number is the start of a sequence:

        num - 1 does NOT exist

        Then extend forward.

        🚀 Java Solution

 */







import java.util.*;

class Solution {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums)
            set.add(num);

        int longest = 0;

        for (int num : set) {

            // Start of sequence
            if (!set.contains(num - 1)) {

                int current = num;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}









/*
Complexity
Time  : O(n)
Space : O(n)
🧠 Why Check num - 1?

Without it:

        1 → 2 → 3 → 4

would be counted four times.

Checking num - 1 ensures we only start from the beginning of a sequence.

        🔥 Interview Summary
Problem	Pattern
Largest Subarray of 0's and 1's	Prefix Sum + HashMap
Longest Consecutive Sequence	HashSet
💡 Key Takeaways
Largest Subarray of 0's and 1's
Replace:
        0 → -1

Then solve:
Largest Zero Sum Subarray
Longest Consecutive Sequence
Only start counting
when num-1 doesn't exist

 */