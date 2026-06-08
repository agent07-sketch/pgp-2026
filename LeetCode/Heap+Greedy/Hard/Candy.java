/*Problem

Each child has a rating.

        Rules:

Every child gets at least 1 candy.
Child with higher rating than adjacent child gets more candies.

Return the minimum candies required.

        Example:

ratings = [1,0,2]
Output = 5

Candies = [2,1,2]
        🔑 Key Idea — Two Pass Greedy
Left → Right

If:

ratings[i] > ratings[i-1]

Then:

candies[i] = candies[i-1] + 1
Right → Left

If:

ratings[i] > ratings[i+1]

Then:

candies[i] = max(candies[i], candies[i+1] + 1)
🚀 Java Solution

 */





class Solution {
    public int candy(int[] ratings) {

        int n = ratings.length;

        int[] candies = new int[n];

        // everyone gets at least 1 candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        // left -> right
        for (int i = 1; i < n; i++) {

            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // right -> left
        for (int i = n - 2; i >= 0; i--) {

            if (ratings[i] > ratings[i + 1]) {
                candies[i] =
                        Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;

        for (int candy : candies)
            total += candy;

        return total;
    }
}




/*
Complexity
Time  : O(n)
Space : O(n)
🧠 Why it works

Example:

ratings:
        1 2 3 2 1

left pass:
        1 2 3 1 1

right pass:
        1 2 3 2 1
Pattern Recognition
Local constraints
Need minimum assignment

→ Two-pass Greedy

 */