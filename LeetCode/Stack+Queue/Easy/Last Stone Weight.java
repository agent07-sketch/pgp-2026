/*Problem

You smash the two heaviest stones:

x ≤ y → result = y - x

Return the final remaining stone (or 0).

Example:

        [2,7,4,1,8,1] → Output: 1
        🔑 Key Idea — Max Heap (Priority Queue)

We always need:

extract 2 largest elements
🚀 Java Solution
 */



import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones)
            maxHeap.add(stone);

        while (maxHeap.size() > 1) {

            int y = maxHeap.poll(); // largest
            int x = maxHeap.poll(); // second largest

            if (y != x)
                maxHeap.add(y - x);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}

/*
Complexity
Time  : O(n log n)
Space : O(n)
🧠 Insight
Repeatedly selecting max → use max heap

 */