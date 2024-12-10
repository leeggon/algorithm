package programmers.algorithm.level2.디펜스_게임;

import java.util.*;

class 디펜스_게임 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i=0; i<enemy.length; i++) {
            pq.offer(enemy[i]);

            n -= enemy[i];
            if (n < 0) {
                if (k == 0) {
                    return i;
                }

                k--;
                n += pq.poll();
            }
        }

        return enemy.length;
    }
}
