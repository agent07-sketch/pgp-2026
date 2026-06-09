/*Problem

        Merge k sorted linked lists into one sorted linked list.

        Example:

        Input:
        1→4→5
        1→3→4
        2→6

        Output:
        1→1→2→3→4→4→5→6
        🔑 Key Idea — Min Heap

        Always pick the smallest current node among all lists.

        🚀 Java Solution

 */





import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq =
                new PriorityQueue<>((a, b) -> a.val - b.val);

        // insert first node of every list
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {

            ListNode node = pq.poll();

            curr.next = node;
            curr = curr.next;

            if (node.next != null)
                pq.offer(node.next);
        }

        return dummy.next;
    }
}



/*
Complexity
Time  : O(N log k)
Space : O(k)

Where:

N = total number of nodes
k = number of lists
🧠 Pattern Recognition
Multiple sorted structures
→ Heap

 */