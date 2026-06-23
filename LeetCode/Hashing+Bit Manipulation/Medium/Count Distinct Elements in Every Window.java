/*Problem

        Given an array and window size k, return the number of distinct elements in every window.

        Example:

        arr = [1,2,1,3,4,2,3]
        k = 4

        Output:
        [3,4,4,3]

        Windows:

        [1,2,1,3] → {1,2,3} → 3
        [2,1,3,4] → {1,2,3,4} → 4
        [1,3,4,2] → {1,2,3,4} → 4
        [3,4,2,3] → {2,3,4} → 3
        🔑 Key Idea — Sliding Window + HashMap

        Maintain frequency of elements inside the current window.

        When:

        Adding an element → increase frequency
        Removing an element → decrease frequency
        Remove from map when frequency becomes 0
        🚀 Java Solution

 */






import java.util.*;

class Solution {

    ArrayList<Integer> countDistinct(int arr[], int k) {

        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        // First window
        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        result.add(map.size());

        // Remaining windows
        for (int i = k; i < arr.length; i++) {

            // Remove outgoing element
            int outgoing = arr[i - k];

            map.put(outgoing, map.get(outgoing) - 1);

            if (map.get(outgoing) == 0)
                map.remove(outgoing);

            // Add incoming element
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            result.add(map.size());
        }

        return result;
    }
}







/*
Complexity
Time  : O(n)
Space : O(k)

 */