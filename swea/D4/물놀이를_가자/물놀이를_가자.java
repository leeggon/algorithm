package swea.D4.물놀이를_가자;

import java.io.*;
import java.util.*;

public class 물놀이를_가자 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,-1,0,1};
    static int answer;
    static int T, N, M;
    static char[][] map;

    static class Pos {
        int i;
        int j;
        int dist;

        public Pos(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            answer = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];

            for (int i=0; i<N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            bfs();
            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        // 물 위치를 모두 큐에 넣고 시작
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 'W') {
                    visited[i][j] = true;
                    queue.offer(new Pos(i, j, 0));
                }
            }
        }

        // BFS하면 자동적으로 vistied 채워지면서 물로부터의 최단 거리가 채워지게 된다.
        while(!queue.isEmpty()) {
            Pos curPos = queue.poll();
            answer += curPos.dist;
            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!isValidCoordinate(nextI, nextJ)) continue;
                if (visited[nextI][nextJ]) continue;

                visited[nextI][nextJ] = true;
                queue.offer(new Pos(nextI, nextJ, curPos.dist + 1));
            }
        }

    }

    private static boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<M) return true;
        return false;
    }
}
