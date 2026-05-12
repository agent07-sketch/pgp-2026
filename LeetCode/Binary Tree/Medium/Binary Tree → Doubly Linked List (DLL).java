/*Problem

Convert tree to DLL using inorder traversal

🔑 Key Insight

👉 Inorder of BST = sorted
👉 Just connect nodes as you traverse

🎯 Maintain
prev → previous node
head → start of DLL
✅ Java Code

 */


class Solution {

    TreeNode prev = null;
    TreeNode head = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;

        inorder(root);

        return head;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }

        prev = node;

        inorder(node.right);
    }
}


/*
🔍 Example
Tree:        DLL:
        2         1 ⇄ 2 ⇄ 3
        / \
        1   3
        ⚡ If Circular DLL required (common variation)

Add at end:

head.left = prev;
prev.right = head;
⏱ Complexity
Time: O(n)
Space: O(h)
🧠 Pattern Summary
Problem	Pattern
Good Leaf Pairs	DFS + merge lists
Tree → DLL	Inorder + pointer linking
🚨 Common Mistakes
Good Leaf Pairs

❌ Forgetting to increment distance
❌ Not pruning distances ≥ limit

Tree → DLL

❌ Losing head reference
❌ Wrong linking order

 */