package programmers.algorithm.level2.하노이의_탑;

import java.util.*;

class 하노이의_탑 {
    List<int[]> list = new ArrayList<>();

    public List<int[]> solution(int n) {
        hanoi(n, 1, 2, 3);

        return list;
    }

    private void hanoi(int x, int s, int m, int a) {
        int[] move = {s, a};

        if (x == 1) {
            list.add(move);
            return;
        }

        hanoi(x-1, s, a, m);
        list.add(move);
        hanoi(x-1, m, s, a);
    }
}
