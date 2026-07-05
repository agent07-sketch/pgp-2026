/*Problem

Count the number of islands in a 2D grid.

1 = Land

0 = Water

Example:

        1 1 0 0

        1 0 0 1

        0 0 1 1

Answer = 2
        🔑 Key Idea — DFS Flood Fill

Whenever we find a land cell:

grid[i][j] == '1'

we:

Count one island.
DFS to mark all connected land as visited.
        🚀 Java Solution

 */








class Solution {

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int islands = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == '1') {

                    islands++;

                    dfs(grid, i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, int r, int c) {

        if (r < 0 || c < 0 ||
                r >= grid.length ||
                c >= grid[0].length ||
                grid[r][c] == '0')
            return;

        grid[r][c] = '0';

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}







/*
Complexity
Time  : O(m × n)

Space : O(m × n)   // recursion stack (worst case)
BFS Solution (Alternative)
Queue<int[]> queue = new LinkedList<>();

queue.offer(new int[]{i, j});

        while(!queue.isEmpty()){

int[] cell = queue.poll();

// explore four directions
}

DFS and BFS are both accepted.

        🔥 Interview Summary
Problem	Pattern	Complexity
DFS of Graph	Graph Traversal (DFS)	O(V + E)
Number of Islands	Grid DFS / Flood Fill	O(m × n)
💡 Key Takeaways
DFS of Graph
        visited[]

↓

Recursive DFS
Number of Islands
Found '1'

        ↓

DFS

↓

Mark entire island visited
⚡ Pattern Recognition Guide
DFS on Graph

Used in:

DFS of Graph
Cycle Detection
Connected Components
Course Schedule (DFS)
Topological Sort (DFS)
Flood Fill / Grid DFS

Used in:

Number of Islands
Flood Fill
Max Area of Island
Surrounded Regions
Rotten Oranges (BFS preferred)
Pacific Atlantic Water Flow
🎯 Interview Tips
When you see:
Connected components

➡️ Think DFS/BFS.

When you see:
Grid with 1s and 0s
Count connected regions

➡️ Think Flood Fill (DFS/BFS).

Traversal Rule

For graph problems:

visited[] array

For grid problems:

Mark visited by:
        - separate visited[][]
OR
- modifying the grid itself

 */