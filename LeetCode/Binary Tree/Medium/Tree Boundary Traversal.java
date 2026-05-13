/*Problem

Print boundary of binary tree in order:

Root → Left Boundary → Leaves → Right Boundary (reverse)
🔑 Key Insight

We break into 3 parts:

Left boundary (excluding leaves)
All leaf nodes
Right boundary (excluding leaves, reverse)
🎯 Important Rules
Do NOT include leaf nodes in left/right boundary
Avoid duplicates
Right boundary must be reversed
✅ Java Code

 */





class Solution {

    public List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        if (!isLeaf(root)) res.add(root.val);

        // Left boundary
        addLeft(root.left, res);

        // Leaves
        addLeaves(root, res);

        // Right boundary
        addRight(root.right, res);

        return res;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private void addLeft(TreeNode node, List<Integer> res) {
        while (node != null) {
            if (!isLeaf(node)) res.add(node.val);

            if (node.left != null)
                node = node.left;
            else
                node = node.right;
        }
    }

    private void addRight(TreeNode node, List<Integer> res) {
        Stack<Integer> st = new Stack<>();

        while (node != null) {
            if (!isLeaf(node)) st.push(node.val);

            if (node.right != null)
                node = node.right;
            else
                node = node.left;
        }

        while (!st.isEmpty()) {
            res.add(st.pop());
        }
    }

    private void addLeaves(TreeNode node, List<Integer> res) {
        if (node == null) return;

        if (isLeaf(node)) {
            res.add(node.val);
            return;
        }

        addLeaves(node.left, res);
        addLeaves(node.right, res);
    }
}



/*
🔍 Example
        1
                /   \
                2     3
                / \     \
                4   5     6

Boundary = 1 → 2 → 4 → 5 → 6 → 3
        ⏱ Complexity
Time: O(n)
Space: O(h)

 */