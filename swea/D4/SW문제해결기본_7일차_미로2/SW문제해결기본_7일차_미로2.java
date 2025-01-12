package swea.D4.SW문제해결기본_7일차_미로2;

import java.io.*;
import java.util.*;

public class SW문제해결기본_7일차_미로2 {

    static int[] di = {-1,0,1,0};
    static int[] dj = {0,-1,0,1};

    static int T = 10;
    static int[][] map;
    static boolean[][] visited;
    static int startI, startJ;
    static int endI, endJ;

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
        StringBuilder sb = new StringBuilder();
        for (int tc=0; tc<T; tc++) {
            int t = Integer.parseInt(br.readLine());
            map = new int[100][100];
            visited = new boolean[100][100];
            for (int i=0; i<100; i++) {
                String s = br.readLine();
                for (int j=0; j<100; j++) {
                    map[i][j] = s.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        startI = i;
                        startJ = j;
                    } else if (map[i][j] == 3) {
                        endI = i;
                        endJ = j;
                    }
                }
            }

            int answer = bfs();
            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static int bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(startI, startJ));
        visited[startI][startJ] = true;

        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            if (curPos.i == endI && curPos.j == endJ) return 1;

            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!isValidCoordinate(nextI, nextJ)) continue;
                if (map[nextI][nextJ] != 0 && map[nextI][nextJ] != 3) continue;
                if (visited[nextI][nextJ]) continue;

                visited[nextI][nextJ] = true;
                queue.offer(new Pos(nextI, nextJ));
            }
        }

        return 0;
    }

    private static boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<100 && 0<=j && j<100) return true;
        return false;
    }
}
