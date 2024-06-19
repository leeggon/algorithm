package boj.G3.욕심쟁이_판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1937 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp : (i,j)에서 출발했을 때 최대 이동가능 칸 수
        dp = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                result = Math.max(result, dfs(i,j));
            }
        }

        System.out.println(result);
    }

    private static int dfs (int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = 1;
        for (int d=0; d<4; d++) {
            int nextI = i + di[d];
            int nextJ = j + dj[d];
            if (!validCoordinate(nextI,nextJ)) continue;
            if (map[nextI][nextJ] <= map[i][j]) continue;
            dp[i][j] = Math.max(dp[i][j],dfs(nextI,nextJ) + 1);
        }

        return dp[i][j];
    }

    private static boolean validCoordinate (int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }
}
