/*Problem

Each job has:

deadline
        profit

👉 Do at most one job per unit time

👉 Maximize total profit

🔑 Core Insight

👉 Do most profitable jobs first

👉 Place each job at latest possible free slot before deadline

⚡ Strategy
Sort jobs by profit (descending)
Create a timeline (slots)
For each job:
Try placing it at deadline → 1 (backwards)
If slot free → assign
✅ Java Code

 */





class Job {
    int id, deadline, profit;

    Job(int x, int y, int z) {
        id = x;
        deadline = y;
        profit = z;
    }
}

class Solution {

    public int[] JobScheduling(Job arr[], int n) {

        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job job : arr) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        int[] slots = new int[maxDeadline + 1]; // 1-based
        Arrays.fill(slots, -1);

        int count = 0;
        int totalProfit = 0;

        for (Job job : arr) {

            for (int j = job.deadline; j > 0; j--) {

                if (slots[j] == -1) {
                    slots[j] = job.id;
                    count++;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        return new int[]{count, totalProfit};
    }
}




/*
🔍 Example
Jobs:
ID  Deadline Profit
1   2        100
        2   1        50
        3   2        10
        4   1        20

Sort by profit:
        1, 2, 4, 3

Schedule:
Slot 2 → Job 1
Slot 1 → Job 2

Total Profit = 150
⏱ Complexity
Time: O(n log n + n * d)
Space: O(d)
🚨 Common Mistakes

❌ Sorting by deadline instead of profit
❌ Placing job at earliest slot instead of latest
❌ Not checking backward

🧠 Pattern Comparison (VERY IMPORTANT)
Problem	Strategy
N Meetings	Sort by end
Job Sequencing	Sort by profit
🔥 Key Takeaway

👉 Greedy = local best → global best

But:

Meetings → minimize end time
Jobs → maximize profit
🚀 Interview Insight

If interviewer asks:

        “Why greedy works here?”

Answer:

        👉 “Because choosing earliest finishing meeting leaves maximum room for future choices”
        👉 “Because placing highest profit job first ensures optimal global profit”

 */