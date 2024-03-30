package BOJ.G4.탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static char[][] map;
    static int R,C;
    static Pos D,S;
    static class Pos {
        int i;
        int j;
        int curLen;
        char type;

        public Pos(int i, int j, int curLen, char type) {
            this.i = i;
            this.j = j;
            this.curLen = curLen;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "i=" + i +
                    ", j=" + j +
                    ", curLen=" + curLen +
                    ", type=" + type +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++) {
                if (map[i][j] == 'D') {
                    D = new Pos(i,j,0,'S');
                }  else if (map[i][j] == 'S') {
                    S = new Pos(i,j,0,'S');
                    map[i][j] = '.';
                }
            }
        }

        bfs();
    }

    private static void bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] == '*') {
                    queue.offer(new Pos(i,j,0,'*'));
                }
            }
        }

        visited[S.i][S.j] = true;
        queue.offer(S);

        while(!queue.isEmpty()) {
            Pos curPos = queue.poll();

            if (curPos.i == D.i && curPos.j == D.j) {
                System.out.println(curPos.curLen);
                return;
            }

            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!validCoordinate(nextI,nextJ)) continue;

                if (curPos.type == '*') {
                    if (map[nextI][nextJ] == '.') {
                        map[nextI][nextJ] = '*';
                        queue.offer(new Pos(nextI,nextJ,0,'*'));
                    }
                } else if (curPos.type == 'S') {
                    if(visited[nextI][nextJ]) continue;
                    if (map[nextI][nextJ] == '.' || map[nextI][nextJ] == 'D') {
                        visited[nextI][nextJ] = true;
                        queue.offer(new Pos(nextI,nextJ,curPos.curLen+1,'S'));
                    }
                }

            }

        }

        System.out.println("KAKTUS");
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<R && 0<=j && j<C) return true;
        return false;
    }
}
