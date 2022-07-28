// You are climbing a staircase. It takes n steps to reach the top.

// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

// Example 1:

// Input: n = 2
// Output: 2
// Explanation: There are two ways to climb to the top.
// 1. 1 step + 1 step
// 2. 2 steps
// Example 2:

// Input: n = 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step
 

// Constraints:

// 1 <= n <= 45

class Solution {    
    public int climbStairs(int n) {
        int n1 = 1;
        int n2 = 0;
        int i = 0, climb = 0;
        while(i < n) {
            climb = n1 + n2;
            n2 = n1;
            n1 = climb;
            i++;
        }
        return climb;
    }
}