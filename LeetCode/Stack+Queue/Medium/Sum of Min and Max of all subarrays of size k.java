/*Problem

For every window:
        👉 find min + max
👉 sum over all windows

🔑 Trick

Use two deques:

maxDeque → decreasing (for max)
minDeque → increasing (for min)
✅ Java Code

 */




public static long sumOfMinAndMax(int[] arr, int k) {

    Deque<Integer> maxDq = new ArrayDeque<>();
    Deque<Integer> minDq = new ArrayDeque<>();

    long sum = 0;

    for (int i = 0; i < arr.length; i++) {

        // Remove out of window
        if (!maxDq.isEmpty() && maxDq.peekFirst() <= i - k) {
            maxDq.pollFirst();
        }
        if (!minDq.isEmpty() && minDq.peekFirst() <= i - k) {
            minDq.pollFirst();
        }

        // Maintain max deque
        while (!maxDq.isEmpty() && arr[maxDq.peekLast()] < arr[i]) {
            maxDq.pollLast();
        }

        // Maintain min deque
        while (!minDq.isEmpty() && arr[minDq.peekLast()] > arr[i]) {
            minDq.pollLast();
        }

        maxDq.offerLast(i);
        minDq.offerLast(i);

        // Window ready
        if (i >= k - 1) {
            sum += arr[maxDq.peekFirst()] + arr[minDq.peekFirst()];
        }
    }

    return sum;
}








/*
🔍 Example
arr = [2, 5, -1, 7, -3, -1, -2], k = 4

Windows:
        [2,5,-1,7] → min=-1, max=7 → sum=6
        [5,-1,7,-3] → min=-3, max=7 → sum=4
        [-1,7,-3,-1] → min=-3, max=7 → sum=4
        [7,-3,-1,-2] → min=-3, max=7 → sum=4

Total = 18
        ⚡ Complexity (Both Problems)
Time: O(n)
Space: O(k)
🚨 Common Mistakes

❌ Storing values instead of indices
❌ Not removing out-of-window elements
❌ Wrong order in deque (breaks logic)

🧠 Pattern Recognition

If you see:

        “max/min in every window”
        “sliding window + optimal”

        👉 Think: Deque

🔥 Interview Insight

If interviewer asks:

        👉 “Why deque?”

Answer:

        “It helps maintain candidates in sorted order while allowing O(1) access to max/min and O(n) total operations.”

 */