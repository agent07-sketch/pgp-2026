/*Problem

        Each element is at most k positions away from its correct position.

        Example:

        Input:
        arr = [6,5,3,2,8,10,9]
        k = 3

        Output:
        [2,3,5,6,8,9,10]
        🔑 Key Idea — Min Heap

        Since an element can move at most k places:

        Correct element must lie within next (k+1) elements
        🚀 Java Solution

 */




import java.util.*;

class Solution {

    ArrayList<Integer> nearlySorted(int arr[], int k) {

        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {

            minHeap.offer(num);

            if (minHeap.size() > k) {
                result.add(minHeap.poll());
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }
}



/*
Alternative (Index-based)
for (int i = 0; i <= k; i++)
        pq.offer(arr[i]);

for (int i = k + 1; i < arr.length; i++) {
arr[index++] = pq.poll();
    pq.offer(arr[i]);
}
Complexity
Time  : O(n log k)
Space : O(k)
🔥 Interview Summary
Problem	Pattern
Top K Frequent Words	HashMap + Heap
Nearly Sorted Array	Min Heap
💡 Key Takeaways
Top K Frequent Words
Frequency count + Heap of size K
Nearly Sorted Array
Element lies within k distance
→ Heap of size k+1
        ⚡ Heap Recognition Guide
Signal in Problem	Pattern
Top K elements	Heap
Kth largest/smallest	Heap
Frequency ranking	HashMap + Heap
Nearly sorted array	Min Heap
Merge K lists	Heap

 */