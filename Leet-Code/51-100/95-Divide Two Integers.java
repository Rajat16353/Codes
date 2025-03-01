// Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

// The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

// Return the quotient after dividing dividend by divisor.

// Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 

// Example 1:

// Input: dividend = 10, divisor = 3
// Output: 3
// Explanation: 10/3 = 3.33333.. which is truncated to 3.
// Example 2:

// Input: dividend = 7, divisor = -3
// Output: -2
// Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 

// Constraints:

// -231 <= dividend, divisor <= 231 - 1
// divisor != 0

// class Solution:
//     def divide(self, dividend: int, divisor: int) -> int:
//         quotient = 0
//         neg = False
//         if (dividend < 0 and divisor >= 0) or (dividend >= 0 and divisor < 0):
//             neg = True
        
//         dividend = abs(dividend)
//         divisor = abs(divisor)
//         for i in range(31, -1, -1):
//             if divisor << i <= dividend:
//                 dividend -= divisor << i
//                 quotient += 1 << i
        
//         if neg:
//             quotient = -quotient;
        
//         return min(max(quotient, -2147483648), 2147483647);

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor) return 1;

        boolean sign = true;
        if (dividend >= 0 && divisor < 0) sign = false;
        else if (dividend < 0 && divisor > 0) sign = false;

        long n = Math.abs((long)dividend);
        long d = Math.abs((long)divisor);
        long ans = 0;

        while (n >= d) {
            int count = 0;

            while (n >= d << (count + 1)) count += 1;

            n = n - (d << count);
            ans += (1L << count);
        }

        if (sign && ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if (!sign && ans > Integer.MAX_VALUE) return Integer.MIN_VALUE;

        return sign ? (int)ans : (int)ans * -1;
    }
}