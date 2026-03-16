/*
Manhattan Distance

For two points:

|x1 - x2| + |y1 - y2|

For N points, brute force:

O(n²)

But we can optimize to:

O(n log n)
Key Insight

Manhattan distance separates:

|x1-x2| + |y1-y2|

So compute X contribution + Y contribution separately.

Trick

If array is sorted:

Contribution formula:

sum += arr[i] * i - prefixSum
 */

import java.util.*;

class Solution {

    public long totalManhattanDistance(int[][] points) {

        int n = points.length;

        int[] x = new int[n];
        int[] y = new int[n];

        for(int i = 0; i < n; i++){
            x[i] = points[i][0];
            y[i] = points[i][1];
        }

        Arrays.sort(x);
        Arrays.sort(y);

        return calculate(x) + calculate(y);
    }

    private long calculate(int[] arr){

        long sum = 0;
        long prefix = 0;

        for(int i = 0; i < arr.length; i++){

            sum += (long)arr[i] * i - prefix;
            prefix += arr[i];
        }

        return sum;
    }
}


/*
Pattern Recognition (Important for Interviews)
Problem	Pattern
719 Kth Smallest Pair Distance	Binary Search on Answer + Two Pointers
Manhattan Distance Sum	Mathematical Prefix Trick
 */