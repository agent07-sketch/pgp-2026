/*Problem

Return all root → leaf paths as strings.

        🔑 Idea

👉 Use DFS (recursion)
👉 Keep building path string
👉 When you reach a leaf → store path

✅ Java Code

 */




class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // build path
        if (path.isEmpty()) {
            path = String.valueOf(node.val);
        } else {
            path = path + "->" + node.val;
        }

        // if leaf → add result
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }
}


/*
🔍 Example
    1
            / \
            2   3
            \
            5

Output:

        ["1->2->5", "1->3"]
        🧠 Pattern

👉 DFS + path building (backtracking style)

 */