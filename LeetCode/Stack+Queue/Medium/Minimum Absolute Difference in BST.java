/*Problem

Find minimum difference between values of any two nodes in BST.

🔑 Key Idea — Inorder Traversal

BST property:

Inorder → sorted order
🚀 Java Solution

 */




class Solution {

    int minDiff = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode node) {

        if (node == null) return;

        inorder(node.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }

        prev = node.val;

        inorder(node.right);
    }
}





/*
🧠 Why this works
Closest values in sorted order → minimum difference
Complexity
Time  : O(n)
Space : O(h)
🔥 Interview Summary
Problem	Pattern
Remove Duplicate Letters	Monotonic Stack + Greedy
Min Diff in BST	Inorder Traversal
💡 Key Takeaways
Remove Duplicate Letters
Stack + remove bigger chars if possible
BST Minimum Difference
Inorder → sorted → compare adjacent
⚡ Pattern Recognition
Pattern	Use Case
Monotonic Stack	lexicographical, next greater
Inorder Traversal	BST problems

 */