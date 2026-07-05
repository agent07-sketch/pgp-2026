/*Problem

Given an m × n board containing 'X' and 'O':

Capture all regions surrounded by 'X'.
A region is captured by flipping all surrounded 'O' to 'X'.

Example:

Input:

X X X X
X O O X
X X O X
X O X X

Output:

X X X X
X X X X
X X X X
X O X X

The bottom 'O' is not flipped because it is connected to the boundary.

        🔑 Key Idea — Boundary DFS/BFS

Most people think:

        "Find surrounded regions."

Instead, think the opposite:

        "Find regions that cannot be surrounded."

Those are the 'O' cells connected to the boundary.

        Algorithm
Traverse all boundary cells.
Whenever you find an 'O', perform DFS/BFS and mark it as safe ('#').
Traverse the entire board:
        'O' → 'X' (captured)
        '#' → 'O' (restore safe cells)
        🚀 Java Solution (DFS)

 */










class Solution {

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // Left & Right boundaries
        for (int i = 0; i < rows; i++) {

            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }

        // Top & Bottom boundaries
        for (int j = 0; j < cols; j++) {

            dfs(board, 0, j);
            dfs(board, rows - 1, j);
        }

        // Flip captured regions
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (board[i][j] == 'O')
                    board[i][j] = 'X';

                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {

        if (r < 0 || c < 0 ||
                r >= board.length ||
                c >= board[0].length ||
                board[r][c] != 'O')
            return;

        board[r][c] = '#';

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}









/*
Example Walkthrough

Initial board:

X X X X
X O O X
X X O X
X O X X
Step 1: Mark boundary-connected 'O'
X X X X
X O O X
X X O X
X # X X

The bottom 'O' becomes '#'.

Step 2: Flip remaining 'O'
X X X X
X X X X
X X X X
X # X X
Step 3: Restore '#'
X X X X
X X X X
X X X X
X O X X

Final answer ✔️

Complexity
Time  : O(m × n)

Space : O(m × n)   // recursion stack in worst case

Using BFS gives the same time complexity.

🔥 Interview Summary
Problem	Pattern	Complexity
Surrounded Regions	Boundary DFS/BFS (Flood Fill)	O(m × n)
        💡 Key Takeaways
Don't search for surrounded regions.

Search for safe regions.

Boundary 'O'

        ↓

DFS/BFS

↓

Mark safe
Final Traversal
'O'  → 'X'

        '#'  → 'O'
        ⚡ Pattern Recognition Guide
Boundary DFS/BFS

Used in:

Surrounded Regions
Number of Islands
Pacific Atlantic Water Flow
Flood Fill
Walls and Gates
Flood Fill

Used in:

Flood Fill
Max Area of Island
Number of Islands
Surrounded Regions
🎯 Interview Tips
If the problem says:
Cells connected to boundary

➡️ Start DFS/BFS from the boundary, not from the interior.

If the problem says:
Capture enclosed regions

➡️ Mark all boundary-connected cells as safe first, then flip the remaining ones.

 */