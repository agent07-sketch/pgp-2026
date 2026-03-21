/*
Problem

Find the n-th number divisible by either a or b.

        🔑 Key Idea — Binary Search on Answer

We search the number x such that:

count of numbers ≤ x divisible by a or b = n

Using Inclusion–Exclusion:

count = x/a + x/b - x/lcm(a,b)
*/

class Solution {

    public int nthMagicalNumber(int n, int a, int b) {

        long low = 1;
        long high = (long)1e14;
        long mod = 1000000007;

        long lcm = lcm(a, b);

        while (low < high) {

            long mid = low + (high - low) / 2;

            long count = mid / a + mid / b - mid / lcm;

            if (count < n)
                low = mid + 1;
            else
                high = mid;
        }

        return (int)(low % mod);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}

/*
Complexity
Time  : O(log N)
Space : O(1)

 */