// Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.

// Note that operands in the returned expressions should not contain leading zeros.

 

// Example 1:

// Input: num = "123", target = 6
// Output: ["1*2*3","1+2+3"]
// Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
// Example 2:

// Input: num = "232", target = 8
// Output: ["2*3+2","2+3*2"]
// Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
// Example 3:

// Input: num = "3456237490", target = 9191
// Output: []
// Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 

// Constraints:

// 1 <= num.length <= 10
// num consists of only digits.
// -231 <= target <= 231 - 1

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;

        backtrack(num, res, target, "", 0, 0, 0);
        return res;
    }

    private void backtrack(String num, List<String> res, int target, String path, int i, long eval, long prevVal) {
        if (i == num.length()) {
            if (eval == target) {
                res.add(path);
            }
            return;
        }

        for (int j = i; j < num.length(); j++) {
            if (i != j && num.charAt(i) == '0') break;
            long cur = Long.parseLong(num.substring(i, j + 1));

            if (i == 0) {
                backtrack(num, res, target, path + cur, j + 1, cur, cur);
            } else {
                backtrack(num, res, target, path + "+" + cur, j + 1, eval + cur, cur);

                backtrack(num, res, target, path + "-" + cur, j + 1, eval - cur, -cur);

                backtrack(num, res, target, path + "*" + cur, j + 1, eval - prevVal + prevVal * cur, prevVal * cur);
            }
        }
    }
}