/*Problem

Find all subtrees that appear more than once

Return roots of those subtrees.

🔑 Core Idea

👉 Serialize each subtree into a string

Example:

        2
        / \
        4   5

        → "2,4,#,#,5,#,#"
        ⚡ Strategy
Traverse using DFS
Serialize subtree
Store frequency in map
If freq == 2 → add to result
✅ Java Code

 */





class Solution {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();

        serialize(root, map, res);

        return res;
    }

    private String serialize(TreeNode node, Map<String, Integer> map, List<TreeNode> res) {
        if (node == null) return "#";

        String serial = node.val + "," +
                serialize(node.left, map, res) + "," +
                serialize(node.right, map, res);

        int count = map.getOrDefault(serial, 0);

        if (count == 1) { // second time → duplicate
            res.add(node);
        }

        map.put(serial, count + 1);

        return serial;
    }
}





/*
🔍 Example
        1
                / \
                2   3
                /   / \
                4   2   4
                /
                4

Duplicates:
        - Subtree rooted at 4
        - Subtree rooted at 2→4
        ⏱ Complexity
Time: O(n)
Space: O(n)
🚨 Mistakes

❌ Not including null markers → wrong matches
❌ Adding duplicates multiple times
❌ Using only node value (structure matters!)

🧠 Pattern Summary
Problem	Pattern
Bottom View	BFS + Horizontal Distance
Duplicate Subtrees	DFS + Hashing
🔥 Interview Tip

If interviewer pushes further:

        👉 Optimize duplicate subtree using IDs instead of strings
        (Advanced optimization)

 */