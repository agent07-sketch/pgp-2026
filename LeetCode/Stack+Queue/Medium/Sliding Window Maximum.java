/*Problem

For every window of size k, return the maximum element.

❌ Naive

Check each window → O(n * k)

🔑 Optimal Idea (Deque)

Maintain a deque that stores indices of useful elements in decreasing order.

        👉 Why?

Front = max of current window
Remove smaller elements (they’ll never be useful)
✅ Java Code

 */




public static List<Integer> maxSlidingWindow(int[] nums, int k) {

    Deque<Integer> dq = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {

        // Remove elements out of window
        if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
            dq.pollFirst();
        }

        // Remove smaller elements from back
        while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
            dq.pollLast();
        }

        dq.offerLast(i);

        // Add result when window is formed
        if (i >= k - 1) {
            result.add(nums[dq.peekFirst()]);
        }
    }

    return result;
}




/*
🔍 Example
nums = [1,3,-1,-3,5,3,6,7], k = 3

Output = [3,3,5,5,6,7]
        🧠 Intuition

Deque always keeps:

Front → maximum
Back → smallest (within window relevance)

 */