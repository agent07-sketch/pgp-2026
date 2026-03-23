/*Problem

Multiply two non-negative numbers given as strings (no direct integer conversion).

Example:

Input:  "123", "456"
Output: "56088"
        🔑 Key Idea (Grade School Multiplication)

We simulate multiplication like we do manually:

        123
        ×  456
        -------
        738
        615
        492
        -------
        56088

Use an array of size n + m to store results.
 */

class Solution {
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0"))
            return "0";

        int n = num1.length();
        int m = num2.length();

        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                int sum = mul + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.toString();
    }
}
/*
Complexity
Time  : O(n * m)
Space : O(n + m)

 */