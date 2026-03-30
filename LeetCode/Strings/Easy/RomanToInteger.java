/*Problem

        Convert Roman numeral to integer.

        Example:

        Input:  "MCMXCIV"
        Output: 1994
        🔑 Key Idea

        Roman rules:

        Normally: add values

        Exception: if smaller value comes before larger → subtract

        IV = 5 - 1 = 4
        IX = 10 - 1 = 9
        🧠 Trick

        Traverse from right to left:

        If current < next → subtract

        Else → add
*/



import java.util.*;

class Solution {
    public int romanToInt(String s) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {

            int curr = map.get(s.charAt(i));

            if (i < s.length() - 1 && curr < map.get(s.charAt(i + 1))) {
                sum -= curr;
            } else {
                sum += curr;
            }
        }

        return sum;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

 */