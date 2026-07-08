/*Problem

        A grid contains:

        0 → Empty cell
        1 → Fresh orange
        2 → Rotten orange

        Every minute:

        A rotten orange rots its 4 adjacent fresh oranges.

        Return:

        Minimum minutes to rot all oranges.
        -1 if impossible.

        Example:

        2 1 1
        1 1 0
        0 1 1

        Output:

        4
        🔑 Key Idea — Multi-Source BFS

        Unlike normal BFS (one source),

        Here every rotten orange starts spreading simultaneously.

        So:

        Put all rotten oranges into the queue.
        Count fresh oranges.
        BFS level by level.
        Each level = 1 minute.
        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int fresh = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 2)
                    queue.offer(new int[]{i, j});

                else if (grid[i][j] == 1)
                    fresh++;
            }
        }

        if (fresh == 0)
            return 0;

        int[][] dir = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] cell = queue.poll();

                for (int[] d : dir) {

                    int nr = cell[0] + d[0];
                    int nc = cell[1] + d[1];

                    if (nr >= 0 && nc >= 0 &&
                            nr < rows && nc < cols &&
                            grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;
                        fresh--;

                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}




















/*
Example
Minute 0

        2 1 1
        1 1 0
        0 1 1

        ↓

Minute 1

        2 2 1
        2 1 0
        0 1 1

        ↓

Minute 2

        2 2 2
        2 2 0
        0 1 1

        ↓

Eventually all become rotten.

Complexity
Time  : O(m × n)

Space : O(m × n)
Pattern Recognition
Spread simultaneously

↓

Multi-source BFS

 */