/*Problem

A node is good if:
        👉 No ancestor has a greater value

🔑 Idea

👉 Keep track of max value seen so far

✅ Java Code

 */




class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;

        int count = 0;

        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);

        return count;
    }
}



/*
🔍 Example
      3
              / \
              1   4
              /     \
              3       5

Good nodes:

        3, 3, 4, 5 → total = 4
        ⏱ Complexity
Time: O(n)
Space: O(h)
🧠 Pattern Summary
Problem	Pattern
Subtree	DFS + Tree Comparison
Good Nodes	DFS + Carry State

 */