/*Problem

        Given a graph represented as an adjacency list, perform Breadth First Search (BFS) starting from vertex 0.

        Example:

        0
        / \
        1   2
        /
        3

        BFS:
        0 1 2 3
        🔑 Key Idea — Queue

        Unlike DFS, BFS explores level by level.

        Algorithm:

        Push starting node into queue.
        Mark it visited.
        Pop node.
        Visit all unvisited neighbors.
        Repeat.
        🚀 Java Solution

 */






import java.util.*;

class Solution {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {

            int node = queue.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }
}





/*
Example

        Graph

0 → 1
        ↓
        2

        1 → 3

Queue evolution

Queue

0

        ↓

        1 2

        ↓

        2 3

        ↓

        3

        ↓

Empty

        Traversal

0 1 2 3
Complexity
Time  : O(V + E)

Space : O(V)

 */