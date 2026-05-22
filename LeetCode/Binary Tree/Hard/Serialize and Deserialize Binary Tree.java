/*Problem

Convert tree → string
Convert string → tree

🔑 Core Idea

👉 Use preorder traversal with null markers

Node → Left → Right
⚡ Why null markers?

Without nulls:

        1 2 3  ❌ ambiguous

With nulls:

        1,2,#,#,3,#,#  ✅ unique
✅ Java Code

 */




class Codec {

    // Serialize
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }

        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Deserialize
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(q);
    }

    private TreeNode buildTree(Queue<String> q) {
        String val = q.poll();

        if (val.equals("#")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(q);
        node.right = buildTree(q);

        return node;
    }
}



/*
🔍 Example
Tree:
        1
        / \
        2   3

Serialized:
        "1,2,#,#,3,#,#"
        ⏱ Complexity
Time: O(n)
Space: O(n)
🚨 Mistakes

❌ Not adding null markers
❌ Using inorder (not unique)
❌ Wrong split handling

🧠 Pattern Summary
Problem	Pattern
Vertical Traversal	BFS + coordinates + sorting
Serialize/Deserialize	DFS + encoding
🔥 Interview Insight

These test:

Data structure design
Traversal control
Edge-case thinking

 */