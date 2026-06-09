/*Problem

        Given buildings:

        [left, right, height]

        Return skyline key points.

        Example:

        [[2,9,10],[3,7,15],[5,12,12]]

        Output:
        [[2,10],[3,15],[7,12],[12,0]]
        🔑 Key Idea — Sweep Line + Max Heap

        Convert each building into events:

        Start event
        (x, -height)
        End event
        (x, +height)

        Negative height ensures start events are processed first.

        🚀 Java Solution

 */






import java.util.*;

class Solution {

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        events.sort((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];

            return a[1] - b[1];
        });

        TreeMap<Integer, Integer> heights = new TreeMap<>();
        heights.put(0, 1);

        int prevMax = 0;

        List<List<Integer>> result = new ArrayList<>();

        for (int[] event : events) {

            int x = event[0];
            int h = event[1];

            if (h < 0) {
                h = -h;
                heights.put(h, heights.getOrDefault(h, 0) + 1);
            } else {

                heights.put(h, heights.get(h) - 1);

                if (heights.get(h) == 0)
                    heights.remove(h);
            }

            int currMax = heights.lastKey();

            if (currMax != prevMax) {

                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}




/*
Complexity
Time  : O(n log n)
Space : O(n)
Why TreeMap instead of Heap?

A heap cannot efficiently remove arbitrary heights.

TreeMap gives:

insert  -> O(log n)
remove  -> O(log n)
maxHeight -> O(log n)
🔥 Interview Summary
Problem	Pattern
Merge K Sorted Lists	Heap
Skyline Problem	Sweep Line + TreeMap
💡 Key Takeaways
Merge K Lists
Many sorted sources
→ Min Heap
Skyline
Events + Active Heights
→ Sweep Line

 */