/*Problem

Every element appears twice except two numbers, which appear only once.

Return those two unique numbers.

        Example:

Input:
nums = [1,2,1,3,2,5]

Output:
        [3,5]
        🔑 Key Idea — XOR + Rightmost Set Bit
Step 1

XOR all numbers.

        xor = a ^ b

where a and b are the two unique numbers.

        Since:

x ^ x = 0

all duplicate numbers cancel out.

        Step 2

Find one differing bit.

mask = xor & (-xor)

This extracts the rightmost set bit.

Step 3

Partition numbers into two groups:

Bit = 0
Bit = 1

Each unique number falls into a different group.

XOR each group separately.

        🚀 Java Solution

 */






class Solution {

    public int[] singleNumber(int[] nums) {

        int xor = 0;

        for (int num : nums)
            xor ^= num;

        int mask = xor & (-xor);

        int a = 0;
        int b = 0;

        for (int num : nums) {

            if ((num & mask) == 0)
                a ^= num;
            else
                b ^= num;
        }

        return new int[]{a, b};
    }
}





/*
Complexity
Time  : O(n)
Space : O(1)
Example
1 2 1 3 2 5

xor

= 3 ^ 5

        = 6

mask

= 6 & -6

        = 2

Partition:

Group1:
        1 1 5

Group2:
        2 2 3

Answer:
        5,3

 */