/*Problem

        Generate the Gray code sequence of n bits.

        Adjacent numbers differ by exactly one bit.

        Example:

        n = 2

        Output:
        [0,1,3,2]

        Binary:

        00
        01
        11
        10
        🔑 Key Idea

        Gray code formula:

        Gray(i) = i ^ (i >> 1)

        where:

        ^ = XOR
        >> = right shift
        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<>();

        int total = 1 << n;

        for (int i = 0; i < total; i++) {

            result.add(i ^ (i >> 1));
        }

        return result;
    }
}




/*
Example

For n = 3

i      Gray

000 -> 000 = 0
        001 -> 001 = 1
        010 -> 011 = 3
        011 -> 010 = 2
        100 -> 110 = 6
        101 -> 111 = 7
        110 -> 101 = 5
        111 -> 100 = 4

Result:

        0
        1
        3
        2
        6
        7
        5
        4
Complexity
Time  : O(2ⁿ)
Space : O(1) (excluding output)
        🔥 Interview Summary
Problem	Pattern
Subsets	Backtracking / Bitmask
Gray Code	Bit Manipulation
💡 Key Takeaways
Subsets
Choose / Don't Choose

or

        Bitmask
Gray Code
Gray = i ^ (i >> 1)
        ⚡ Pattern Recognition Guide
        Backtracking

Used in:

Subsets
Subsets II
Combination Sum
Generate Parentheses
N Queens
Restore IP Addresses
Bit Manipulation

Used in:

Gray Code
Single Number
Counting Bits
Maximum XOR
Power Set (Bitmask)
🎯 Interview Tips
When you see:
Generate all possible combinations

➡️ Think Backtracking.

When you see:
Every element has two choices

➡️ Think Bitmask (1 << n).

When you see:
Adjacent binary values differ by one bit

➡️ Remember the Gray code formula:

Gray(i) = i ^ (i >> 1)

 */