/*Problem

Each person buys 1 ticket per turn.
Find time for person at index k.

Example:

tickets = [2,3,2], k = 2 → Output = 6
        🔑 Key Idea — Simulation Insight (No Queue Needed)

Instead of simulating queue:
        👉 Count how many times each person contributes

🧠 Logic

For each index i:

        if i ≤ k → min(tickets[i], tickets[k])
if i > k → min(tickets[i], tickets[k] - 1)
🚀 Java Solution

 */





class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {

        int time = 0;

        for (int i = 0; i < tickets.length; i++) {

            if (i <= k)
                time += Math.min(tickets[i], tickets[k]);
            else
                time += Math.min(tickets[i], tickets[k] - 1);
        }

        return time;
    }
}



/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Insight
Each person contributes limited turns before k finishes

 */