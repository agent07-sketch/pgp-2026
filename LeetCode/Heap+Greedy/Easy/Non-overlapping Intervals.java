/*Problem

        Given intervals, remove minimum number so remaining intervals don’t overlap.

        🔑 Core Idea (VERY IMPORTANT)

👉 Instead of removing intervals…
        👉 Think: maximize number of non-overlapping intervals

⚡ Greedy Strategy
Sort by end time
Always pick interval with smallest end
Skip overlapping ones
🎯 Why end time?
Smaller end → leaves more room for future intervals
✅ Java Code

 */





class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < prevEnd) {
                // overlap → remove this
                count++;
            } else {
                // no overlap → take it
                prevEnd = intervals[i][1];
            }
        }

        return count;
    }
}















/*
🔍 Example
[1,2], [2,3], [3,4], [1,3]

Sorted by end:
        [1,2], [1,3], [2,3], [3,4]

Remove [1,3] → answer = 1
        ⏱ Complexity
Time: O(n log n)
Space: O(1)
🚨 Mistakes

❌ Sorting by start instead of end
❌ Not updating prevEnd correctly
❌ Confusing removal vs selection

 */