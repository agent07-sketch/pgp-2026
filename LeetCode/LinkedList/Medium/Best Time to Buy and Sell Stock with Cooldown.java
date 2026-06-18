/*Problem

After selling a stock, you must wait 1 day (cooldown) before buying again.

Example:

prices = [1,2,3,0,2]

Output = 3

Transactions:

Buy(1) → Sell(3) → Cooldown → Buy(0) → Sell(2)
🔑 DP States

At index i, we have three possibilities:

        1. Buy State

Currently holding a stock.

        2. Sell State

Just sold a stock.

        3. Cooldown/Rest State

Not holding anything.

State transitions:

buy[i]  = max(buy[i-1], rest[i-1]-price[i])

sell[i] = buy[i-1] + price[i]

rest[i] = max(rest[i-1], sell[i-1])
🚀 Optimized Java Solution (O(1) Space)

 */





class Solution {
    public int maxProfit(int[] prices) {

        int buy = -prices[0];
        int sell = 0;
        int rest = 0;

        for (int i = 1; i < prices.length; i++) {

            int prevSell = sell;

            sell = buy + prices[i];

            buy = Math.max(buy, rest - prices[i]);

            rest = Math.max(rest, prevSell);
        }

        return Math.max(sell, rest);
    }
}




/*
Complexity
Time  : O(n)
Space : O(1)
Pattern Recognition
Stock + restrictions
→ DP State Machine

 */