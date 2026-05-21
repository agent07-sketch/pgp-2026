/*Problem

A binary tree is a Sum Tree if:

Node value = sum of left subtree + sum of right subtree

👉 Leaves are always valid (sum = 0)

🔑 Core Idea

👉 For every node:

Get left subtree sum
Get right subtree sum
Check condition
Return total sum
⚡ Approach → Postorder DFS

Why postorder?
        👉 Because we need children sums before parent

✅ Java Code

 */




class Solution {

    public boolean isSumTree(TreeNode root) {
        return check(root).isSumTree;
    }

    class Pair {
        boolean isSumTree;
        int sum;

        Pair(boolean isSumTree, int sum) {
            this.isSumTree = isSumTree;
            this.sum = sum;
        }
    }

    private Pair check(TreeNode node) {
        if (node == null) return new Pair(true, 0);

        // Leaf node
        if (node.left == null && node.right == null) {
            return new Pair(true, node.val);
        }

        Pair left = check(node.left);
        Pair right = check(node.right);

        boolean isValid = left.isSumTree && right.isSumTree &&
                (node.val == left.sum + right.sum);

        int totalSum = node.val + left.sum + right.sum;

        return new Pair(isValid, totalSum);
    }
}




/*
🔍 Example
        10
                /  \
                4    6

Valid Sum Tree:
        4 + 6 = 10
        ⏱ Complexity
Time: O(n)
Space: O(h)
🚨 Mistakes

❌ Recomputing subtree sum separately (O(n²))
        ❌ Not handling leaf properly
❌ Using preorder instead of postorder

 */