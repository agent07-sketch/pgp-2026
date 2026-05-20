/*Problem

Given an N-ary tree (each node can have multiple children), return level-wise traversal.

        🔑 Core Idea

👉 Same as binary tree level order
Just instead of left/right, you iterate over children

⚡ Approach → BFS
Use queue
Process level by level
Add all children of node
✅ Java Code

 */






class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
        children = new ArrayList<>();
    }
}

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                level.add(curr.val);

                for (Node child : curr.children) {
                    q.offer(child);
                }
            }

            res.add(level);
        }

        return res;
    }
}





/*
🔍 Example
        1
                /  |  \
                3   2   4
                / \
                5   6

Output:
        [
        [1],
        [3,2,4],
        [5,6]
        ]
        ⏱ Complexity
Time: O(n)
Space: O(n)
🚨 Mistakes

❌ Forgetting null check
❌ Not looping over all children
❌ Mixing DFS instead of BFS

 */