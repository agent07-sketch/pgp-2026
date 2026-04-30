/*
🔑 Key Idea — Monotonic Stack


        We want:

        Next greater element to the right
        🚀 Java Solution (LeetCode 496)

 */






import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // process nums2
        for (int num : nums2) {

            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // remaining → no next greater
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // build result
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}




/*
⚡ Variant: Circular Array (LeetCode 503)

👉 Traverse array twice

        Complexity
Time  : O(n)
Space : O(n)
🧠 Why stack works
Maintain decreasing stack
Pop smaller elements when bigger comes
🔥 Interview Summary
Problem	Pattern
Time to Buy Tickets	Math / Simulation Optimization
Next Greater Element	Monotonic Stack
💡 Key Takeaways
Tickets Problem
Avoid simulation → count contributions
Next Greater Element
Use stack for "next greater/smaller" problems
⚡ Monotonic Stack Pattern
Type	Condition
Next Greater	stack decreasing
Next Smaller	stack increasing

 */