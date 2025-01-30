package swea.D4.SW문제해결기본_7일차_행렬찾기;

import java.io.*;
import java.util.*;

public class SW문제해결기본_7일차_행렬찾기 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,-1,0,1};
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static PriorityQueue<Square> pq;

    static class Pos {
        int i;
        int j;

        Pos (int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "i : " + i + ", j : " + j;
        }
    }

    static class Square implements Comparable<Square> {
        int r;
        int c;

        Square (int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Square o) {
            int area1 = this.r * this.c;
            int area2 = o.r * o.c;

            if (area1 != area2) {
                return Integer.compare(area1, area2);
            }

            return Integer.compare(this.r, o.r);
        }

        @Override
        public String toString() {
            return "r : " + r + ", c: " + c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            pq = new PriorityQueue<>();
            bfs();

            sb.append("#").append(tc+1).append(" ").append(pq.size());
            while (!pq.isEmpty()) {
                Square sq = pq.poll();
                sb.append(" ").append(sq.r);
                sb.append(" ").append(sq.c);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void bfs() {
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int minI = Integer.MAX_VALUE;
                    int minJ = Integer.MAX_VALUE;
                    int maxI = Integer.MIN_VALUE;
                    int maxJ = Integer.MIN_VALUE;

                    Queue<Pos> queue = new ArrayDeque<>();
                    visited[i][j] = true;
                    queue.offer(new Pos(i, j));

                    while (!queue.isEmpty()) {
                        Pos curPos = queue.poll();

                        minI = Math.min(minI, curPos.i);
                        minJ = Math.min(minJ, curPos.j);
                        maxI = Math.max(maxI, curPos.i);
                        maxJ = Math.max(maxJ, curPos.j);

                        for (int d=0; d<4; d++) {
                            int nextI = curPos.i + di[d];
                            int nextJ = curPos.j + dj[d];
                            if (!isValidCoordinate(nextI, nextJ)) continue;
                            if (map[nextI][nextJ] > 0 && !visited[nextI][nextJ]) {
                                visited[nextI][nextJ] = true;
                                queue.offer(new Pos(nextI, nextJ));
                            }
                        }
                    }

                    pq.offer(new Square(maxI - minI + 1, maxJ - minJ + 1));
                }
            }
        }
    }

    private static boolean isValidCoordinate (int i, int j) {
        return 0<=i && i<N && 0<=j && j<N;
    }
}
