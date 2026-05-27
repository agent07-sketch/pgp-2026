/*Problem
Pick 2 largest stones
Smash them:
If equal → both destroyed
Else → push difference

Repeat until 1 or 0 stone left

🔑 Core Idea

👉 Always need largest elements

→ Use Max Heap

⚡ Approach
Insert all stones into max heap
While size > 1:
pop two largest
push difference if not equal
✅ Java Code

 */





class Solution {

    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {

            int first = maxHeap.poll();
            int second = maxHeap.poll();

            if (first != second) {
                maxHeap.offer(first - second);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}






/*
🔍 Example
stones = [2,7,4,1,8,1]

Step:
        8-7=1 → [4,2,1,1,1]
        4-2=2 → [2,1,1,1]
        2-1=1 → [1,1,1]
        1-1=0 → [1]

Answer = 1
        ⏱ Complexity
Time: O(n log n)
Space: O(n)
🚨 Mistakes

❌ Using min heap instead of max
❌ Forgetting to check equality
❌ Not handling empty heap case

        🧠 Pattern Summary
Problem	Pattern
K Weakest Rows	Heap + Sorting + Binary Search
Last Stone Weight	Max Heap

 */