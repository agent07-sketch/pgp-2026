/*Problem

        Design a data structure supporting:

        addNum(num)
        findMedian()

        Example:

        addNum(1)
        addNum(2)
        findMedian() -> 1.5

        addNum(3)
        findMedian() -> 2
        🔑 Key Idea — Two Heaps

        Maintain:

        Max Heap (left half)
        Stores smaller numbers
        Min Heap (right half)
        Stores larger numbers

        Invariant:

        1. maxHeap.size() >= minHeap.size()
        2. size difference <= 1
        🚀 Java Solution

 */





import java.util.*;

class MedianFinder {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}




/*
Complexity
addNum()     -> O(log n)
findMedian() -> O(1)
Space        -> O(n)
🧠 Why it works

Example:

Numbers:
        1 2 3 4 5

MaxHeap: 3 2 1
MinHeap: 4 5

Median = 3

 */