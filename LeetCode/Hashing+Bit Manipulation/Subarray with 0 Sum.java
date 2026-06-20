/*Problem

        Determine whether there exists a subarray whose sum is 0.

        Example:

        arr = [4,2,-3,1,6]

        Output = true

        Subarray:
        [2,-3,1]
        🔑 Key Idea — Prefix Sum + HashSet

        If the same prefix sum appears twice:

        prefixSum(i) == prefixSum(j)

        ⇒ subarray (i+1 ... j) has sum 0

        Also:

        prefixSum == 0

        means subarray from index 0 has sum 0.

        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public boolean subArrayExists(int[] arr) {

        Set<Integer> set = new HashSet<>();

        int prefixSum = 0;

        for (int num : arr) {

            prefixSum += num;

            if (prefixSum == 0 || set.contains(prefixSum))
                return true;

            set.add(prefixSum);
        }

        return false;
    }
}




/*
Complexity
Time  : O(n)
Space : O(n)
To Count Number of Zero Sum Subarrays
Map<Integer,Integer> map = new HashMap<>();
map.put(0,1);

int sum = 0;
int count = 0;

for(int num : arr){

sum += num;

count += map.getOrDefault(sum,0);

    map.put(sum, map.getOrDefault(sum,0)+1);
        }
        🔥 Interview Summary
Problem	Pattern
Two Sum	HashMap
Subarray with 0 Sum	Prefix Sum + HashSet
💡 Key Takeaways
Two Sum
Need complement
→ HashMap
Subarray with 0 Sum
Repeated prefix sum
→ zero-sum subarray exists

 */