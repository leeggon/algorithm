package programmers.algorithm.level3.순위;

import java.util.*;

class 순위 {
    public int solution(int n, int[][] results) {
        int answer = 0;

        int[][] dist = new int[n+1][n+1];
        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];

            dist[win][lose] = 1;
            dist[lose][win] = -1;
        }

        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (dist[i][k] == 1 && dist[k][j] == 1) {
                        dist[i][j] = 1;
                        dist[j][i] = -1;
                    }

                    if (dist[i][k] == -1 && dist[k][j] == -1) {
                        dist[i][j] = -1;
                        dist[j][i] = 1;
                    }
                }
            }
        }

        for (int i=1; i<=n; i++) {
            int cnt = 0;
            for (int j=1; j<=n; j++) {
                if (i == j) continue;
                if (dist[i][j] == 0) continue;
                cnt++;
            }

            if (cnt == n - 1) answer++;
        }

        return answer;
    }
}