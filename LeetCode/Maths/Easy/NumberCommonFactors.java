/*Problem

Count how many numbers divide both a and b.

        Example:

Input: a = 12, b = 6
Output: 4

Common factors:

        1, 2, 3, 6
        Key Idea

Common factors of a and b = factors of:

gcd(a, b)

So problem reduces to:
        count divisors of gcd

 */

Java Solution
class Solution {
    public int commonFactors(int a, int b) {

        int gcd = gcd(a, b);
        int count = 0;

        for (int i = 1; i * i <= gcd; i++) {

            if (gcd % i == 0) {

                count++;  // i

                if (i != gcd / i)
                    count++;  // pair
            }
        }

        return count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
/*
Complexity
GCD         : O(log n)
Factor loop : O(√n)

Total       : O(√n)
Interview Summary
Problem	Pattern
Multiply Strings	Simulation (Math + Array)
Common Factors	GCD + Factor Counting
Key Takeaways
Multiply Strings

Use i + j indexing trick

Handle carry immediately

Common Factors

Always reduce problem using:

common factors → gcd

Since you're now covering math + arrays + binary search + merge sort, you're very close to complete DSA coverage for interviews.

 */