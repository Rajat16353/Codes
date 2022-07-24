// You are given Chef's calendar for the next N days, defined as a binary string S of length N where Si=0 means that Chef has a holiday on the ith day from now, and Si=1 means that Chef has to work on that day.

// Chef wants to plan his vacations. For each vacation, Chef needs X consecutive holidays in his calendar. Obviously, he can only go on one vacation at a time.

// Chef can take at most one extra holiday. That is, he can flip at most one digit in S from 1 to 0. If he does this optimally, what is the maximum number of vacations that he can go on?

// Input Format
// The first line of input contains a single integer T, denoting the number of test cases. The description of T test cases follows.
// The first line of each test case contains two space-separated integers N and X.
// The second line of each test case contains a binary string S of length N — Chef's schedule.
// Output Format
// For each test case, output on a new line the answer — the maximum number of vacations Chef can take if he takes at most one more extra holiday.

// Constraints
// 1≤T≤1000
// 1≤N≤2⋅105
// 1≤X≤N
// The sum of N across all test cases does not exceed 2⋅105
// Sample Input 1 
// 3
// 7 2
// 0010001
// 4 3
// 1010
// 5 2
// 00100
// Sample Output 1 
// 3
// 1
// 2
// Explanation
// Test Case 1: Chef can flip the 3rd digit to make his calendar 0000001. This allows him to take 3 vacations in the first 6 days.

// Test Case 2: Chef can flip the 3rd digit to make his calendar 1000. This allows him to take one vacation using the last 3 days.

// Test Case 3: Regardless of whether Chef flips the 3rd digit or not, he can take at most 2 vacations.

/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc = new Scanner(System.in);
	    Solution sol = new Solution();
	    
		int t = sc.nextInt();
		for (int i = 0; i< t; i++) {
		    int n = sc.nextInt();
		    int x = sc.nextInt();
		    String s = sc.next();
		    System.out.println(sol.getMaxVacations(s, n ,x));
		}
	}
}

class Solution {
    public int getMaxVacations(String s, int n, int x) {
        int next1[] = new int[n];
        int vacCount[] = new int[n+1];
        int count0 = 0, k = 0, maxVac = 0, vac = 0, pos1 = n;
        
        for(int i = n-1; i>=0; i--) {
            next1[i] = pos1;
            if (s.charAt(i) == '1') {
                count0 = 0;
                pos1 = i;
            } else {
                count0++;
                if (count0 == x) {
                    count0 = 0;
                    vac++;
                }
            }
            vacCount[i] = vac;
        }
        
        maxVac = vacCount[0];
        count0 = 0;
        vac = 0;
        for(int i = 0; i<n; i++) {
            if (s.charAt(i) == '1') {
                if (next1[i] > i+x-count0-1) {
                    maxVac = Math.max(maxVac, vac+vacCount[i+x-count0]+1);
                }
                count0 = 0;
            } else {
                count0++;
                if (count0 == x) {
                    vac++;
                    count0 = 0;
                }
            }
        }
        
        return maxVac;
    }
}