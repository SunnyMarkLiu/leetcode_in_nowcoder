package evaluate_reverse_polish_notation;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class Solution {
    /**
     * 逆波兰表达式求值
     * <p>
     * 遇到数字入栈，遇到符号出栈，计算结果，结果再入栈
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // 如果 tokens[i] 为数字，入栈
            try {
                int num = Integer.parseInt(tokens[i]);
                stack.push(num);
            } catch (NumberFormatException e) {
                // 为操作符,出栈两个，计算结果，结果入栈，注意计算的顺序
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(calcOperate(num1, num2, tokens[i]));
            }
        }
        return stack.pop();
    }

    private int calcOperate(int num1, int num2, String operator) {
        if (operator.equals("+"))
            return num1 + num2;
        else if (operator.equals("-"))
            return num1 - num2;
        else if (operator.equals("*"))
            return num1 * num2;
        else if (operator.equals("/")) {
                if (num2 == 0)
                    throw new RuntimeException("The dividend cannot be 0");
                return num1 / num2;
            }
        else
            throw new RuntimeException(operator + " is not supported");
    }
}