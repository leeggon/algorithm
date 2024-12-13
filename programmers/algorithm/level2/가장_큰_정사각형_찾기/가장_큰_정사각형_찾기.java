package programmers.algorithm.level2.가장_큰_정사각형_찾기;

import java.util.*;

class 가장_큰_정사각형_찾기 {
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        if (N == 1 || M == 1) return 1;

        int[][] dp = new int[N][M];
        for (int i=0; i<N; i++) {
            dp[i][0] = board[i][0];
        }
        for (int j=0; j<M; j++) {
            dp[0][j] = board[0][j];
        }

        for (int i=1; i<N; i++) {
            for (int j=1; j<M; j++) {
                if (board[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        return answer * answer;
    }
}