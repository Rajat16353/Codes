// You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

// Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.

 

// Example 1:

// Input: equations = ["a==b","b!=a"]
// Output: false
// Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
// There is no way to assign the variables to satisfy both equations.
// Example 2:

// Input: equations = ["b==a","a==b"]
// Output: true
// Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 

// Constraints:

// 1 <= equations.length <= 500
// equations[i].length == 4
// equations[i][0] is a lowercase letter.
// equations[i][1] is either '=' or '!'.
// equations[i][2] is '='.
// equations[i][3] is a lowercase letter.

class Solution {
    int[] parent;

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];

        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        for (String eq: equations) {
            if (eq.charAt(1) == '=') {
                union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }

        for (String eq: equations) {
            if (eq.charAt(1) == '!') {
                int lx = find(eq.charAt(0) - 'a');
                int ly = find(eq.charAt(3) - 'a');

                if (lx == ly) return false;
            }
        }

        return true;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int lx = find(x);
        int ly = find(y);

        if (lx != ly) {
            parent[lx] = ly;
        }
    }
}