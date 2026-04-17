/*Problem

Count number of strings of length n such that:

Even index → digits {0,2,4,6,8} → 5 choices
Odd index → primes {2,3,5,7} → 4 choices
🔑 Key Idea — Math + Fast Power

Let:

even positions = (n + 1) / 2
odd positions  = n / 2

Total:

answer = 5^(even) * 4^(odd)

Use modular exponentiation.
 */



class Solution {

    long MOD = 1000000007;

    public int countGoodNumbers(long n) {

        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = (power(5, even) * power(4, odd)) % MOD;

        return (int) ans;
    }

    private long power(long base, long exp) {

        long result = 1;

        while (exp > 0) {

            if ((exp & 1) == 1)
                result = (result * base) % MOD;

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}



/*
Complexity
Time  : O(log n)
Space : O(1)
🧠 Insight
Independent choices → multiply possibilities
Use fast exponentiation for large n

 */