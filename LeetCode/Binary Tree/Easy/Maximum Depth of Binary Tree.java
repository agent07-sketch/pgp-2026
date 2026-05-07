/*Idea

👉 Depth = longest path from root → leaf

So at each node:

depth = 1 + max(leftDepth, rightDepth)
✅ Recursive (Best for interviews)
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }
}
🔍 Example
    1
            / \
            2   3
            /
            4

            👉 Depth = 3

        ⏱ Complexity
Time: O(n)
Space: O(h) (recursion stack)
        💡 Iterative (Level Order BFS)

 */



class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            depth++;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return depth;
    }
}