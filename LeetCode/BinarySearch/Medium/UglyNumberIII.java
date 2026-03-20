/*
Find the nth number divisible by a, b, or c.

Example

n = 3
a = 2
b = 3
c = 5

Output = 4

Ugly numbers:

2,3,4,5,6,8...

3rd ugly number = 4

Key Idea (Binary Search on Answer)

Search range:

low = 1
high = 2 * 10^9

For a number mid, count how many numbers ≤ mid are divisible by a, b, or c.

Using Inclusion–Exclusion Principle:

count =
mid/a + mid/b + mid/c
- mid/lcm(a,b)
- mid/lcm(b,c)
- mid/lcm(a,c)
+ mid/lcm(a,b,c)
 */

class Solution {

    public int nthUglyNumber(int n, int a, int b, int c) {

        long low = 1;
        long high = (long)2e9;

        long ab = lcm(a,b);
        long bc = lcm(b,c);
        long ac = lcm(a,c);
        long abc = lcm(a,(int)bc);

        while(low < high){

            long mid = low + (high - low) / 2;

            long count =
                    mid/a + mid/b + mid/c
                            - mid/ab - mid/ac - mid/bc
                            + mid/abc;

            if(count < n)
                low = mid + 1;
            else
                high = mid;
        }

        return (int)low;
    }

    private long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private long lcm(long a, long b){
        return (a * b) / gcd(a,b);
    }
}


login
