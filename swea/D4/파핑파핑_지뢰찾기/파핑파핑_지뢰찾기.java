package swea.D4.파핑파핑_지뢰찾기;

import java.io.*;
import java.util.*;

public class 파핑파핑_지뢰찾기 {
    static int[] di = {-1,-1,-1,0,0,1,1,1};
    static int[] dj = {-1,0,1,-1,1,-1,0,1};

    static int answer;
    static int T;
    static int N;
    static char[][] map;
    static int[][] bombCnt;
    static boolean[][] visited;

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

        T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i=0; i<N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            bombCnt = new int[N][N];
            countBombs();

            visited = new boolean[N][N];
            bfsCnt();
            remainderCnt();

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void remainderCnt() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (bombCnt[i][j] > 0 && !visited[i][j]) {
                    answer++;
                }
            }
        }
    }

    private static void bfsCnt() {
        Queue<Pos> queue = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (bombCnt[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.offer(new Pos(i, j));

                    while (!queue.isEmpty()) {
                        Pos curPos = queue.poll();
                        for (int d=0; d<8; d++) {
                            int nextI = curPos.i + di[d];
                            int nextJ = curPos.j + dj[d];
                            if (!isValidCoordinate(nextI, nextJ)) continue;
                            if (visited[nextI][nextJ]) continue;

                            visited[nextI][nextJ] = true;
                            if (bombCnt[nextI][nextJ] == 0) {
                                queue.offer(new Pos(nextI, nextJ));
                            }
                        }
                    }

                    answer++;
                }

            }
        }
    }

    private static void countBombs() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == '*') {
                    bombCnt[i][j] = -1;
                    continue;
                }

                int cnt = 0;
                for (int d=0; d<8; d++) {
                    int nextI = i + di[d];
                    int nextJ = j + dj[d];
                    if (!isValidCoordinate(nextI, nextJ)) continue;

                    if (map[nextI][nextJ] == '*') cnt++;
                }

                bombCnt[i][j] = cnt;
            }
        }
    }

    private static boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }

}
