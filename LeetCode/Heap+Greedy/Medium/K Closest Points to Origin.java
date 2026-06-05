/*Problem

        Return the k points closest to (0,0).

        Example:

        points = [[1,3],[-2,2]]
        k = 1

        Output = [[-2,2]]
        🔑 Key Idea — Max Heap of Size K

        Distance:

        d² = x² + y²

        No need for square root.

        🚀 Java Solution (Heap)

 */





import java.util.*;

class Solution {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(
                        (a, b) ->
                                (b[0] * b[0] + b[1] * b[1]) -
                                        (a[0] * a[0] + a[1] * a[1])
                );

        for (int[] point : points) {

            pq.offer(point);

            if (pq.size() > k)
                pq.poll();
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}




/*
Alternative
QuickSelect → O(n) average
Heap → O(n log k) (most common interview solution)
Complexity
Time  : O(n log k)
Space : O(k)
🧠 Insight
Need top K closest
→ maintain K best candidates
using max heap
🔥 Interview Summary
Problem	Pattern
Minimum Limit of Balls in a Bag	Binary Search on Answer
K Closest Points to Origin	Heap / Top-K
💡 Key Takeaways
Balls in a Bag
Search answer space,
not array indices
K Closest Points
Top K problem
→ Heap
⚡ Must-Remember Patterns
Binary Search on Answer

Used in:

Allocate Pages
Aggressive Cows
Koko Eating Bananas
Split Array Largest Sum
Minimum Limit of Balls in a Bag

Template:

        if (isPossible(mid))
high = mid - 1;
        else
low = mid + 1;
Top-K Heap Problems

Used in:

K Closest Points
Kth Largest Element
Top K Frequent Elements
Merge K Sorted Lists

Template:

Maintain heap of size K
Remove worst element

These are both high-frequency interview patterns:

Binary Search on Answer ⭐
Top-K Heap ⭐

Make sure you can recognize them quickly in interviews.

 */