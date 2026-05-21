/*Problem

Two nodes in a BST are swapped by mistake.
Restore BST without changing structure.

🔑 Core Insight

👉 Inorder traversal of BST should be:

Sorted order

👉 If two nodes are swapped → order breaks

⚡ Key Observation

You’ll see violations:

prev > current

Cases:

Adjacent swap → 1 violation
Non-adjacent swap → 2 violations
✅ Java Code (Optimal O(1) space)

 */




class Solution {

    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);

        // swap values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
            }
            second = node;
        }

        prev = node;

        inorder(node.right);
    }
}




/*
🔍 Example
Original (wrong BST):
        3
        / \
        1   4
        /
        2

Inorder: 1 3 2 4  ❌

Fix → swap 3 and 2
        ⏱ Complexity
Time: O(n)
Space: O(h)   (or O(1) with Morris traversal)
        🚨 Mistakes

❌ Swapping nodes instead of values
❌ Not tracking both violations
❌ Missing adjacent swap case

        🧠 Pattern Summary
Problem	Pattern
Sum Tree	Postorder + aggregation
Recover BST	Inorder + anomaly detection

 */