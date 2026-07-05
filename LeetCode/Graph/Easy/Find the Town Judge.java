/*Problem

There are n people.

The judge:

Trusts nobody
Everyone else trusts the judge

Return the judge's label, otherwise return -1.

Example:

n = 3

trust =

        1 → 3
        2 → 3

Answer = 3
        🔑 Key Idea — In-degree & Out-degree

Maintain two arrays:

indegree[i]
outdegree[i]

For every trust relation:

a trusts b

↓

outdegree[a]++

indegree[b]++

Judge must satisfy:

indegree == n-1

outdegree == 0
        🚀 Java Solution

 */





class Solution {

    public int findJudge(int n, int[][] trust) {

        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        for (int[] edge : trust) {

            outdegree[edge[0]]++;
            indegree[edge[1]]++;
        }

        for (int i = 1; i <= n; i++) {

            if (indegree[i] == n - 1 &&
                    outdegree[i] == 0)
                return i;
        }

        return -1;
    }
}






/*
Example
        n = 4

1 → 3
        2 → 3
        4 → 3

Arrays

        Person

1
        2
        3
        4

Indegree

0
        0
        3
        0

Outdegree

1
        1
        0
        1

Judge

        Indegree = 3

Outdegree = 0

Answer = 3
Complexity
Time  : O(n + trust.length)

Space : O(n)
🔥 Interview Summary
Problem	Pattern	Complexity
BFS of Graph	Queue + Graph Traversal	O(V + E)
Find the Town Judge	In-degree / Out-degree	O(n + m)
💡 Key Takeaways
BFS
        Queue

↓

Visit level by level
Town Judge
Judge

↓

Trusted by everyone

Trusts nobody
⚡ Pattern Recognition Guide
        BFS

Used in:

BFS of Graph
Number of Islands (BFS)
Rotten Oranges
Word Ladder
Shortest Path in Unweighted Graph
Binary Tree Level Order Traversal
In-degree / Out-degree

Used in:

Find the Town Judge
Course Schedule
Topological Sort
Eventual Safe States
Prerequisite Problems
🎯 Interview Tips
If the problem says:
Shortest path in an
unweighted graph

➡️ Think BFS.

If it says:
Everyone points to one node

➡️ Think In-degree.

If it says:
Node with no outgoing edge

➡️ Think Out-degree.

📝 DFS vs BFS Cheat Sheet
Feature	DFS	BFS
Data Structure	Stack / Recursion	Queue
Traversal	Depth first	Level by level
Shortest Path	❌	✅ (Unweighted graphs)
Space	O(V)	O(V)
Common Problems	Islands, Cycle Detection, Backtracking	Shortest Path, Level Order, Multi-source BFS

 */