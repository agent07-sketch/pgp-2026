/*Problem

Given start[] and end[] of meetings,
👉 Find maximum number of meetings that can be done in one room (no overlap)

🔑 Core Insight

👉 Same pattern as Non-overlapping Intervals

👉 Always pick meeting with earliest ending time

⚡ Strategy
Create pairs → (start, end)
Sort by end time
Pick first meeting
For next:
If start > lastEnd → take it
✅ Java Code

 */




class Solution {

    static class Meeting {
        int start, end;

        Meeting(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static int maxMeetings(int start[], int end[], int n) {

        Meeting[] meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(start[i], end[i]);
        }

        Arrays.sort(meetings, (a, b) -> a.end - b.end);

        int count = 1;
        int lastEnd = meetings[0].end;

        for (int i = 1; i < n; i++) {

            if (meetings[i].start > lastEnd) {
                count++;
                lastEnd = meetings[i].end;
            }
        }

        return count;
    }
}


/*
🔍 Example
Start: [1,3,0,5,8,5]
End:   [2,4,6,7,9,9]

Sorted by end:
        (1,2), (3,4), (0,6), (5,7), (8,9), (5,9)

Pick:
        (1,2), (3,4), (5,7), (8,9)

Answer = 4
        🚨 Common Mistakes

❌ Sorting by start
❌ Using >= instead of > (depends on problem statement)
        ❌ Forgetting to pick first meeting

 */
