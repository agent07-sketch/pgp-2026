/*Problem

        Given:

        times[i] = (u, v, w)

        Signal starts from node k.

        Find the time required for the signal to reach all nodes.

        If impossible:

        return -1
        🔑 Key Idea — Dijkstra's Algorithm

        Weighted graph +

        Need shortest path

        ↓

        Use Priority Queue (Min Heap)

        🚀 Java Solution

 */





import java.util.*;

class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : times) {

            graph[edge[0]].add(new int[]{
                    edge[1],
                    edge[2]
            });
        }

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(
                        (a, b) -> a[1] - b[1]);

        pq.offer(new int[]{k, 0});

        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int node = curr[0];
            int time = curr[1];

            if (time > dist[node])
                continue;

            for (int[] next : graph[node]) {

                int neighbor = next[0];
                int weight = next[1];

                if (dist[neighbor] > time + weight) {

                    dist[neighbor] = time + weight;

                    pq.offer(new int[]{
                            neighbor,
                            dist[neighbor]
                    });
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE)
                return -1;

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}







/*
Complexity
Time  : O((V + E) log V)

Space : O(V + E)
Example
1 →2 (1)

        1 →3 (4)

        2 →3 (2)

Start = 1

Shortest:

        1→2 = 1

        1→2→3 = 3

Answer:

        3
        🔥 Interview Summary
Problem	Pattern	Algorithm
Rotting Oranges	Multi-source BFS	Queue
Network Delay Time	Shortest Path	Dijkstra
💡 Key Takeaways
Rotting Oranges
Multiple starting points

↓

BFS by level

↓

Each level = 1 minute
Network Delay Time
Weighted graph

↓

Shortest path

↓

Priority Queue
⚡ Pattern Recognition Guide
Multi-source BFS

Used in:

Rotting Oranges
Walls and Gates
01 Matrix
Fire Spread
Nearest Exit
Dijkstra

Used in:

Network Delay Time
Path With Minimum Effort
Minimum Cost to Reach Destination
Cheapest Flights (priority queue variant)
Shortest Path in Weighted Graph
🎯 Interview Tips
If the problem says:
Spreads simultaneously

➡️ Think Multi-source BFS.

If it says:
Minimum time/cost
Weighted edges

➡️ Think Dijkstra.

Quick Decision Table
Graph Type	Algorithm
Unweighted shortest path	BFS
Weighted shortest path	Dijkstra
Negative weights	Bellman-Ford
DAG shortest path	Topological Sort

 */