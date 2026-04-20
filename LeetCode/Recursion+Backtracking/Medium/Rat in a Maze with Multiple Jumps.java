/*Problem
Each cell contains a number → max jump length
From (i,j) you can move right or down up to that value
Reach bottom-right

Example:

maze =
        [ [2,1,0,0],
        [3,0,0,1],
        [0,1,0,1],
        [0,0,0,1] ]
        🔑 Key Idea — Backtracking + Multi-step moves

From each cell:

        try all jumps: 1 → maze[i][j]
        🚀 Java Solution

 */


class Solution {

    public boolean solveMaze(int[][] maze) {

        int n = maze.length;
        int[][] sol = new int[n][n];

        if (solve(maze, 0, 0, sol))
            return true;

        return false;
    }

    private boolean solve(int[][] maze, int i, int j, int[][] sol) {

        int n = maze.length;

        // reached destination
        if (i == n - 1 && j == n - 1) {
            sol[i][j] = 1;
            return true;
        }

        if (isSafe(maze, i, j)) {

            sol[i][j] = 1;

            int maxJump = maze[i][j];

            // move right
            for (int step = 1; step <= maxJump; step++) {
                if (solve(maze, i, j + step, sol))
                    return true;
            }

            // move down
            for (int step = 1; step <= maxJump; step++) {
                if (solve(maze, i + step, j, sol))
                    return true;
            }

            // backtrack
            sol[i][j] = 0;
        }

        return false;
    }

    private boolean isSafe(int[][] maze, int i, int j) {
        int n = maze.length;
        return i < n && j < n && maze[i][j] > 0;
    }
}

        /*
Complexity
Exponential (backtracking)
🧠 Key Insight
Instead of 1 step → try multiple steps
Backtracking explores all paths

         */