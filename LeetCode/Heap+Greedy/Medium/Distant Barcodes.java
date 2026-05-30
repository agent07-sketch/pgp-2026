/*Problem

Rearrange barcodes so that:

        👉 No two adjacent elements are the same

🔑 Core Idea

👉 Always place the most frequent element first

Why?

Because it’s the hardest to place → handle it early

⚡ Strategy (Greedy + Placement)
Count frequency
Sort by frequency (descending)
Fill:
First even indices (0,2,4…)
Then odd indices (1,3,5…)
✅ Java Code

 */





class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : barcodes) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();

        for (int key : map.keySet()) {
            list.add(new int[]{key, map.get(key)});
        }

        // sort by frequency descending
        Collections.sort(list, (a, b) -> b[1] - a[1]);

        int[] result = new int[barcodes.length];
        int index = 0;

        // fill even indices first
        for (int[] pair : list) {
            int num = pair[0];
            int freq = pair[1];

            while (freq > 0) {
                result[index] = num;
                index += 2;

                if (index >= barcodes.length) {
                    index = 1;
                }

                freq--;
            }
        }

        return result;
    }
}






/*
🔍 Example
Input: [1,1,1,2,2,2]

Output: [1,2,1,2,1,2]
        🚨 Key Insight

👉 Filling even positions first ensures separation
👉 This avoids adjacency conflicts automatically

❌ Mistakes
Trying brute-force permutations ❌
Not placing highest frequency first ❌
Using sorting only (fails for duplicates) ❌

 */