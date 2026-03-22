/*Problem

Find the largest prime factor of a number n.

        Example:

Input: 13195
Output: 29

Prime factors:

        5 × 7 × 13 × 29
        🔑 Optimal Idea

Remove all factors of 2

Then check only odd numbers up to √n

Keep dividing → reduces the number fast

If remainder > 1 → it’s a prime

 */


class Solution {

    public static long largestPrimeFactor(long n) {

        long maxPrime = -1;

        // remove factor 2
        while (n % 2 == 0) {
            maxPrime = 2;
            n /= 2;
        }

        // check odd factors
        for (long i = 3; i * i <= n; i += 2) {

            while (n % i == 0) {
                maxPrime = i;
                n /= i;
            }
        }

        // if n is still > 1 → prime
        if (n > 1)
            maxPrime = n;

        return maxPrime;
    }
}
/*
Complexity
Time  : O(√n)
Space : O(1)
 */