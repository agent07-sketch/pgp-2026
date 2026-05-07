/*Idea

👉 Swap left and right at every node

✅ Recursive Solution

 */



class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // recurse
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
/*
🔍 Example

Before:

        4
        / \
        2   7

After:

        4
        / \
        7   2
        🔁 Iterative (BFS)

 */



class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            // swap
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        return root;
    }
}


/*
🧠 Pattern Recognition
Problem	Pattern
Max Depth	DFS / Recursion / Tree Height
Invert Tree	Tree Traversal + Swap

 */