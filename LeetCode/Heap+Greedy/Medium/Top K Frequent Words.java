/*Problem

        Return the k most frequent words.

        Rules:

        Higher frequency first
        If frequencies are equal → lexicographically smaller word first

        Example:

        Input:
        words = ["i","love","leetcode","i","love","coding"]
        k = 2

        Output:
        ["i","love"]
        🔑 Key Idea — HashMap + Min Heap
        Count frequency.
        Maintain a heap of size k.
        Heap order:
        Lower frequency first.
        If same frequency → lexicographically larger word first (so smaller words survive).
        🚀 Java Solution

 */




import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (!freq.get(a).equals(freq.get(b)))
                        return freq.get(a) - freq.get(b);

                    return b.compareTo(a);
                }
        );

        for (String word : freq.keySet()) {

            pq.offer(word);

            if (pq.size() > k)
                pq.poll();
        }

        List<String> result = new ArrayList<>();

        while (!pq.isEmpty())
            result.add(pq.poll());

        Collections.reverse(result);

        return result;
    }
}




/*
Complexity
Time  : O(n log k)
Space : O(n)
Alternative

Sort all words:

O(n log n)

Heap solution is preferred when k << n.

 */