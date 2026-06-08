/*Problem

        Each worker has:

        quality[i]
        wage[i]

        Need exactly k workers.

        Rule:

        All selected workers
        must be paid using the same ratio

        Find minimum total cost.

        🔑 Key Insight

        For each worker:

        ratio = wage / quality

        If a worker defines the ratio:

        Total Cost =
        (sum of qualities) * ratio

        We want:

        minimum quality sum
        for current ratio
        🚀 Approach
        Sort workers by ratio.
        Maintain largest qualities using max heap.
        Keep only K workers.
        🚀 Java Solution

 */




import java.util.*;

class Solution {

    class Worker {
        int quality;
        int wage;
        double ratio;

        Worker(int q, int w) {
            quality = q;
            wage = w;
            ratio = (double) w / q;
        }
    }

    public double mincostToHireWorkers(int[] quality,
                                       int[] wage,
                                       int k) {

        int n = quality.length;

        Worker[] workers = new Worker[n];

        for (int i = 0; i < n; i++) {
            workers[i] =
                    new Worker(quality[i], wage[i]);
        }

        Arrays.sort(workers,
                (a, b) -> Double.compare(a.ratio, b.ratio));

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        int qualitySum = 0;
        double answer = Double.MAX_VALUE;

        for (Worker worker : workers) {

            qualitySum += worker.quality;
            maxHeap.offer(worker.quality);

            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }

            if (maxHeap.size() == k) {

                answer = Math.min(
                        answer,
                        qualitySum * worker.ratio
                );
            }
        }

        return answer;
    }
}




/*
Complexity
Sorting      : O(n log n)
Heap Ops     : O(n log k)

Total        : O(n log n)
Space        : O(k)
🔥 Interview Summary
Problem	Pattern
Find Median from Data Stream	Two Heaps
Minimum Cost to Hire K Workers	Greedy + Heap
💡 Key Takeaways
Median Data Stream
Left half → Max Heap
Right half → Min Heap
Hire K Workers
Sort by ratio
Keep smallest K qualities
⚡ Heap Pattern Recognition
Two Heaps

Used in:

Median from Data Stream
Running Median
Sliding Window Median
Heap + Greedy

Used in:

Hire K Workers
        IPO
Maximum Performance of a Team
Course Schedule III
Interview Signals
Signal	Pattern
Running median	Two heaps
Top K	Heap
Minimize cost with ratio/ranking	Greedy + Heap
Stream processing	Heap

 */