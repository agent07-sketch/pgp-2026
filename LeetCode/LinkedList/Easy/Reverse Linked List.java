/*Problem
1 → 2 → 3 → 4 → 5

        ↓

        5 → 4 → 3 → 2 → 1
        🔑 Key Idea — Three Pointers

Maintain:

prev
        curr
next

Reverse links one by one.

🚀 Iterative Java Solution

 */





class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode nextNode = curr.next;

            curr.next = prev;

            prev = curr;
            curr = nextNode;
        }

        return prev;
    }
}
Complexity
Time  : O(n)
Space : O(1)
Recursive Solution
class Solution {
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}





/*
Complexity
Time  : O(n)
Space : O(n)   // recursion stack
🔥 Interview Summary
Problem	Pattern
Linked List Cycle	Fast & Slow Pointers
Reverse Linked List	Pointer Manipulation
💡 Key Takeaways
Linked List Cycle
slow += 1
fast += 2
Reverse Linked List
curr.next = prev
⚡ Pattern Recognition
Fast & Slow Pointer

Used in:

Linked List Cycle
Find Duplicate Number
Middle of Linked List
Happy Number
Pointer Reversal

Used in:

Reverse Linked List
Reverse Between Positions
Reverse Nodes in K Groups

 */