/*Problem

Given sorted array → build height-balanced BST

🔑 Key Insight

👉 Middle element = root
👉 Left half → left subtree
👉 Right half → right subtree

✅ Java Code

 */




class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }
}




/*
🔍 Example
nums = [-10, -3, 0, 5, 9]

Tree:

        0
        / \
        -3   9
        /   /
        -10  5
        🧠 Pattern

👉 Divide & Conquer
👉 Recursion on indices

⚡ Complexity
Problem	Time	Space
Binary Tree Paths	O(n)	O(h)
Sorted Array → BST	O(n)	O(h)
        🚨 Common Mistakes
Binary Tree Paths

❌ Forgetting leaf condition
❌ Wrong string concatenation

Sorted Array → BST

❌ Using wrong mid
❌ Not ensuring balance

🔥 Interview Insight

These test:

Tree traversal (DFS)
Recursion thinking
Divide & conquer

 */