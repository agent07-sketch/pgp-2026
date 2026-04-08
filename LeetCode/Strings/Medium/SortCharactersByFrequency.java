/*Problem

        Sort characters in a string by decreasing frequency.

        Example:

        Input:  "tree"
        Output: "eetr"  or "eert"
        🔑 Key Idea
        Count frequency
        Sort characters based on frequency
        🚀 Approach 1 — HashMap + Sorting
        Java Solution

 */


import java.util.*;

class Solution {
    public String frequencySort(String s) {

        Map<Character, Integer> freq = new HashMap<>();

        // count frequency
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // convert to list
        List<Character> list = new ArrayList<>(freq.keySet());

        // sort by frequency descending
        Collections.sort(list, (a, b) -> freq.get(b) - freq.get(a));

        StringBuilder sb = new StringBuilder();

        for (char c : list) {
            int count = freq.get(c);
            while (count-- > 0)
                sb.append(c);
        }

        return sb.toString();
    }
}


⚡ Better Approach — Bucket Sort (O(n))

class Solution {
    public String frequencySort(String s) {

        int[] freq = new int[128];

        for (char c : s.toCharArray())
            freq[c]++;

        List<Character>[] bucket = new List[s.length() + 1];

        for (int i = 0; i < 128; i++) {
            if (freq[i] > 0) {
                if (bucket[freq[i]] == null)
                    bucket[freq[i]] = new ArrayList<>();
                bucket[freq[i]].add((char) i);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++)
                        sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}

/*
Complexity
Time  : O(n)
Space : O(n)

 */