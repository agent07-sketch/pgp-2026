/*Problem

Given:

arr[i] = j

Modify so that:

arr[j] = i

👉 Basically invert the mapping (permutation)

🔑 Important Constraint

Works when:

arr is permutation of 0 → n-1
        🧠 Optimal In-Place Idea

We encode both old & new values in same array:

arr[i] = old + new * n

Later:

arr[i] = arr[i] / n
Java Solution (O(1) space)

 */


class Solution {
    public void rearrange(int[] arr, int n) {

        // Step 1: Encode both values
        for (int i = 0; i < n; i++) {
            int j = arr[i] % n;
            arr[j] += i * n;
        }

        // Step 2: Extract new values
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / n;
        }
    }
}

/*
Example
Input:  arr = [2,0,1]
Goal:   arr[arr[i]] = i

Result: [1,2,0]
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Search 2D Matrix II	Matrix Traversal
Modify Array Mapping	In-place Encoding
💡 Key Takeaways
2D Search
Start from top-right → eliminate row/column each step
Modify Array
Store two values in one index using modulo trick
⚠️ Important Edge Case

For mapping problem:

Works ONLY if elements are in range [0, n-1]
*/