/*Problem

        Return an order in which all courses can be completed.

        If impossible (cycle exists), return an empty array.

        Example:

        numCourses = 4

        prerequisites =
        [[1,0],[2,0],[3,1],[3,2]]

        Output:
        [0,1,2,3]
        🔑 Key Idea — Topological Sort (Kahn's Algorithm)
        Build graph.
        Compute indegree of every node.
        Start with nodes having indegree = 0.
        Remove them one by one.

        If all nodes are processed:

        No cycle exists

        Otherwise:

        Cycle exists → impossible
        🚀 Java Solution

 */







import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prereq = pre[1];

            graph[prereq].add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            order[index++] = node;

            for (int neighbor : graph[node]) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        return index == numCourses ? order : new int[0];
    }
}





/*
Complexity
Time  : O(V + E)
Space : O(V + E)
Pattern Recognition
Dependency graph + ordering
→ Topological Sort

 */