/*Problem

        Find whether there exist two pairs:

        (a,b) and (c,d)

        such that:

        a + b = c + d

        and the indices are all distinct (non-overlapping).

        Example:

        arr = [3,4,7,1,2,9,8]

        Pairs:
        3+8 = 4+7 = 11

        Output: true
        🔑 Key Idea — HashMap of Pair Sums

        For every pair (i,j):

        Compute sum.
        If sum already exists:
        Check whether indices overlap.
        Otherwise store the pair.
        🚀 Java Solution

 */






import java.util.*;

class Solution {

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public boolean findPairs(int[] arr) {

        Map<Integer, Pair> map = new HashMap<>();

        int n = arr.length;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int sum = arr[i] + arr[j];

                if (map.containsKey(sum)) {

                    Pair p = map.get(sum);

                    if (p.first != i &&
                            p.first != j &&
                            p.second != i &&
                            p.second != j) {

                        return true;
                    }
                }
                else {
                    map.put(sum, new Pair(i, j));
                }
            }
        }

        return false;
    }
}






/*
Complexity
Time  : O(n²)
Space : O(n²)
Pattern Recognition
Need previous pair information
→ HashMap + Pair Storage

 */