package swea.D4.치즈도둑;

import java.io.*;
import java.util.*;

public class 치즈도둑 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,-1,0,1};
    static int N;
    static int answer;
    static int[][] map;

    static boolean[][] visited;

    static class Pos {
        int i;
        int j;

        public Pos (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            answer = 1;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            for (int i=1; i<=100; i++) {
                count(i);
            }

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void count(int day) {
        int cnt = 0;
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j] && map[i][j] > day) {
                    cnt++;
                    bfs(i, j, day);
                }
            }
        }

        answer = Math.max(answer, cnt);
    }

    private static void bfs(int i, int j, int day) {
        Queue<Pos> queue = new ArrayDeque<>();
        visited[i][j] = true;
        queue.offer(new Pos(i, j));

        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!isValidCoordinate(nextI, nextJ)) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ] <= day) continue;

                visited[nextI][nextJ] = true;
                queue.offer(new Pos(nextI, nextJ));
            }
        }
    }

    private static boolean isValidCoordinate (int i, int j) {
        return 0<=i && i<N && 0<=j && j<N;
    }

}
