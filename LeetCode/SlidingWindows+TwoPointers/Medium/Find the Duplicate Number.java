/*Problem

Array contains:

n+1 numbers in range [1, n]

Find duplicate without modifying array and O(1) space.

        🔑 Key Idea — Floyd’s Cycle Detection (Linked List)

Treat array as:

index → value → next index

👉 Duplicate = cycle entry point

 */



class Solution {
    public int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[0];

        // detect cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // find entry point
        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}


/*
🧠 Why this works
Duplicate creates a cycle
Floyd’s algorithm finds cycle start
        Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Container With Most Water	Two Pointers
Find Duplicate	Cycle Detection
💡 Key Takeaways
Container Problem
Move pointer with smaller height
Duplicate Problem
Array → Linked list → cycle detection
⚡ Pattern Recognition
Pattern	When to Use
Two pointers	Opposite ends optimization
Floyd cycle	Duplicate in constrained array

 */