/*Problem

Print nodes visible when tree is viewed from the bottom.

        🔑 Core Idea

Each node has a horizontal distance (HD):

Root → HD = 0
Left → HD - 1
Right → HD + 1

        👉 For each HD, keep the last node seen (bottom-most)

⚡ Best Approach → BFS (Level Order)

Why BFS?

It naturally processes level by level
Later nodes overwrite earlier ones → gives bottom view
✅ Java Code

 */





class Solution {

    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode n, int h) {
            node = n;
            hd = h;
        }
    }

    public List<Integer> bottomView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Map<Integer, Integer> map = new TreeMap<>(); // sorted by HD
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            // overwrite → keeps bottom-most
            map.put(curr.hd, curr.node.val);

            if (curr.node.left != null) {
                q.offer(new Pair(curr.node.left, curr.hd - 1));
            }

            if (curr.node.right != null) {
                q.offer(new Pair(curr.node.right, curr.hd + 1));
            }
        }

        for (int val : map.values()) {
            res.add(val);
        }

        return res;
    }
}





/*
🔍 Example
        1
                /   \
                2     3
                \   / \
                4 5   6

Bottom View → 2 4 5 6
        ⏱ Complexity
Time: O(n log n)   (TreeMap)
Space: O(n)
🚨 Mistakes

❌ Using DFS without depth tracking
❌ Not overwriting values
❌ Using HashMap (unordered output)

 */