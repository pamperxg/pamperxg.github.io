package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesis {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            // 遇见左括号推入栈中
            if (isLeft(c)) {
                stack.push(c);
            } else {
                // 如果不为空且在在栈中有对应的匹配括号
                if (!stack.isEmpty() && leftOf(c) == stack.peek()) {
                    stack.poll();
                } else {
                    // 当前括号未匹配上
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private char leftOf(char c) {
        if (c =='}') return '{';
        if (c == ']') return '[';
        return '(';
    }
}
