/*Problem

        Return all combinations of size k from numbers 1 → n.

        Example:

        Input: n = 4, k = 2
        Output:
        [
        [1,2], [1,3], [1,4],
        [2,3], [2,4],
        [3,4]
        ]
        🔑 Key Idea — Backtracking

        At each step:

        Choose a number
        Move forward (avoid reuse)

 */



import java.util.*;

class Solution {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int start, int n, int k) {

        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {

            curr.add(i);
            backtrack(res, curr, i + 1, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}



/*
⚡ Optimization (Pruning)
for (int i = start; i <= n - (k - curr.size()) + 1; i++)

        👉 Avoid unnecessary recursion

        Complexity
O(C(n,k))

 */