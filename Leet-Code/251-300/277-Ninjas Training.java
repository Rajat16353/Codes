// Coding Ninjas

// Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?

// You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.

// For Example
// If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
// Detailed explanation ( Input/output format, Notes, Images )
// Constraints:
// 1 <= T <= 10
// 1 <= N <= 100000.
// 1 <= values of POINTS arrays <= 100 .

// Time limit: 1 sec

// Top Down
import java.util.Arrays;
public class Solution {
    private static int ninjaTraining(int points[][], int day, int last, int[][] dp) {
        if (day == 0) {
            int maxP = 0;
            for(int task = 0; task < 3; task++) {
                if (task != last) {
                    maxP = Math.max(points[0][task], maxP);
                }
            }
            return maxP;
        }

        if (dp[day][last] != -1) return dp[day][last];

        int maxP = 0;

        for(int task = 0; task < 3; task++) {
            if (task != last)
                maxP = Math.max(points[day][task] + ninjaTraining(points, day - 1, task, dp), maxP);
        }

        dp[day][last] = maxP;
        return maxP;
    }

    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp = new int[n][4];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));

        return ninjaTraining(points, n - 1, 3, dp);
    }

}

// Bottom Up
import java.util.Arrays;

public class Solution {
    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp = new int[n][4];
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                if (i != j) dp[0][i] = Math.max(dp[0][i], points[0][j]);
            }
        }


        for(int day = 1; day < n; day++) {
            for(int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for(int task = 0; task < 3; task++) {
                    if (task != last)
                        dp[day][last] = Math.max(points[day][task] + dp[day - 1][task], dp[day][last]);
                }
            }
        }

        return dp[n - 1][3];
    }

}