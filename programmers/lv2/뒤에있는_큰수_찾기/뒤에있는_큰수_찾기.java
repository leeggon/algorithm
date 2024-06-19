package programmers.lv2.뒤에있는_큰수_찾기;

import java.util.*;

class 뒤에있는_큰수_찾기 {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        Arrays.fill(result, -1);

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, numbers[0]});
        for (int i=1; i<numbers.length; i++) {
            int curNum = numbers[i];

            while (!stack.isEmpty()) {
                if (stack.peek()[1] < curNum) {
                    int[] peek = stack.pop();
                    result[peek[0]] = curNum;
                } else {
                    break;
                }
            }

            stack.push(new int[]{i, curNum});
        }


        return result;
    }
}
