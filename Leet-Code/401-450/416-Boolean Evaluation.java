// You are given an expression 'exp' in the form of a string where operands will be : (TRUE or FALSE), and operators will be : (AND, OR or XOR).



// Now you have to find the number of ways we can parenthesize the expression such that it will evaluate to TRUE.



// As the answer can be very large, return the output modulo 1000000007.



// Note :

// ‘T’ will represent the operand TRUE.
// ‘F’ will represent the operand FALSE.
// ‘|’ will represent the operator OR.
// ‘&’ will represent the operator AND.
// ‘^’ will represent the operator XOR.
// Example :

// Input: 'exp’ = "T|T & F".

// Output: 1

// Explanation:
// There are total 2  ways to parenthesize this expression:
//     (i) (T | T) & (F) = F
//     (ii) (T) | (T & F) = T
// Out of 2 ways, one will result in True, so we will return 1.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample Input 1 :
// T^T^F    
// Sample Output 1 :
// 0
// Explanation For Sample Input 1:
// There are total 2  ways to parenthesize this expression:
// (i) (T^T)^(F) = F
// (ii) (T)^(T^F) = F
// Both ways will result in False, so we will return 0.
// Sample Input 2 :
// F|T^F
// Sample Output 2 :
// 2
// Explanation For Sample Input 2:
// For the first test case:
// There are total 2  ways to parenthesize this expression:
// (i) (F|T)^(F) = T
// (ii) (F)|(T^F) = T
// Both ways will result in True, so we will return 2.
// Expected time complexity
// The expected time complexity is O(n ^ 3), where 'n' denotes the length of 'exp'.
// Constraints:
// 3 <= |‘exp’| <= 200
// Where |'exp'| denotes the length of 'exp'.

// Time Limit: 1 sec

// recurison and memoisation
public class Solution {
    static final int MOD = 1000000007;
    public static int evaluateExp(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        return (int)recursion(exp, 0, n - 1, 1, dp);
    }

    private static long recursion(String exp, int i, int j, int isTrue, long[][][] dp) {
        if (i > j) return 0;

        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != 0) return dp[i][j][isTrue];

        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = recursion(exp, i, ind - 1, 1, dp);
            long lF = recursion(exp, i, ind - 1, 0, dp);
            long rT = recursion(exp, ind + 1, j, 1, dp);
            long rF = recursion(exp, ind + 1, j, 0, dp);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }
}

// tabulation
public class Solution {
    static final int MOD = 1000000007;
    public static int evaluateExp(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n + 1][n + 1][2];

        for (int i = 0; i < n; i++) {
            dp[i][i][0] = exp.charAt(i) == 'F' ? 1 : 0;
            dp[i][i][1] = exp.charAt(i) == 'T' ? 1 : 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    long ways = 0;
                    for (int ind = i + 1; ind <= j - 1; ind += 2) {
                        long lT = dp[i][ind - 1][1];
                        long lF = dp[i][ind - 1][0];
                        long rT = dp[ind + 1][j][1];
                        long rF = dp[ind + 1][j][0];

                        char operator = exp.charAt(ind);
                        if (operator == '&') {
                            if (isTrue == 1) {
                                ways = (ways + (lT * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                            }
                        } else if (operator == '|') {
                            if (isTrue == 1) {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rF) % MOD) % MOD;
                            }
                        } else {
                            if (isTrue == 1) {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                            }
                        }

                        dp[i][j][isTrue] = ways;
                    }
                }
            }
        }
        return (int)dp[0][n - 1][1];
    }

    private static long recursion(String exp, int i, int j, int isTrue, long[][][] dp) {
        if (i > j) return 0;

        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != 0) return dp[i][j][isTrue];

        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = recursion(exp, i, ind - 1, 1, dp);
            long lF = recursion(exp, i, ind - 1, 0, dp);
            long rT = recursion(exp, ind + 1, j, 1, dp);
            long rF = recursion(exp, ind + 1, j, 0, dp);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }
}