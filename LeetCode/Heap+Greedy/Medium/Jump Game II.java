/*Problem

Given array:

        👉 Each index tells max jump length
👉 Find minimum jumps to reach end

🔑 Core Insight

👉 Think like BFS levels (but greedy optimized)

At each step:

Track current reachable range
Expand to farthest reachable
⚡ Strategy (Greedy)

Maintain:

end → current jump boundary
farthest → max reachable index
jumps → count
✅ Java Code

 */







class Solution {
    public int jump(int[] nums) {

        int jumps = 0;
        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            farthest = Math.max(farthest, i + nums[i]);

            // when we reach boundary → jump
            if (i == end) {
                jumps++;
                end = farthest;
            }
        }

        return jumps;
    }
}







/*
🔍 Example
Input: [2,3,1,1,4]

Step-by-step:
i=0 → farthest=2 → jump → end=2
i=1 → farthest=4
i=2 → jump → end=4

Answer = 2
        🎯 Visualization
[2,3,1,1,4]

Level 1 → indices reachable: [0 → 2]
Level 2 → [1 → 4]

        → total jumps = 2
🚨 Key Insight

👉 You don't jump at every index
        👉 You jump only when you must extend range

❌ Mistakes
Using recursion (TLE) ❌
Using DP O(n²) ❌
Jumping greedily at each step ❌
        🧠 Pattern Comparison
Problem	Type
Distant Barcodes	Greedy + Frequency
Jump Game II	Greedy + Range Expansion

 */