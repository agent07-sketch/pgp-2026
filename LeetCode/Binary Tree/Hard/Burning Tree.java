/*Problem

Given a target node → fire spreads to:

Left child
Right child
Parent

Find time to burn entire tree

🔑 Core Insight

👉 Tree becomes undirected graph

We need:

Parent mapping
BFS from target
⚡ Approach
Step 1: Map parent pointers
Step 2: Find target node
Step 3: BFS (like spreading fire)
✅ Java Code

 */






class Solution {

    public int burnTree(TreeNode root, int target) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        TreeNode targetNode = buildParentMap(root, parentMap, target);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // left
                if (curr.left != null && !visited.contains(curr.left)) {
                    burned = true;
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                // right
                if (curr.right != null && !visited.contains(curr.right)) {
                    burned = true;
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                // parent
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    burned = true;
                    visited.add(parent);
                    q.offer(parent);
                }
            }

            if (burned) time++;
        }

        return time;
    }

    private TreeNode buildParentMap(TreeNode root,
                                    Map<TreeNode, TreeNode> parentMap,
                                    int target) {

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        TreeNode targetNode = null;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.val == target) {
                targetNode = curr;
            }

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.offer(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.offer(curr.right);
            }
        }

        return targetNode;
    }
}




/*
🔍 Example
        1
                /   \
                2     3
                / \
                4   5

Target = 5

Time = 3
        (5 → 2 → 1 → 3)
        ⏱ Complexity
Time: O(n)
Space: O(n)
🚨 Mistakes

❌ Not tracking parent
❌ Not using visited (infinite loop risk)
❌ Counting time incorrectly

🧠 Pattern Summary
Problem	Pattern
Flatten Tree	Pointer manipulation / Morris
Burning Tree	Graph BFS + parent mapping
🔥 Interview Insight

These test:

In-place transformation (flatten)
Converting tree → graph (burning)
BFS vs DFS decision

 */