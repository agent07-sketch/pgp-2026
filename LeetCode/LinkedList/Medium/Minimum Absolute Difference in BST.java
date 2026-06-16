/*Problem

Find the minimum difference between values of any two nodes in a BST.

🔑 Key Idea — Inorder Traversal

BST inorder traversal produces:

sorted sequence

The minimum difference must occur between adjacent values in sorted order.

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

        if (node == null)
            return;

        inorder(node.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }

        prev = node.val;

        inorder(node.right);
    }
}









/*
Complexity
Time  : O(n)
Space : O(h)

Where:

h = height of tree
🧠 Why Adjacent Nodes?

Example:

BST:

        4
        / \
        2   6
        / \
        1   3

Inorder:
        1 2 3 4 6

Differences:
        1,1,1,2

Answer = 1
        🔥 Interview Summary
Problem	Pattern
Generate Parentheses	Backtracking
Minimum Absolute Difference in BST	Inorder Traversal
💡 Key Takeaways
Generate Parentheses
open < n
        close < open
        BST Minimum Difference
        BST + inorder = sorted order
        ⚡ Pattern Recognition Guide
        Backtracking Problems
        Generate Parentheses
        N Queens
        Combination Sum
        Restore IP Addresses
        BST Inorder Problems
        Minimum Absolute Difference
        Validate BST
        Kth Smallest in BST
        BST Iterator

        ⭐ These are both high-frequency interview problems:

        Generate Parentheses → Backtracking
        Minimum Absolute Difference in BST → Tree Traversal

 */