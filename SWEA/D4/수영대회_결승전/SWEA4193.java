package SWEA.D4.수영대회_결승전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA4193 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N;
    static int startI,startJ;
    static int endI,endJ;
    static int[][] map;
    static StringBuilder sb;
    static class Pos {
        int i;
        int j;
        int curLen;

        public Pos(int i, int j, int curLen) {
            this.i = i;
            this.j = j;
            this.curLen = curLen;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            st = new StringTokenizer(br.readLine());
            startI = Integer.parseInt(st.nextToken());
            startJ = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endI = Integer.parseInt(st.nextToken());
            endJ = Integer.parseInt(st.nextToken());

            bfs(tc);

        }
        System.out.println(sb.toString());
    }

    private static void bfs(int tc) {
        boolean[][] visited = new boolean[N][N];
        Queue<Pos> queue = new ArrayDeque<>();

        visited[startI][startJ] = true;
        queue.offer(new Pos(startI,startJ,0));

        while(!queue.isEmpty()) {
            Pos curPos = queue.poll();
            if (curPos.i==endI && curPos.j==endJ) {
                sb.append("#").append(tc+1).append(" ").append(curPos.curLen).append("\n");
                return;
            }

            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!validCoordinate(nextI,nextJ)) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ]==0) {
                    visited[nextI][nextJ] = true;
                    queue.offer(new Pos(nextI,nextJ,curPos.curLen+1));
                } else if (map[nextI][nextJ] == 2) {
                    if (curPos.curLen%3 == 2) {
                        visited[nextI][nextJ] = true;
                        queue.add(new Pos(nextI,nextJ,curPos.curLen+1));
                    } else {
                        queue.add(new Pos(curPos.i,curPos.j,curPos.curLen+1));
                    }
//                    visited[nextI][nextJ] = true;
//                    int t = curPos.curLen / 3;
//                    queue.offer(new Pos(nextI,nextJ,3*(t+1)));
                }
            }
        }

        sb.append("#").append(tc+1).append(" ").append(-1).append("\n");
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }
}
