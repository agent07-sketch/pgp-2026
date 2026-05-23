/*Problem

Find the maximum sum of any subtree which is a valid BST.

        🔑 Core Idea

For every node, you must know:

        - Is subtree a BST?
        - Min value in subtree
- Max value in subtree
- Sum of subtree
⚡ Key Condition (VERY IMPORTANT)

A subtree is BST if:

left.max < node.val < right.min
🧩 What to return from recursion?

We return a custom object:

        (isBST, min, max, sum)
        ✅ Java Code

 */






class Solution {

    int maxSum = 0;

    class Info {
        boolean isBST;
        int min, max, sum;

        Info(boolean isBST, int min, int max, int sum) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

    public int maxSumBST(TreeNode root) {
        solve(root);
        return maxSum;
    }

    private Info solve(TreeNode node) {
        if (node == null) {
            return new Info(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        Info left = solve(node.left);
        Info right = solve(node.right);

        // check BST condition
        if (left.isBST && right.isBST &&
                node.val > left.max && node.val < right.min) {

            int sum = node.val + left.sum + right.sum;

            maxSum = Math.max(maxSum, sum);

            int min = Math.min(node.val, left.min);
            int max = Math.max(node.val, right.max);

            return new Info(true, min, max, sum);
        }

        return new Info(false, 0, 0, 0);
    }
}




/*
🔍 Example
        5
                / \
                3   8
                / \   \
                2   4   9

Whole tree is BST → sum = 31
        ⏱ Complexity
Time: O(n)
Space: O(h)
🚨 Mistakes

❌ Not tracking min/max properly
❌ Returning wrong values for null
        ❌ Forgetting global max update

 */