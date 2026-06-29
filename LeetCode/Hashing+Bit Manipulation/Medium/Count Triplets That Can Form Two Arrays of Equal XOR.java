/*Problem

Count triplets (i, j, k) such that:

        0 <= i < j <= k < n

        and

arr[i] ^ ... ^ arr[j-1] == arr[j] ^ ... ^ arr[k]
        🔑 Key Observation

Let:

a = arr[i] ^ ... ^ arr[j-1]
b = arr[j] ^ ... ^ arr[k]

If:

a == b

        then

a ^ b = 0

which means

arr[i] ^ arr[i+1] ^ ... ^ arr[k] = 0

So whenever the XOR from i to k is 0, every j between them is valid.

Number of valid j:

k - i
🚀 O(n²) Java Solution

 */








class Solution {

    public int countTriplets(int[] arr) {

        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {

            int xor = 0;

            for (int k = i; k < n; k++) {

                xor ^= arr[k];

                if (xor == 0) {
                    count += (k - i);
                }
            }
        }

        return count;
    }
}







/*
Complexity
Time  : O(n²)
Space : O(1)
🧠 Example
arr = [2,3,1,6,7]

i = 0

        2^3^1 = 0

k = 2

Possible j:

        1
        2

Count += 2

 */