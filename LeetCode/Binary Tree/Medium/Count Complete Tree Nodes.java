/*Problem

Count nodes in a complete binary tree efficiently.

        ❌ Naive
DFS → O(n)
🔥 Optimized Idea

👉 Use height comparison

If:

left height == right height → perfect tree
nodes = 2^h - 1

Else:

recurse left + right
✅ Java Code

 */





class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^h - 1
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}




/*
🔍 Why it works
Complete Tree → last level partially filled
Perfect Tree → full nodes → use formula
⏱ Complexity
Time: O(log² n)
Space: O(log n)
🧠 Pattern Summary
Problem	Pattern
Boundary Traversal	Controlled DFS + structure
Count Nodes	Height + divide & conquer
🚨 Common Mistakes
Boundary Traversal

❌ Including leaves twice
❌ Not reversing right boundary
❌ Missing edge case (single node)

Count Nodes

❌ Using full DFS (miss optimization)
❌ Wrong height calculation

 */