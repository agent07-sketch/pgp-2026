/*Problem

        Given pairs:

        (1,2)
        (3,4)
        (5,9)
        (4,3)
        (9,5)

        Find symmetric pairs:

        (3,4) ↔ (4,3)
        (5,9) ↔ (9,5)
        🔑 Key Idea — HashMap

        For pair (a,b):

        Check whether:

        (b,a)

        already exists.

        If yes → symmetric pair found.

        🚀 Java Solution

 */






import java.util.*;

class Solution {

    public List<int[]> findSymPairs(int[][] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (int[] pair : arr) {

            int first = pair[0];
            int second = pair[1];

            if (map.containsKey(second)
                    && map.get(second) == first) {

                result.add(new int[]{second, first});
            }
            else {
                map.put(first, second);
            }
        }

        return result;
    }
}





/*
Complexity
Time  : O(n)
Space : O(n)
Example

Input:

        [[1,2],[3,4],[5,9],[4,3],[9,5]]

Map evolution:

        1 → 2
        3 → 4
        5 → 9

        (4,3) found because map[3] = 4
        (9,5) found because map[5] = 9
        🔥 Interview Summary
Problem	Pattern
First Unique Character	Frequency Array
Symmetric Pairs	HashMap
💡 Key Takeaways
First Unique Character
Count first
Scan second
Symmetric Pairs
        (a,b) exists?
Check (b,a)
⚡ Pattern Recognition Guide
Frequency Counting

Used in:

First Unique Character
Valid Anagram
Ransom Note
Character Frequency Problems
HashMap Pair Problems

Used in:

Two Sum
Symmetric Pairs
Equal Sum Pairs
Pair Difference

 */