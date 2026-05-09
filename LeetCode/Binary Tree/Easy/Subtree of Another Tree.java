/*Problem

Check if tree subRoot exists exactly inside root.

        🔑 Idea

👉 For every node in root:

Check: “Is this subtree identical to subRoot?”

So we need:

Traversal of main tree
Comparison function
✅ Java Code

 */



class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        if (isSame(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }

    private boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        if (a.val != b.val) return false;

        return isSame(a.left, b.left) &&
                isSame(a.right, b.right);
    }
}


/*
🔍 Intuition
Try every node as root
→ compare structure + values
⏱ Complexity
Worst: O(n * m)

 */