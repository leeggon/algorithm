package swea.D4.SW문제해결기본_6일차_계산기3;

import java.io.*;
import java.util.*;

public class SW문제해결기본_6일차_계산기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc=0; tc<10; tc++) {
            int len = Integer.parseInt(br.readLine());
            String inFix = br.readLine();
            String postFix = toPostFix(inFix);
            int answer = calculate(postFix);

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static String toPostFix(String inFix) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<inFix.length(); i++) {
            char c = inFix.charAt(i);

            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (c == '(') {
                    stack.add(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && stack.peek() != '(' && getPriority(stack.peek()) >= getPriority(c)) {
                        sb.append(stack.pop());
                    }
                    stack.add(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static int calculate(String postFix) {
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<postFix.length(); i++) {
            char c = postFix.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int a = stack.pop();
                int b = stack.pop();
                int result = operate(a, b, c);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static int operate (int a, int b, char c) {
        if (c == '*') return a*b;
        if (c == '/') return a/b;
        if (c == '+') return a+b;
        return a-b;
    }

    private static int getPriority (char c) {
        if (c == '*' || c == '/') return 1;
        else return 0;
    }
}
