/*Problem

        Two strings are isomorphic if:

        Each character in s maps to exactly one character in t

        Mapping is one-to-one

        Example:

        Input:  s = "egg", t = "add"
        Output: true

        Input:  s = "foo", t = "bar"
        Output: false
        🔑 Key Idea

        We need bidirectional mapping:

        s → t
        t → s

        Because:

        "a → x" and "b → x" ❌ (not allowed)
 */



import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // check s -> t
            if (mapST.containsKey(c1)) {
                if (mapST.get(c1) != c2)
                    return false;
            } else {
                mapST.put(c1, c2);
            }

            // check t -> s
            if (mapTS.containsKey(c2)) {
                if (mapTS.get(c2) != c1)
                    return false;
            } else {
                mapTS.put(c2, c1);
            }
        }

        return true;
    }
}
⚡ Optimized Approach (Using Arrays)
class Solution {
    public boolean isIsomorphic(String s, String t) {

        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {

            if (mapS[s.charAt(i)] != mapT[t.charAt(i)])
                return false;

            mapS[s.charAt(i)] = i + 1;
            mapT[t.charAt(i)] = i + 1;
        }

        return true;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Roman to Integer	Mapping + Greedy
Isomorphic Strings	Hashing / Mapping
💡 Key Insights
Roman to Integer
If current < next → subtract
Else → add
Isomorphic Strings
Always check BOTH directions
🧠 Quick Memory Trick
Roman → compare next value
Isomorphic → two maps OR index trick

 */