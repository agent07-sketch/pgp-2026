/*Problem

        Given a list of points on a 2D plane, return the maximum number of points that lie on the same straight line.

        Example:

        Input:
        [[1,1],[2,2],[3,3]]

        Output:
        3

        Another example:

        Input:
        [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]

        Output:
        4
        🔑 Key Idea — Fix One Point + HashMap of Slopes

        For every point i:

        Consider it as the starting point.
        Compute the slope with every other point.
        Count how many times each slope occurs.
        The most frequent slope gives the maximum points on one line through point i.
        ⚠️ Important

        Using double for slopes can cause precision errors.

        Instead, store the slope as a reduced fraction:

        dy / dx

        Reduce using GCD.

        Example:

        4/2

        ↓

        2/1

        Store as:

        "2/1"
        🚀 Java Solution

 */







import java.util.*;

class Solution {

    public int maxPoints(int[][] points) {

        int n = points.length;
        if (n <= 2) return n;

        int ans = 0;

        for (int i = 0; i < n; i++) {

            Map<String, Integer> map = new HashMap<>();
            int duplicate = 1;

            for (int j = i + 1; j < n; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }

                int g = gcd(dx, dy);

                dx /= g;
                dy /= g;

                // Normalize sign
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                // Vertical line
                if (dx == 0) {
                    dy = 1;
                }

                // Horizontal line
                if (dy == 0) {
                    dx = 1;
                }

                String key = dy + "/" + dx;

                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            int max = 0;

            for (int val : map.values()) {
                max = Math.max(max, val);
            }

            ans = Math.max(ans, max + duplicate);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }
}







/*
Example
Points

        (1,1)
(2,2)
        (3,3)
        (3,1)

Using (1,1):

Slope

        (2,2)

1/1

        (3,3)

        1/1

        (3,1)

        0/1

HashMap:

        1/1 -> 2

        0/1 -> 1

Maximum:

        2 + current point

        =

        3
Complexity
Time  : O(n²)

Space : O(n)
Why Not Use Floating Point?

Wrong:

double slope = (double)dy / dx;

Because:

        1/3

        2/6

        3/9

may produce precision issues.

Always reduce:

dy/gcd

dx/gcd
🔥 Interview Summary
Problem	Pattern
Max Points on a Line	Geometry + HashMap + GCD
💡 Key Takeaways
Core Idea
Fix one point

Count slopes

Maximum frequency
Represent Slopes Safely
dy/gcd

dx/gcd

instead of floating-point values.

        ⚡ Pattern Recognition Guide
Geometry + HashMap

Used in:

Max Points on a Line
Valid Boomerang
Check Straight Line
Detect Squares
GCD-Based Normalization

Used in:

Max Points on a Line
Fraction Problems
Rational Number Comparison
🎯 Interview Tips

When the problem asks:

Maximum points satisfying the same line

Think:

Fix one point.
Compute slopes to all other points.
Normalize slope using GCD.
Count frequencies with a HashMap.

 */


/*
Why your test case fails

For:

[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]

The line containing 4 points is:

(1,4)
(2,3)
(3,2)
(4,1)

Their slope is:

-1

But your code stores some slopes as:

-1/1

and others as

1/-1

These are mathematically identical but become different HashMap keys.

Interview Tip ⭐

Whenever you represent a slope as (dy, dx):

Divide by gcd.
Keep dx > 0.
If dx == 0, store as (1,0) (vertical line).
If dy == 0, store as (0,1) (horizontal line).

This normalization avoids all sign-related mismatches and passes all LeetCode test cases.
 */