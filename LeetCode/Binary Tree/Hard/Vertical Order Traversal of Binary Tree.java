/*Problem

Group nodes column-wise (vertical lines).

Each node has coordinates:

Root → (row=0, col=0)
Left → (row+1, col-1)
Right → (row+1, col+1)
        🔑 Requirements (important!)

Sort by:

Column (left → right)
Row (top → bottom)
Value (if same position)
⚡ Approach

👉 Use BFS + store (col, row, value)

👉 Data structure:

TreeMap<col, TreeMap<row, PriorityQueue>>

Why?

TreeMap → sorted columns & rows
PriorityQueue → sorted values
✅ Java Code

 */




class Solution {

    static class Tuple {
        TreeNode node;
        int row, col;

        Tuple(TreeNode n, int r, int c) {
            node = n;
            row = r;
            col = c;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();

        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {
            Tuple t = q.poll();

            map.putIfAbsent(t.col, new TreeMap<>());
            map.get(t.col).putIfAbsent(t.row, new PriorityQueue<>());
            map.get(t.col).get(t.row).offer(t.node.val);

            if (t.node.left != null)
                q.offer(new Tuple(t.node.left, t.row + 1, t.col - 1));

            if (t.node.right != null)
                q.offer(new Tuple(t.node.right, t.row + 1, t.col + 1));
        }

        List<List<Integer>> res = new ArrayList<>();

        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> colList = new ArrayList<>();

            for (PriorityQueue<Integer> pq : rows.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }

            res.add(colList);
        }

        return res;
    }
}




/*
🔍 Example
        3
                / \
                9   20
                /  \
                15   7

Output:
        [
        [9],
        [3,15],
        [20],
        [7]
        ]
        ⏱ Complexity
Time: O(n log n)
Space: O(n)
🚨 Mistakes

❌ Not sorting same row nodes
❌ Using HashMap (loses order)
❌ Ignoring row level

 */