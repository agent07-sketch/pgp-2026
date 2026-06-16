/*Problem

Compute:

x^n

Efficiently. 

🔑 Key Idea — Binary Exponentiation

Instead of multiplying x exactly n times:

x^10
        = (x^5)^2
        = ((x^2)^2 * x)^2

Reduce exponent by half each step.

        🚀 Java Solution

 */





class Solution {
    public double myPow(double x, int n) {

        long power = n; // handle Integer.MIN_VALUE

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1;

        while (power > 0) {

            if ((power & 1) == 1)
                result *= x;

            x *= x;
            power >>= 1;
        }

        return result;
    }
}





/*
Complexity
Time  : O(log n)
Space : O(1)
🧠 Why long power = n?

        Because:

        Integer.MIN_VALUE = -2147483648

abs(Integer.MIN_VALUE)
overflows int

Using long avoids this issue.

🔥 Interview Summary
Problem	Pattern
Non-overlapping Intervals	Greedy + Sorting
Pow(x, n)	Binary Exponentiation
💡 Key Takeaways
Non-overlapping Intervals
Sort by ending time
Keep interval ending earliest
Pow(x, n)
Odd exponent → multiply answer
Square base every step
⚡ Pattern Recognition Guide
Greedy Interval Problems

Used in:

Non-overlapping Intervals
Meeting Rooms
Activity Selection
Minimum Arrows to Burst Balloons
Binary Exponentiation

Used in:

Pow(x, n)
Count Good Numbers
Modular Arithmetic
Matrix Exponentiation

⭐ Both are high-frequency interview problems:

Non-overlapping Intervals → Greedy
Pow(x, n) → Divide & Conquer / Math

 */