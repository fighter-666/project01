package com.example.java;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        // 创建一个栈来存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 如果是左括号，推入栈中
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 如果是右括号，检查栈是否为空或栈顶的左括号是否与之匹配
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        // 如果栈为空，说明所有左括号都找到了匹配的右括号
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        String input = "()[]{}";
        boolean result = vp.isValid(input);
        System.out.println("The input string \"" + input + "\" is valid: " + result);
    }
}
