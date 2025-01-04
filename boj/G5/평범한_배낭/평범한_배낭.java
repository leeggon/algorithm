package boj.G5.평범한_배낭;

import java.io.*;
import java.util.*;

public class 평범한_배낭 {
    static int N, K;
    static int[] weights;
    static int[] values;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N+1];
        values = new int[N+1];
        dp = new int[K+1][N+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int j=1; j<=N; j++) {
            for (int k=0; k<=K; k++) {
                if (k - weights[j] >= 0) {
                    dp[k][j] = Math.max(dp[k][j-1], dp[k - weights[j]][j-1] + values[j]);
                } else {
                    dp[k][j] = dp[k][j-1];
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}
