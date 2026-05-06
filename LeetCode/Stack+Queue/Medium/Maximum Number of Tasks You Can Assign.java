/*
Core Idea

        You have:

        tasks[] (difficulty)
        workers[] (strength)
        pills (boost strength by strength value)

        👉 Goal: maximize number of tasks completed

        🔑 Key Pattern

        👉 Binary Search on Answer + Greedy + Deque

        💡 Why Binary Search?

        You don’t know max tasks → so:

        Try k tasks → can we assign them?
        🎯 Strategy
        Sort tasks and workers
        Binary search k
        Check feasibility using:
        strongest workers
        pills if needed
        ✅ Java Code

 */




import java.util.*;

class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {

        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0, right = Math.min(tasks.length, workers.length);
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canAssign(tasks, workers, pills, strength, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {

        Deque<Integer> dq = new ArrayDeque<>();

        int j = workers.length - 1;

        for (int i = k - 1; i >= 0; i--) {

            while (j >= workers.length - k && workers[j] + strength >= tasks[i]) {
                dq.addFirst(workers[j]);
                j--;
            }

            if (dq.isEmpty()) return false;

            if (dq.peekLast() >= tasks[i]) {
                dq.pollLast(); // no pill needed
            } else {
                if (pills == 0) return false;
                dq.pollFirst(); // use pill
                pills--;
            }
        }

        return true;
    }
}


/*
🧠 Intuition
Assign hardest tasks first
Use strongest workers
Use pills only when necessary

 */