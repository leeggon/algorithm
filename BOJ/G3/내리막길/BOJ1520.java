package BOJ.G3.내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int M,N;
    static int[][] map;
    //	static boolean[][] visited;
    static int[][] dp;
    static int result = 0;
    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i=0; i<M; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp[i][j] : (i,j)에서 끝점까지 경로 개수ㄷ
        dp = new int[M][N];
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
//		for (int[] arr: dp) {
//			System.out.println(Arrays.toString(arr));
//		}
    }


    private static int dfs(int curI, int curJ) {

        if(curI == M-1 && curJ == N-1) {
            return 1;
        }

        if (dp[curI][curJ] != -1) {
            return dp[curI][curJ];
        }

        // dp[curI][curJ] == -1 이라면, dfs로 채우러가야함.
        // 일단 dp[curI][curJ] = 0으로 만들고.
        dp[curI][curJ] = 0;
        for(int d=0; d<4; d++) {
            int nextI = curI + di[d];
            int nextJ = curJ + dj[d];
            if (!validCoordinate(nextI,nextJ)) continue;

            if (map[nextI][nextJ] < map[curI][curJ]) {
                dp[curI][curJ] += dfs(nextI,nextJ);
            }
        }

        // 채우고 나서는 dp[curI][curJ]값으로 return해줘야함. 아무것도 안 채워지면?
        return dp[curI][curJ];
    }


    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<M && 0<=j && j<N) return true;
        return false;
    }
}
