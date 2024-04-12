package BOJ.G3.소문난_칠공주;

import java.io.*;
import java.util.*;

public class BOJ1941 {
    static final int N = 5;
    static final int R = 7;
    static int curLen;
    static int sCnt;
    static int result = 0;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int[] combResult = new int[R];
    static char[][] map  = new char[N][];
    static boolean[] visited; // combResult 애들 방문여부. bfs에서 사용

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
        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        comb(0,0);
        System.out.println(result);

    }

    private static void comb(int n, int start) {
        if (n == R) {
            visited = new boolean[R];
            curLen = 1;
            sCnt = 0;
            bfs();
            return;
        }

        for (int i=start; i<N*N; i++) {
            combResult [n] = i;
            comb(n+1,i+1);
        }

    }

    private static void bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        if (map[combResult[0]/N][combResult[0]%N] == 'S') {
            sCnt ++;
        }
        visited[0] = true;
        queue.offer(new Pos(combResult[0] / N, combResult[0] % N));

        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            int curI = curPos.i;
            int curJ = curPos.j;

            for(int d=0; d<4; d++) {
                int nextI = curI + di[d];
                int nextJ = curJ + dj[d];
                if (0<=nextI && nextI<N && 0<=nextJ && nextJ<N) { // 갈 수 있는 좌표일때
                    for (int i=0; i<R; i++) { // combResult에서 찾기
                        if (!visited[i] && (combResult[i]/N)==nextI && (combResult[i]%N)==nextJ) {
                            if (map[combResult[i]/N][combResult[i]%N] == 'S') {
                                sCnt++;
                            }
                            curLen++;
                            visited[i] = true;
                            queue.offer(new Pos(combResult[i] / N, combResult[i] % N));
                        }
                    }
                }
            }

        }

        if (curLen == R && sCnt>=4) result++;
    }
}
