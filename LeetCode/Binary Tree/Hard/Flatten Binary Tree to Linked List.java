/*Problem

Convert tree into linked list in-place following preorder:

Root → Left → Right

Result should look like:

        1
        \
        2
        \
        3 ...
        🔑 Core Idea

👉 Use preorder traversal
👉 Rearrange pointers

⚡ Optimal Approach (Morris-style, O(1) space)

For each node:

If left exists:
Find rightmost node of left subtree
Attach current.right to that node
Move left subtree to right
Move to next node
✅ Java Code

 */



class Solution {

    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;

                // find rightmost of left subtree
                while (prev.right != null) {
                    prev = prev.right;
                }

                // attach right subtree
                prev.right = curr.right;

                // move left to right
                curr.right = curr.left;
                curr.left = null;
            }

            curr = curr.right;
        }
    }
}





/*
🔍 Example
Before:
        1
        / \
        2   5
        / \   \
        3   4   6

After:
        1 → 2 → 3 → 4 → 5 → 6
        ⏱ Complexity
Time: O(n)
Space: O(1)
🚨 Mistakes

❌ Forgetting to nullify left
❌ Losing right subtree
❌ Using extra list (not in-place)

 */