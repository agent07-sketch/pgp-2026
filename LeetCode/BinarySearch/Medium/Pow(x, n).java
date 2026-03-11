/*LeetCode 50 — Pow(x, n)

Goal: Compute

        𝑥
𝑛
        x
n

The optimal approach is Binary Exponentiation (Fast Power).

Idea

Instead of multiplying x n times, use:

x^10 = (x^5)^2
x^5  = x * (x^2)^2

So we halve the exponent each step.

Time Complexity:

O(log n)
*/
class Solution {
    public double myPow(double x, int n) {

        long power = n;   // handle overflow for Integer.MIN_VALUE

        if(power < 0){
            x = 1/x;
            power = -power;
        }

        double ans = 1;

        while(power > 0){

            if(power % 2 == 1)
                ans = ans * x;

            x = x * x;
            power = power / 2;
        }

        return ans;
    }
}

/*
        Example
                x = 2, n = 10
Output = 1024

 */