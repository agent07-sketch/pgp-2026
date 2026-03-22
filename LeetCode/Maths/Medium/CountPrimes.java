/*
Problem
Count number of primes less than n.

        Example:

Input: 10
Output: 4

Primes:

        2, 3, 5, 7
        🔑 Optimal Approach — Sieve of Eratosthenes

Idea:

Assume all numbers are prime

Mark multiples as non-prime
 */


class Solution {
    public int countPrimes(int n) {

        if (n <= 2) return 0;

        boolean[] isPrime = new boolean[n];

        // initialize all as true
        for (int i = 2; i < n; i++)
            isPrime[i] = true;

        for (int i = 2; i * i < n; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }

        return count;
    }
}

/*
Complexity
Time  : O(n log log n)
Space : O(n)

 Interview Summary
Problem	Pattern
Largest Prime Factor	Trial Division (√n optimization)
Count Primes	Sieve of Eratosthenes

 Pro Tips (Interview Gold)
Largest Prime Factor

Always reduce n while dividing

Only iterate till √n

Count Primes

Start marking from:

i * i   (not 2*i)

because smaller multiples are already handled.

 */