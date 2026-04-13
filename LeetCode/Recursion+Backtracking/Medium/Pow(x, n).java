/*Problem

Compute:

x^n
🔑 Key Idea — Binary Exponentiation

Instead of multiplying x n times, we:

Square the base
Halve the exponent

 */


class Solution {
    public double myPow(double x, int n) {

        long power = n;  // handle Integer.MIN_VALUE

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double ans = 1;

        while (power > 0) {

            if (power % 2 == 1)
                ans *= x;

            x *= x;
            power /= 2;
        }

        return ans;
    }
}

/*
Complexity
Time  : O(log n)
Space : O(1)
🧠 Example
2^10
        = (2^5)^2
        = ((2^2)^2 * 2)^2

 */