/*Problem

        Given intervals, remove the minimum number of intervals so that the remaining intervals do not overlap.

        Example:

        Input:
        [[1,2],[2,3],[3,4],[1,3]]

        Output:
        1

        Remove [1,3].

        🔑 Key Idea — Greedy by Ending Time

        Sort intervals by their end time.

        Why?

        Choosing the interval that finishes earliest
        leaves maximum room for future intervals.
        🚀 Java Solution

 */




import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < prevEnd) {
                count++; // remove current interval
            } else {
                prevEnd = intervals[i][1];
            }
        }

        return count;
    }
}





/*
Complexity
Time  : O(n log n)
Space : O(1)
🧠 Pattern Recognition
Intervals + maximize number kept
→ Sort by end time (Greedy)

 */