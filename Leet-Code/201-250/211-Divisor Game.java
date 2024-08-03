// Alice and Bob take turns playing a game, with Alice starting first.

// Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:

// Choosing any x with 0 < x < n and n % x == 0.
// Replacing the number n on the chalkboard with n - x.
// Also, if a player cannot make a move, they lose the game.

// Return true if and only if Alice wins the game, assuming both players play optimally.

 

// Example 1:

// Input: n = 2
// Output: true
// Explanation: Alice chooses 1, and Bob has no more moves.
// Example 2:

// Input: n = 3
// Output: false
// Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 

// Constraints:

// 1 <= n <= 1000

// Mathematical Solution
class Solution {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}

// Using Memoization and Recursion
class Solution {
    private boolean divisorGame(int n, Map<Integer, Boolean> memo) {
        if (n == 1) {
            memo.put(n, false);
            return false;
        }
        if (n == 2) {
            memo.put(n, true);
            return true;
        }
        for(int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                memo.put(n, !divisorGame(n - i, memo));
                return memo.get(n);
            }
        }
        return false;
    }

    public boolean divisorGame(int n) {
        return divisorGame(n, new HashMap<>());
    }
}