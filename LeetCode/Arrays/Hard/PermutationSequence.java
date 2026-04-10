/*Problem

        Given n and k, return the k-th permutation of numbers 1..n.

        Example:

        n = 3, k = 3
        Permutations:
        123, 132, 213, 231, 312, 321

        Output: "213"
        🔑 Key Idea — Factorial Number System

        Instead of generating all permutations:

        Group size = (n-1)!

        Use:

        index = k / (n-1)!

 */




class Solution {
    public String getPermutation(int n, int k) {

        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n];

        fact[0] = 1;

        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--; // convert to 0-based

        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {

            int index = k / fact[i - 1];
            sb.append(numbers.get(index));

            numbers.remove(index);

            k = k % fact[i - 1];
        }

        return sb.toString();
    }
}

/*
Complexity
Time  : O(n^2) (due to list removal)
Space : O(n)
🧠 Insight
We directly jump to k-th permutation
instead of generating all

 */