/*Problem

Find:

sum of all prime numbers ≤ n

Example:

Input: 10
Output: 17
Primes: 2 + 3 + 5 + 7 = 17
        🔑 Optimal Approach — Sieve of Eratosthenes

Steps:

Mark all numbers as prime initially

Mark multiples as non-prime

Sum remaining primes

 */


class Solution {
    public long sumOfPrimes(int n) {

        if (n < 2) return 0;

        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++)
            isPrime[i] = true;

        for (int i = 2; i * i <= n; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        long sum = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime[i])
                sum += i;
        }

        return sum;
    }
}

/*
Complexity
Time  : O(n log log n)
Space : O(n)
🔥 Interview Pattern Summary
Problem	Pattern
Count Smaller After Self	Merge Sort + Counting
Sum of Primes	Sieve of Eratosthenes
💡 Key Insights
Merge Sort Problems (VERY IMPORTANT)

Used in:

Count Inversions

Count Smaller After Self

Reverse Pairs

👉 All follow:

count during merge
 */