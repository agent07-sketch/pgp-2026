/*Idea

        Maximize minimum distance

        Search Space
        low  = 1
        high = max - min

 */

import java.util.*;

class Solution {
    public int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int low = 1;
        int high = position[position.length - 1] - position[0];
        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canPlace(position, m, mid)) {
                ans = mid;
                low = mid + 1;  // maximize
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canPlace(int[] pos, int m, int dist) {

        int count = 1;
        int last = pos[0];

        for (int i = 1; i < pos.length; i++) {
            if (pos[i] - last >= dist) {
                count++;
                last = pos[i];
            }
            if (count >= m) return true;
        }

        return false;
    }
}