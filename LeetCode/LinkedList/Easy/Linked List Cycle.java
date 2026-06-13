/*Problem

Determine whether a linked list contains a cycle.

Example:

        3 → 2 → 0 → -4
        ↑_____|

Output:

        true
        🔑 Key Idea — Floyd's Cycle Detection (Tortoise & Hare)

Use two pointers:

slow → moves one step
fast → moves two steps

If a cycle exists, they will eventually meet.

🚀 Java Solution

 */




class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }
}



/*
Complexity
Time  : O(n)
Space : O(1)
🧠 Why it works
slow → 1 step
fast → 2 steps

If there is a loop,
fast eventually catches slow.

 */