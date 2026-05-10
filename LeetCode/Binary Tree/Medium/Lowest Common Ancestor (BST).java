/*Key Insight (VERY IMPORTANT)

👉 BST property:

left < root < right
🔑 Logic

At node:

If both p and q are smaller → go LEFT
If both larger → go RIGHT
Else → current node is LCA
✅ Java Code

 */






class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {

            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            else {
                return root;
            }
        }

        return null;
    }
}




/*
🔍 Example
        6
                / \
                2   8
                / \ / \
                0  4 7  9

LCA(2, 8) → 6
LCA(2, 4) → 2

        ⏱ Complexity
Time: O(h)
Space: O(1)
🧠 Pattern Summary
Problem	Key Trick
Min Difference	Inorder (sorted BST)
LCA BST	Use BST property
🚨 Common Mistakes
Min Difference

❌ Comparing all pairs (O(n²))
        ❌ Forgetting inorder gives sorted

LCA

❌ Treating BST like normal tree
❌ Using full DFS instead of pruning

🔥 Interview Insight

These test whether you:

Recognize BST properties instantly
Avoid brute force
Use traversal smartly

 */