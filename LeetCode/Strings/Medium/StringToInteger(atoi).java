/*Problem

Convert string → integer with rules:

Ignore leading spaces
Handle + / -
Stop at non-digit
Clamp to:
        [-2^31, 2^31 - 1]
        🔑 Key Idea

Simulate parsing step by step.

 */




class Solution {
    public int myAtoi(String s) {

        int i = 0, n = s.length();

        // 1. skip spaces
        while (i < n && s.charAt(i) == ' ')
            i++;

        // 2. sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-')
                sign = -1;
            i++;
        }

        // 3. parse number
        int result = 0;

        while (i < n && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            // 4. handle overflow
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Reverse Words	String Manipulation
Atoi	Parsing + Edge Cases
💡 Key Takeaways
Reverse Words
Trim → Split → Reverse
        Atoi
Skip spaces → sign → digits → overflow check
⚡ Interview Traps
Atoi Edge Cases
"   -42"        → -42
        "4193 with text"→ 4193
        "words 123"     → 0
        "-91283472332"  → clamp

 */