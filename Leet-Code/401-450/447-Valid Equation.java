// Validate if the equation is syntactically correct.


// Valid operators: +, -, a-z, (, )
// Test cases:
// Valid - a + x = b + (c + a)
// Invalid - a + x = (ending with =; doesn't have RHS)
// Invalid - a + -x = a + b (- in -x is a unary operator)

import java.util.*;

public class ValidEquation {
    public static void main(String[] args) {
        String[] input = new String[]{"a + x = b + (c + a)", "a + x = ", "a + -x = a + b",
        "a + x = b + ( z - ( a + w ) )", "a + x = b + ( z ( a + w ) )",
        "(a + x = b + z - ( a + w ) )", "a + x = b + z - ( a + w ) )",
        "a + x = b + z = c + y", "", null, "(a + x) = (b + z - ( a + w ) )"};
        
        for (String str: input) {
            System.out.println(str + " : " + isEquationValid(str));
        }
    }
    
    private static boolean isEquationValid(String equation) {
        if (equation == null || equation.trim().length() == 0) return false;
        
        String[] parts = equation.split("=");
        
        if (parts.length != 2) return false;
        
        String lhs = parts[0].trim();
        String rhs = parts[1].trim();
        
        return isValidExpression(lhs) && isValidExpression(rhs);
    }
    
    private static boolean isValidExpression(String expression) {
        if (expression.length() == 0) return false;
        Stack<Character> validParenthesis = new Stack<>();
        
        boolean expectOperand = true;
        
        for (char ch: expression.toCharArray()) {
            // System.out.println(ch + " : " + expectOperand);
            if (ch == ' ') continue;
            if (ch == '(') {
                if (!expectOperand) return false;
                validParenthesis.push(ch);
            } else if (ch == ')') {
                if (validParenthesis.isEmpty() || expectOperand) return false;
                validParenthesis.pop();
            } else if (ch >= 'a' && ch <= 'z') {
                if (!expectOperand) return false;
                expectOperand = false;
            } else {
                if (expectOperand) return false;
                expectOperand = true;
            }
        } 
        
        return validParenthesis.isEmpty() && !expectOperand;
    }
}