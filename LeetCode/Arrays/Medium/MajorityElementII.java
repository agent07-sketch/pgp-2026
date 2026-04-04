/*Problem

        Find all elements appearing more than ⌊n/3⌋ times.

        Example:

        Input:  [3,2,3]
        Output: [3]
        🔑 Key Idea — Boyer-Moore Voting (Extended)

        👉 There can be at most 2 elements with frequency > n/3.

        🧠 Steps
        Find 2 candidates
        Verify counts

 */



class Solution {
    public List<Integer> majorityElement(int[] nums) {

        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        // Step 1: find candidates
        for (int num : nums) {

            if (candidate1 != null && num == candidate1)
                count1++;

            else if (candidate2 != null && num == candidate2)
                count2++;

            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            }

            else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            }

            else {
                count1--;
                count2--;
            }
        }

        // Step 2: verify
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();

        int n = nums.length;

        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);

        return result;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Permutation in String	Sliding Window + Frequency
Majority Element II	Boyer-Moore Voting
💡 Key Takeaways
Permutation in String
Fixed window + frequency match
Majority Element II
At most 2 elements > n/3
        🧠 Pro Insight (Interview Gold)
Majority Element Variants
Condition	Max Elements
> n/2	1
        > n/3	2
        > n/k	k-1

 */