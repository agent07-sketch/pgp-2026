/*Problem

Count pairs of leaf nodes whose distance ≤ distance.

🔑 Key Idea

👉 At each node, return a list of distances to leaf nodes below it

Then:

Combine left + right lists
Count valid pairs
🧠 Why this works

Each node acts like a merge point for leaf distances.

        ✅ Java Code

 */



class Solution {
    int count = 0;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return count;
    }

    private List<Integer> dfs(TreeNode node, int distance) {
        List<Integer> list = new ArrayList<>();

        if (node == null) return list;

        // leaf node
        if (node.left == null && node.right == null) {
            list.add(1);
            return list;
        }

        List<Integer> left = dfs(node.left, distance);
        List<Integer> right = dfs(node.right, distance);

        // count pairs
        for (int l : left) {
            for (int r : right) {
                if (l + r <= distance) {
                    count++;
                }
            }
        }

        // return distances (increase by 1)
        for (int l : left) {
            if (l + 1 < distance) list.add(l + 1);
        }
        for (int r : right) {
            if (r + 1 < distance) list.add(r + 1);
        }

        return list;
    }
}



/*
🔍 Intuition
Leaf distances bubble up ↑
Each node combines left + right
⏱ Complexity
Time: O(n * d²)

(d = distance limit, small in constraints)

 */