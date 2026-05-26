/*Problem Summary

You have an N-ary tree, and each node supports:

lock(node, user)
unlock(node, user)
upgrade(node, user)
🔒 Rules
✅ lock(node, user)

Allowed only if:

Node is NOT locked
No ancestor is locked
No descendant is locked
✅ unlock(node, user)

Allowed only if:

Node is locked
Locked by SAME user
✅ upgrade(node, user)

Allowed only if:

Node is NOT locked
No ancestor is locked
At least one descendant is locked

👉 After upgrade:

Unlock ALL descendants
Lock current node
🔥 Key Challenge

Efficiently check:

        1. Any locked ancestor?
        2. Any locked descendant?
        ⚡ Optimal Design

We store extra info in each node:

        - parent pointer
- children list
- isLocked
- lockedBy (user id)
- lockedDescendantCount
🧩 Why lockedDescendantCount?

        👉 Instead of traversing all children every time:

lockedDescendantCount > 0 → some descendant is locked
✅ Java Implementation

 */






class Node {
    String name;
    Node parent;
    List<Node> children = new ArrayList<>();

    boolean isLocked = false;
    int lockedBy = -1;
    int lockedDescendantCount = 0;

    Node(String name) {
        this.name = name;
    }
}

class LockingTree {

    Map<String, Node> map = new HashMap<>();

    public LockingTree(List<String> nodes, int m) {
        // Build tree (level order)
        for (String name : nodes) {
            map.put(name, new Node(name));
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node parent = map.get(nodes.get(i));

            for (int j = 1; j <= m; j++) {
                int childIndex = i * m + j;
                if (childIndex < nodes.size()) {
                    Node child = map.get(nodes.get(childIndex));
                    parent.children.add(child);
                    child.parent = parent;
                }
            }
        }
    }

    // 🔒 LOCK
    public boolean lock(String name, int user) {
        Node node = map.get(name);

        if (node.isLocked || node.lockedDescendantCount > 0)
            return false;

        // check ancestors
        Node curr = node.parent;
        while (curr != null) {
            if (curr.isLocked) return false;
            curr = curr.parent;
        }

        node.isLocked = true;
        node.lockedBy = user;

        // update ancestors
        curr = node.parent;
        while (curr != null) {
            curr.lockedDescendantCount++;
            curr = curr.parent;
        }

        return true;
    }

    // 🔓 UNLOCK
    public boolean unlock(String name, int user) {
        Node node = map.get(name);

        if (!node.isLocked || node.lockedBy != user)
            return false;

        node.isLocked = false;
        node.lockedBy = -1;

        // update ancestors
        Node curr = node.parent;
        while (curr != null) {
            curr.lockedDescendantCount--;
            curr = curr.parent;
        }

        return true;
    }

    // ⬆️ UPGRADE
    public boolean upgrade(String name, int user) {
        Node node = map.get(name);

        if (node.isLocked || node.lockedDescendantCount == 0)
            return false;

        // check ancestors
        Node curr = node.parent;
        while (curr != null) {
            if (curr.isLocked) return false;
            curr = curr.parent;
        }

        // unlock all descendants
        unlockDescendants(node);

        // lock current
        node.isLocked = true;
        node.lockedBy = user;

        // update ancestors
        curr = node.parent;
        while (curr != null) {
            curr.lockedDescendantCount++;
            curr = curr.parent;
        }

        return true;
    }

    private void unlockDescendants(Node node) {
        for (Node child : node.children) {
            unlockDescendants(child);

            if (child.isLocked) {
                child.isLocked = false;
                child.lockedBy = -1;

                Node curr = child.parent;
                while (curr != null) {
                    curr.lockedDescendantCount--;
                    curr = curr.parent;
                }
            }
        }
    }
}






/*
⏱ Complexity
Operation	Time
lock	O(h)
unlock	O(h)
upgrade	O(n) worst
🧠 Key Concepts Tested
Concept	Why
Tree + parent pointer	upward traversal
State caching	avoid repeated traversal
Design thinking	multiple constraints
DFS + BFS combo	upgrade logic
🚨 Common Mistakes

❌ Not tracking locked descendants → TLE
❌ Forgetting ancestor check
❌ Not updating counts correctly
❌ Unlocking descendants inefficiently

🔥 Interview Insight

This is NOT just DSA—it’s system design inside DSA.

They check:

Can you optimize brute force?
Can you maintain state correctly?
Can you handle edge cases?
        🚀 Final Level Achieved

You’ve now covered:

        ✅ Binary Search (all patterns)
✅ Sliding Window / Stack
✅ Trees (FULL coverage)
✅ Advanced design problems

 */