package programmers.algorithm.level3.인사고과;

import java.util.*;

class 인사고과 {
    public int solution(int[][] scores) {
        int answer = 0;

        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int maxCoScore = scores[0][1];
        for (int i=1; i<scores.length; i++) {
            int[] score = scores[i];

            if (score[1] < maxCoScore) {
                if (score[0] == wanhoA && score[1] == wanhoB) {
                    return -1;
                }

                score[0] = -1;
                score[1] = -1;
            } else {
                maxCoScore = score[1];
            }
        }

        for (int[] score : scores) {
            if (score[0] + score[1] > wanhoA + wanhoB) answer++;
        }

        return answer + 1;
    }
}
