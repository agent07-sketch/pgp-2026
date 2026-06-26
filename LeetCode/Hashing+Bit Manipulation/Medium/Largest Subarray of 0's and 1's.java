/*Problem

        Find the length of the largest subarray having an equal number of 0s and 1s.

        Example:

        Input:
        arr = [0,0,1,0,1,1,0]

        Output:
        6

        Explanation:

        [0,1,0,1,1,0]

        contains:

        3 zeros
        3 ones
        🔑 Key Idea — Prefix Sum + HashMap

        Replace:

        0 → -1
        1 → +1

        Now the problem becomes:

        Find the longest subarray with sum = 0

        which is the classic Prefix Sum problem.

        🚀 Java Solution

 */




import java.util.*;

class Solution {

    int maxLen(int[] arr, int n) {

        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] == 0)
                sum += -1;
            else
                sum += 1;

            if (sum == 0)
                maxLength = i + 1;

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength,
                        i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLength;
    }
}






/*
Complexity
Time  : O(n)
Space : O(n)
🧠 Why Replace 0 with -1?

Example:

        0 0 1 1

        ↓

        -1 -1 +1 +1

Sum = 0

Equal number of 0s and 1s ⇒ total sum becomes 0.

 */