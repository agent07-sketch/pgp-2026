/*Problem

Find shortest distance (edges count) between two nodes.

        🔑 Core Insight

👉 Distance =

dist(a, b) = dist(root → a) + dist(root → b) - 2 * dist(root → LCA)

OR easier:

        👉 Find LCA, then:

distance = dist(LCA → a) + dist(LCA → b)
⚡ Step-by-Step
Find LCA
Find distance from LCA to both nodes
Add them
✅ Java Code

 */



class Solution {

    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = findLCA(root, p, q);

        int d1 = findLevel(lca, p, 0);
        int d2 = findLevel(lca, q, 0);

        return d1 + d2;
    }

    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null) return null;

        if (root.val == p || root.val == q) return root;

        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (left != null && right != null) return root;

        return (left != null) ? left : right;
    }

    private int findLevel(TreeNode root, int target, int level) {
        if (root == null) return -1;

        if (root.val == target) return level;

        int left = findLevel(root.left, target, level + 1);
        if (left != -1) return left;

        return findLevel(root.right, target, level + 1);
    }
}



/*
🔍 Example
        1
                /   \
                2     3
                / \
                4   5

Distance between 4 and 5 = 2
        (4 → 2 → 5)
        ⏱ Complexity
Time: O(n)
Space: O(h)
🚨 Mistakes

❌ Forgetting LCA step
❌ Returning wrong level (-1 handling)
❌ Using node reference vs value mismatch

🧠 Pattern Summary
Problem	Pattern
N-ary Level Order	BFS generalization
Min Distance	LCA + DFS

 */