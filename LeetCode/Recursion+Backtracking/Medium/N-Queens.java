/*Problem

        Place n queens such that:

        No two queens attack each other
        🔑 Key Idea — Backtracking + Safety Check

        We track:

        Columns
        Diagonals
        Anti-diagonals
        🚀 Java Solution

 */



import java.util.*;

class Solution {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag = new HashSet<>();
        Set<Integer> antiDiag = new HashSet<>();

        backtrack(0, n, board, result, cols, diag, antiDiag);

        return result;
    }

    private void backtrack(int row, int n, char[][] board,
                           List<List<String>> res,
                           Set<Integer> cols,
                           Set<Integer> diag,
                           Set<Integer> antiDiag) {

        if (row == n) {
            res.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {

            if (cols.contains(col) ||
                    diag.contains(row - col) ||
                    antiDiag.contains(row + col))
                continue;

            // place queen
            board[row][col] = 'Q';
            cols.add(col);
            diag.add(row - col);
            antiDiag.add(row + col);

            backtrack(row + 1, n, board, res, cols, diag, antiDiag);

            // remove queen
            board[row][col] = '.';
            cols.remove(col);
            diag.remove(row - col);
            antiDiag.remove(row + col);
        }
    }

    private List<String> construct(char[][] board) {

        List<String> list = new ArrayList<>();

        for (char[] row : board) {
            list.add(new String(row));
        }

        return list;
    }
}


/*
Complexity
Time  : O(N!)
Space : O(N)
🧠 Why it works
Each row → choose valid column
Prune invalid positions early
🔥 Interview Summary
Problem	Pattern
Restore IP	Backtracking + String Partition
N-Queens	Backtracking + Constraints
💡 Key Takeaways
Restore IP
Max 4 parts → prune early
Check range + leading zero
N-Queens
Use sets for:
columns
        diagonals
anti-diagonals


⚡ Backtracking Pattern (Final Form)
for (choice) {

        if (valid) {
make choice
recurse
undo choice
    }
            }

 */