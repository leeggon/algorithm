package programmers.algorithm.level3.합승_택시_요금;

import java.util.*;

class 합승_택시_요금 {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        int[][] cost = new int[n+1][n+1];
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<n+1; j++) {
                if (i == j) continue;
                cost[i][j] = 20000000;
            }
        }

        for (int[] fare : fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }


        for (int k=1; k<n+1; k++) {
            for (int i=1; i<n+1; i++) {
                for (int j=1; j<n+1; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        cost[j][i] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        int answer = cost[s][a] + cost[s][b];
        for (int k=1; k<n+1; k++) {
            answer = Math.min(answer, cost[s][k] + cost[k][a] + cost[k][b]);
        }

        return answer;
    }
}
