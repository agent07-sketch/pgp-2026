/*Problem

        You start with startFuel liters and need to reach target.

        Each station is:

        [position, fuel]

        When you reach a station, you may take all its fuel.

        Return:

        Minimum number of refueling stops needed.
        -1 if impossible.
        Example
        target = 100
        startFuel = 10

        stations =
        [[10,60],[20,30],[30,30],[60,40]]

        Output = 2
        🔑 Key Idea — Greedy + Max Heap

        Instead of deciding where to stop beforehand, we:

        Keep driving as far as possible.
        Add all reachable stations into a max heap.
        If we run out of fuel:
        Take fuel from the station with the largest fuel amount among previously passed stations.

        This greedy choice minimizes the number of stops.

        Visualization
        Fuel = 10

        Reach station 10 → add 60

        Fuel exhausted?
        Yes → take largest available = 60

        Continue...
        ✅ Java Solution

 */



import java.util.*;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        int fuel = startFuel;
        int i = 0;
        int stops = 0;

        while (fuel < target) {

            // add all reachable stations
            while (i < stations.length && stations[i][0] <= fuel) {
                maxHeap.offer(stations[i][1]);
                i++;
            }

            // no station available -> impossible
            if (maxHeap.isEmpty())
                return -1;

            fuel += maxHeap.poll();
            stops++;
        }

        return stops;
    }
}













/*
🧠 Example
        target = 100
startFuel = 10

Reach:
Station 10 → add 60

Fuel exhausted:
Take 60

Now fuel = 70

Reach:
        20 → add 30
        30 → add 30
        60 → add 40

Need more fuel:
Take 40

Fuel ≥ 100

Answer = 2
Complexity
Time  : O(n log n)
Space : O(n)
🔥 Pattern Recognition

Whenever you see:

        "Take best among previously available choices"

Think:

Greedy + Max Heap
Similar Problems
Problem	Pattern
Minimum Refueling Stops	Max Heap
Course Schedule III	Max Heap
IPO	Max Heap
Maximum Performance of a Team	Heap + Greedy
Furthest Building You Can Reach	Min Heap

 */