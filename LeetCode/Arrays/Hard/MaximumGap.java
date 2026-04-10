/*Problem

Find:

maximum difference between successive elements in sorted order
❌ Brute Force

Sort → O(n log n)

🔑 Optimal Idea — Bucket Sort (Pigeonhole Principle)

Key insight:

Max gap will NOT be inside a bucket
It will be BETWEEN buckets
        Steps
Find min and max
Create buckets:
bucketSize = ceil((max - min) / (n - 1))
Store:
bucketMin, bucketMax
Compute gap between buckets
 */



class Solution {
    public int maximumGap(int[] nums) {

        if (nums.length < 2) return 0;

        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) return 0;

        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            bucketMin[i] = Integer.MAX_VALUE;
            bucketMax[i] = Integer.MIN_VALUE;
        }

        // fill buckets
        for (int num : nums) {
            int idx = (num - min) / bucketSize;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        // compute max gap
        int maxGap = 0;
        int prevMax = min;

        for (int i = 0; i < bucketCount; i++) {

            if (bucketMin[i] == Integer.MAX_VALUE)
                continue;

            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }
}


/*
Complexity
Time  : O(n)
Space : O(n)
🔥 Interview Summary
Problem	Pattern
Permutation Sequence	Math / Factorial System
Maximum Gap	Bucket Sort
💡 Key Takeaways
Permutation Sequence
Use factorial blocks → jump directly
Maximum Gap
Gap exists BETWEEN buckets, not inside
⚡ Interview Gold Insight
When to use Bucket Sort?

If problem says:

max/min gap
range-based optimization
linear time needed

👉 Think:

Bucket / Pigeonhole Principle

 */