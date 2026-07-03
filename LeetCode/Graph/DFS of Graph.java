/*Problem

        Given a graph represented as an adjacency list, perform a Depth First Search (DFS) starting from vertex 0.

        Example:

        0 ---- 1
        |      |
        |      |
        2 ---- 3

        DFS:
        0 1 3 2
        🔑 Key Idea — Recursion

        DFS explores as deep as possible before backtracking.

        Algorithm:

        Visit current node.
        Mark it visited.
        Visit all unvisited neighbors recursively.
        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        dfs(0, adj, visited, result);

        return result;
    }

    private void dfs(int node,
                     ArrayList<ArrayList<Integer>> adj,
                     boolean[] visited,
                     ArrayList<Integer> result) {

        visited[node] = true;
        result.add(node);

        for (int neighbor : adj.get(node)) {

            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, result);
            }
        }
    }
}



/*
Iterative DFS (Using Stack)
Stack<Integer> stack = new Stack<>();
stack.push(0);

while(!stack.isEmpty()){

int node = stack.pop();

    if(visited[node])
        continue;

visited[node] = true;

        for(int next : adj.get(node))
        stack.push(next);
}
Complexity
Time  : O(V + E)

Space : O(V)
Pattern Recognition
Need to explore entire graph
↓

DFS / BFS

 */