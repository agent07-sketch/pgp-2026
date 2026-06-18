/*Problem

        Given a board and dictionary words, find all words that can be formed by moving:

        8 directions allowed

        A cell can only be used once in a word.

        🔑 Key Idea — DFS + Backtracking

        For every word:

        Start DFS from matching cells.
        Explore all 8 neighbors.
        Mark visited cells.
        Backtrack after recursion.
        🚀 Java Solution

 */





import java.util.*;

class Solution {

    int[] dx = {-1,-1,-1,0,0,1,1,1};
    int[] dy = {-1,0,1,-1,1,-1,0,1};

    public String[] wordBoggle(char[][] board, String[] dictionary) {

        List<String> result = new ArrayList<>();

        for (String word : dictionary) {

            boolean found = false;

            for (int i = 0; i < board.length && !found; i++) {

                for (int j = 0; j < board[0].length && !found; j++) {

                    if (board[i][j] == word.charAt(0)) {

                        boolean[][] visited =
                                new boolean[board.length][board[0].length];

                        if (dfs(board, word, i, j, 0, visited)) {
                            result.add(word);
                            found = true;
                        }
                    }
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private boolean dfs(char[][] board,
                        String word,
                        int row,
                        int col,
                        int index,
                        boolean[][] visited) {

        if (index == word.length())
            return true;

        if (row < 0 || col < 0 ||
                row >= board.length ||
                col >= board[0].length ||
                visited[row][col] ||
                board[row][col] != word.charAt(index))
            return false;

        visited[row][col] = true;

        for (int k = 0; k < 8; k++) {

            if (dfs(board,
                    word,
                    row + dx[k],
                    col + dy[k],
                    index + 1,
                    visited)) {

                visited[row][col] = false;
                return true;
            }
        }

        visited[row][col] = false;

        return false;
    }
}





/*
Complexity

For dictionary size D, word length L, board size M × N:

Time ≈ O(D × M × N × 8^L)

Can be optimized further using Trie + DFS.

🔥 Interview Summary
Problem	Pattern
Best Time to Buy and Sell Stock with Cooldown	DP State Machine
Word Boggle	DFS + Backtracking
💡 Key Takeaways
Stock with Cooldown
        buy
sell
        rest
Word Boggle
DFS + visited array + backtracking
⚡ Pattern Recognition Guide
Stock Problems
Stock I → Greedy
Stock II → DP
Stock with Cooldown → DP States
Stock with Transaction Fee → DP
Backtracking Grid Problems
Word Search
Word Boggle
Rat in a Maze
N-Queens

⭐ Both are high-frequency interview problems:

Stock with Cooldown → Dynamic Programming
Word Boggle → Backtracking + DFS

 */