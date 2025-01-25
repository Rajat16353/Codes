// Given an array of non-negative integers, the goal is to travel from the first index to the last index with maximum possible score with as many jumps allowed. Score of a jump is defined as the number of index jumped multiplied by the value on the jumped index.
// e.g. [3,7,9,10]


// if the jump is from index0 to index2, the score is (2-0)*9 = 18


// Sample input: [3,12,9,10]
// Sample output: 32
// Explanation: jump from index0 to index1 and then to index3 = (1-0) * 12 + (3-1) * 10 = 12 + 20 = 32


// What are the possible ways to approach this problem? Can it be done in better than O(n^2) ?

// DP solution O(n^2)
public class MaximumScore {
    public static void main(String[] args) {
        int[][] input = new int[][]{{3,7,9,10}, {3,12,9,10}};
        
        
        for (int i = 0; i < input.length; i++) {
            int maxScore = findMaxScore(input[i]);
            System.out.println(maxScore);
        }
    }
    
    private static int findMaxScore(int[] input) {
        int n = input.length;
        Integer[] dp = new Integer[n];
        
        return recursion(0, n, input, dp);
    }
    
    private static int recursion(int index, int n, int[] nums, Integer[] dp) {
        if (index == n - 1) {
            return 0;
        }
        
        if (dp[index] != null) return dp[index];
        
        int maxScore = 0;
        for (int i = index + 1; i < n; i++) {
            maxScore = Math.max(maxScore, (i - index) * nums[i] + recursion(i, n, nums, dp));
        }
        
        return dp[index] = maxScore;
    }
}

// O(n) solution
public class MaximumScore {
    public static void main(String[] args) {
        int[][] input = new int[][]{{3,7,9,10}, {3,12,9,10}};
        
        
        for (int i = 0; i < input.length; i++) {
            int maxScore = findMaxScore(input[i]);
            System.out.println(maxScore);
        }
    }
    
    private static int findMaxScore(int[] input) {
        int n = input.length;
        int maxSeenSoFar = -1;
        int result = 0;
        
        for (int i = n - 1; i > 0; i--) {
            maxSeenSoFar = Math.max(maxSeenSoFar, input[i]);
            result += maxSeenSoFar;
        }
        
        return result;
    }
}