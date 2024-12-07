// Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

// You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

 

// Example 1:

// Input: num1 = "11", num2 = "123"
// Output: "134"
// Example 2:

// Input: num1 = "456", num2 = "77"
// Output: "533"
// Example 3:

// Input: num1 = "0", num2 = "0"
// Output: "0"
 

// Constraints:

// 1 <= num1.length, num2.length <= 104
// num1 and num2 consist of only digits.
// num1 and num2 don't have any leading zeros except for the zero itself.

class Solution {
    public String addStrings(String num1, String num2) {
        int n = Integer.MAX_VALUE;
        if (num1.length() > num2.length()) {
            n = num2.length();
        } else {
            n = num1.length();
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder result = new StringBuilder();
        int carry = 0;

        for(int i = 0; i < n; i++) {
            int a = num1.charAt(i) - '0';
            int b = num2.charAt(i) - '0';
            int c = a + b + carry;

            result.append((c % 10));
            carry = c / 10;
        }


        while(n < num1.length()) {
            int c = num1.charAt(n) - '0' + carry;
            result.append(c % 10);
            carry = c / 10;
            n++;
        }

        if (carry == 1) {
            result.append(carry);
        }

        return result.reverse().toString();
    }
}