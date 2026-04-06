/*Problem

        Given two arrays A and B, check if we can swap one pair (a, b) such that:

        sum(A) == sum(B)
        🔑 Key Idea

        Let:

        sumA = sum of A
        sumB = sum of B

        After swapping a and b:

        sumA - a + b = sumB - b + a

        Simplify:

        sumA - sumB = 2 * (a - b)

        👉 So:

        a - b = (sumA - sumB) / 2
        🧠 Strategy
        Compute diff = (sumA - sumB) / 2
        Now check:
        a = b + diff

        Use a HashSet for fast lookup.

 */



import java.util.*;

class Solution {
    public int findSwapValues(int[] A, int[] B) {

        int sumA = 0, sumB = 0;

        for (int a : A) sumA += a;
        for (int b : B) sumB += b;

        int diff = sumA - sumB;

        // if diff is odd → impossible
        if (diff % 2 != 0) return -1;

        diff /= 2;

        Set<Integer> set = new HashSet<>();
        for (int b : B) set.add(b);

        for (int a : A) {
            if (set.contains(a - diff))
                return 1;
        }

        return -1;
    }
}

/*
Complexity
Time  : O(n + m)
Space : O(m)

 */