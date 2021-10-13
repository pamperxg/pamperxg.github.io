package leetcode.stack;

import jdk.nashorn.internal.ir.CaseNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    /**
     * s = "3+2*2"  7
     * s = "3/2"  1
     */
    public int calculate(String s) {
        // 栈中所有的数字
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            // 当前字符为数字，记录当前数字
            if (Character.isDigit(s.charAt(i))) {
               num = num * 10 + s.charAt(i) - '0';
            }
            // 当前字符非数字，且为运算符，进行运算及入栈操作，并记录下操作符
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num); // 弹出上一个数并做运算
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                preSign = s.charAt(i);
            }
        }
        return stack.stream().mapToInt(x -> x).sum();
    }

    /**
     * s = "(1+(4+5+2)-3)+(6+8)"
     */
    public int calculate1(String s) {
        // 括号内的和、当前数字、符号
        int res = 0, num = 0, sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            // 如果是数字
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
                if (i < n - 1 && Character.isDigit(s.charAt(i + 1))) {
                    continue;
                }
                // 遇到+-号进行符号更新
            } else if (ch == '+' || ch == '-') {
                num = 0;  // 新数字！！
                sign = ch == '+' ? 1 : -1;
                // 遇见括号将符号和括号中的和入栈
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;  // 新括号范围
                sign = 1;
                // 遇见括号将符号和结果出栈
            } else if (ch == ')') {
                sign = stack.pop();
                num = res; // 括号中的计算结果
                res = stack.pop(); // 括号前的计算结果
            }
            res += sign * num;
        }
        return res;
    }
}
