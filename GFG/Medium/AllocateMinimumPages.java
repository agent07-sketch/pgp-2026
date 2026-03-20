/*
Problem

arr[i] = pages in i-th book

m = students

Allocate books contiguously

Minimize maximum pages assigned

Example
Input:  arr = [12, 34, 67, 90], m = 2
Output: 113

Split:

        [12,34,67] → 113
        [90]       → 90
        🔑 Key Idea — Binary Search on Answer

Search:

low  = max(arr)
high = sum(arr)

For mid = max pages allowed:

Check if allocation possible within m students
*/

class Solution {

    public static int findPages(int[] arr, int m) {

        if (arr.length < m) return -1;

        int low = 0;
        int high = 0;

        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canAllocate(arr, m, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static boolean canAllocate(int[] arr, int m, int maxPages) {

        int students = 1;
        int pages = 0;

        for (int book : arr) {

            if (pages + book <= maxPages) {
                pages += book;
            } else {
                students++;
                pages = book;

                if (students > m)
                    return false;
            }
        }

        return true;
    }
}

/*
Complexity
Time  : O(n log(sum))
Space : O(1)
Interview Pattern Recognition

Both problems use:

Binary Search on Answer
When to use this pattern?

If:

You need minimum/maximum possible value

And you can validate a candidate answer

Template (VERY IMPORTANT)
while(low <= high){
mid = ...

        if(possible(mid)){
ans = mid;
high = mid - 1;  // minimize
        } else {
low = mid + 1;
        }
        }
        💡 Key Takeaway
Problem	Search Space
Multiplication Table	values (1 → m*n)
Allocate Pages	max pages (max → sum)

 */