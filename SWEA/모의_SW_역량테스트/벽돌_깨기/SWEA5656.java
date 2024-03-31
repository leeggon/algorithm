package SWEA.모의_SW_역량테스트.벽돌_깨기;

import java.io.*;
import java.util.*;

public class SWEA5656 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,W,H;
    static int[][] originalMap;
    static int[][] map;
    static boolean[][] visited;
    static int result;
    static int remainderCnt;
    static int[] permResult;

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
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++) {
            result = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            originalMap = new int[H][];
            for (int i=0; i<H; i++) {
                originalMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            permResult = new int[N];
            perm(0);

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void perm(int n) {
        if (n == N) {
            map = new int[H][W];
            for (int i=0; i<H; i++) {
                for (int j=0; j<W; j++) {
                    map[i][j] = originalMap[i][j];
                }
            }
//            System.out.println(Arrays.toString(permResult));
            remainderCnt = 0;
            for (int i=0; i<N; i++) { // N번 구슬 떨어트리고
                dropBalls(permResult[i]);
            }
            countRemainder();

            result = Math.min(result, remainderCnt); // 남은게 최소가 되어야함.(최대한 많이 벽돌 깨므로)
            return;
        }

        for (int i=0; i<W; i++) {
            permResult[n] = i;
            perm(n+1);
        }
    }

    private static void dropBalls(int dropJ) { // 한번 구슬 떨어뜨리는 상황에 대한 것.
        int dropI = -1;
        for (int i=0; i<H; i++) {
            if (map[i][dropJ] > 0) {
                dropI = i;
                break;
            }
        }

        if (dropI == -1) return; // 하나도 못깨면 return

        bfs(dropI,dropJ);
        breakBricks();
        dropBricks();


    }

    private static void bfs(int dropI, int dropJ) {
        visited = new boolean[H][W];
        Queue<Pos> queue = new ArrayDeque<>();

        visited[dropI][dropJ] = true;
        queue.offer(new Pos(dropI,dropJ));

        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            if (map[curPos.i][curPos.j] > 1) { // poll했는데 1보다 큰 값이라면.
                for (int i=1; i<=map[curPos.i][curPos.j]-1; i++) { // 그값-1번 넣어야함.
                    for (int d=0; d<4; d++) { // 4가지 방향에 대해서
                        int nextI = curPos.i + di[d]*i;
                        int nextJ = curPos.j + dj[d]*i;
                        if (validCoordinate(nextI,nextJ) && !visited[nextI][nextJ]) {
                            visited[nextI][nextJ] = true;
                            queue.offer(new Pos(nextI,nextJ));
                        }
                    }
                }
            }
        }
    }

    private static void breakBricks() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                if (visited[i][j]) map[i][j] = 0;
            }
        }
    }

    private static void dropBricks() {
        ArrayList<Integer>[] bricks = new ArrayList[W];
        for (int j=0; j<W; j++) {
            bricks[j] = new ArrayList<>();
        }

        for (int j=0; j<W; j++) {
            for (int i=H-1; i>=0; i--) {
                if (map[i][j] > 0) {
                    bricks[j].add(map[i][j]);
                }
            }
        }

        map = new int[H][W];
        for (int j=0; j<W; j++) {
            for (int i=0; i<bricks[j].size(); i++) {
                map[H-1-i][j] = bricks[j].get(i);
            }
        }

    }

    private static void countRemainder() {
        int result = 0;
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                if (map[i][j] > 0) result++;
            }
        }

        remainderCnt += result;
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<H && 0<=j && j<W) return true;
        return false;
    }
}
