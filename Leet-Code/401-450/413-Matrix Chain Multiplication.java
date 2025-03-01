// Given a chain of matrices A1, A2, A3,.....An. Your task is to find out the minimum cost to multiply these matrices. The cost of matrix multiplication is defined as the number of scalar multiplications. A Chain of matrices A1, A2, A3,.....An is represented by a sequence of numbers in an array ‘arr’ where the dimension of 1st matrix is equal to arr[0] * arr[1] , 2nd matrix is arr[1] * arr[2], and so on.

// For example:
// For arr[ ] = { 10, 20, 30, 40}, matrix A1 = [10 * 20], A2 = [20 * 30], A3 = [30 * 40]

// Scalar multiplication of matrix with dimension 10 * 20 is equal to 200.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample Input 1:
// 2
// 4
// 4 5 3 2
// 4
// 10 15 20 25
// Sample Output 1:
// 70
// 8000
// Sample Output Explanation 1:
// In the first test case, there are three matrices of dimensions A = [4 5], B = [5 3] and C = [3 2]. The most efficient order of multiplication is A * ( B * C).
// Cost of ( B * C ) = 5 * 3 * 2 = 30  and (B * C) = [5 2] and A * (B * C) = [ 4 5] * [5 2] = 4 * 5 * 2 = 40. So the overall cost is equal to 30 + 40 =70.

// In the second test case, there are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.

// If we multiply in order- A1*(A2*A3), then the number of multiplications required is 11250.

// If we multiply in order- (A1*A2)*A3, then the number of multiplications required is 8000.

// Thus a minimum number of multiplications required is 8000. 
// Sample Input 2:
// 1
// 4
// 1 4 3 2
// Sample Output 2:
// 18
// Explanation of Sample Output 2:
// In the first test case, there are three matrices of dimensions A = [1 4], B = [4 3] and C = [3 2]. The most efficient order of multiplication is (A *  B) * C .

// Using recurison and memoisation
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int matrixMultiplication(int[] arr , int N) {
		int[][] dp = new int[N][N];
		return recursion(1, N - 1, arr, dp);
	}

	private static int recursion(int i, int j, int[] arr, int[][] dp) {
		if (i == j) return 0;

		if (dp[i][j] != 0) return dp[i][j];
		int mini = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int steps = arr[i - 1] * arr[k] * arr[j] + recursion(i, k, arr, dp) + recursion(k + 1, j, arr, dp);

			if (steps < mini) mini = steps;
		}

		return dp[i][j] = mini;
	}
}

// Using tabulation
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int matrixMultiplication(int[] arr , int N) {
		int[][] dp = new int[N][N];
		
		for (int i = N - 1; i >= 1; i--) {
			for (int j = i + 1; j < N; j++) {
				int mini = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];

					if (steps < mini) mini = steps;
				}

				dp[i][j] = mini;
			}
		}

		return dp[1][N - 1];
	}
}

// Tabulation
class Solution {
    public int minCost(int n, int[] cuts) {
        List<Integer> cutList = new ArrayList<>();
        cutList.add(0);
        Arrays.stream(cuts).forEach(cut -> cutList.add(cut));
        cutList.add(n);
        Collections.sort(cutList);
        int c = cuts.length;
        int cs = cutList.size();
        int[][] dp = new int[cs + 1][cs + 1];
        
        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j) continue;
                int mini = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int len = cutList.get(j + 1) - cutList.get(i - 1) + dp[i][k - 1] + dp[k + 1][j];
                    if (len < mini) mini = len;
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][c];
    }
}