/*Key Insight
👉 In a BST, an inorder traversal gives sorted order

So:

Smallest difference will always be between adjacent elements
🔑 Approach
Do inorder traversal
Track previous node value
Compute difference
✅ Java Code

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
🔍 Example
   4
           / \
           2   6
           / \
           1  3

Inorder:

        1 → 2 → 3 → 4 → 6

Differences:

        1,1,1,2 → min = 1
        ⏱ Complexity
Time: O(n)
Space: O(h)

 */
