/*Problem

Given an array → build BST
Count how many reorderings give same BST

🔑 Deep Insight

👉 BST depends on insertion order
👉 First element = root
👉 Split into:

Left subtree → values < root
Right subtree → values > root
⚡ Core Formula

If:

Left size = L
Right size = R

Ways =

C(L + R, L) * ways(left) * ways(right)

👉 Why combination?

Because:

You can interleave left & right elements in many ways
But relative order inside left/right must stay same
🔥 Example
[3,1,2,5,4,6]

Left = [1,2]
Right = [5,4,6]

Ways = C(5,2) * ways(left) * ways(right)
✅ Java Code

 */






class Solution {

    static final int MOD = 1_000_000_007;
    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        return (int)((dfs(list) - 1 + MOD) % MOD); // exclude original
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) return 1;

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int L = left.size();
        int R = right.size();

        long ways = comb[L + R][L];

        return (((ways * leftWays) % MOD) * rightWays) % MOD;
    }
}








/*
🔍 Example
Input: [2,1,3]
Output: 1

Only one reordering → [2,1,3]
        ⏱ Complexity
Time: O(n^2)
Space: O(n^2)
🚨 Mistakes

❌ Not using combinations
❌ Forgetting modulo
❌ Including original permutation (subtract 1)

🧠 Pattern Summary
Problem	Pattern
Max Sum BST	Postorder + BST validation
Reorder Ways	Divide & Conquer + Combinatorics
🔥 Interview Insight

These are Level 🔥🔥🔥 problems

They test:

Tree + DP combo
Math + recursion
Ability to derive formulas
🚀 Final Note

If you can solve these confidently, you're above average candidate.

 */