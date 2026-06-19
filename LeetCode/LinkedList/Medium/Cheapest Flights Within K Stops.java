/*Problem

        Find the cheapest cost from src to dst
        using at most k stops.

        Example:

        n = 4

        flights =
        [[0,1,100],
        [1,2,100],
        [2,3,100],
        [0,3,500]]

        src = 0
        dst = 3
        k = 1

        Output = 500
        🔑 Key Idea — BFS + Cost Tracking

        Normal Dijkstra doesn't directly work because:

        Minimum cost ≠ minimum stops

        We need to track:

        (node, cost, stops)
        🚀 Java Solution

 */







import java.util.*;

class Solution {

    class Pair {
        int node;
        int cost;
        int stops;

        Pair(int node, int cost, int stops) {
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n,
                                 int[][] flights,
                                 int src,
                                 int dst,
                                 int k) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] f : flights) {
            graph[f[0]].add(new int[]{f[1], f[2]});
        }

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(src, 0, 0));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();

            if (curr.stops > k)
                continue;

            for (int[] neighbor : graph[curr.node]) {

                int nextNode = neighbor[0];
                int price = neighbor[1];

                int newCost = curr.cost + price;

                if (newCost < minCost[nextNode]) {

                    minCost[nextNode] = newCost;

                    queue.offer(
                            new Pair(nextNode,
                                    newCost,
                                    curr.stops + 1));
                }
            }
        }

        return minCost[dst] == Integer.MAX_VALUE
                ? -1
                : minCost[dst];
    }
}








/*
Alternative

Can also be solved using:

Bellman-Ford → O(KE)
Modified Dijkstra
Complexity
Time  : O(E + V)
Space : O(V + E)

(Modified BFS version)

        🔥 Interview Summary
Problem	Pattern
Course Schedule II	Topological Sort
Cheapest Flights Within K Stops	Graph + BFS + Cost
💡 Key Takeaways
Course Schedule II
Cycle detection + ordering
→ Topological Sort
Cheapest Flights
Need cost + stop count
→ State-based BFS
⚡ Pattern Recognition Guide
Topological Sort

Used in:

Course Schedule I & II
Alien Dictionary
Task Scheduling
Dependency Resolution
Graph with Constraints

Used in:

Cheapest Flights Within K Stops
Network Delay Time
Minimum Multiplications
Shortest Path with Obstacles

⭐ Both are high-frequency graph problems:

Course Schedule II → Topological Sort
Cheapest Flights → Modified Shortest Path

 */