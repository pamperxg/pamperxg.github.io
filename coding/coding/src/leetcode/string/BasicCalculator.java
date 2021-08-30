package leetcode.string;

import java.util.*;

public class BasicCalculator {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
//        System.out.println("Hello World!");
        String s = "3+2*2";
        int res = basicCalculator(s);
        System.out.println(res);
    }

    // 栈 == 递归
    // (1+(4+5+2)-3)+(6+8)  res num  // res += sign * num
    private static int calculate(String s) {
        int res = 0, num = 0, sign = 1;
        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i ++) {
            char ch = s.charAt(i);
            // 如果为空格忽略
            if (ch == ' ') {
                continue;
            }
            // 取数字
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
                if (i < n - 1 && Character.isDigit(s.charAt(i + 1))) {
                    continue;
                }
                // 遇到 + -号时，更新+ - 号
            } else if (ch == '+' || ch == '-') {
                num = 0;
                sign = ch == '+' ? 1 : -1;
                // 遇到( 将符号和数字都入栈, 更新括号间变量
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                // 遇到）将符号出栈，
            } else if (ch == ')') {
                sign = stack.pop();
                num = res; // 括号中计算结果
                res = stack.pop(); // 括号前的计算结果
            }
            // 每遍历一次计算一次累加和
            res += sign * num;
        }
        return res;
    }

    private static int basicCalculator(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; i ++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        return stack.stream().mapToInt(x -> x).sum();
    }
}
