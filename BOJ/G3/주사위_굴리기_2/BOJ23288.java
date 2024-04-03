package BOJ.G3.주사위_굴리기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23288 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int[][] dice;
    static int d = 1;
    static int N,M;
    static int[][] map;

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int curI = 0;
        int curJ = 0;
        int result = 0;

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dice = new int[][]{{0,2,0},{4,1,3},{0,5,0},{0,6,0}};

        for (int i=0; i<K; i++) { // K번 이동에 대해
            int nextI = curI + di[d];
            int nextJ = curJ + dj[d];
            if (!validCoordinate(nextI,nextJ)) { // 못가는 경우에만 nextI,nextJ 재설정(방향도 재설정)
                d = (d+2)%4;
                nextI = curI + di[d];
                nextJ = curJ + dj[d];
            }

            moveDice(d);
            int A = dice[3][1];
            int B = map[nextI][nextJ];
            int C = bfs(nextI,nextJ);
            result += B*C;

            if (A>B) d = (d+1)%4;
            else if (A<B) d = (d+3)%4;

            // 글로 이동
            curI = nextI;
            curJ = nextJ;
        }

        System.out.println(result);

    }

    private static int bfs(int i, int j) {
        int result = 0;

        boolean[][] visited = new boolean[N][M];
        Queue<Pos> queue = new ArrayDeque<>();

        visited[i][j] = true;
        queue.offer(new Pos(i,j));

        while(!queue.isEmpty()) {
            Pos curPos = queue.poll();
            result++;

            for (int d=0; d<4; d++) {
                int nextI = curPos.i + di[d];
                int nextJ = curPos.j + dj[d];
                if (!validCoordinate(nextI,nextJ)) continue;
                if (visited[nextI][nextJ]) continue;

                if (map[nextI][nextJ] == map[curPos.i][curPos.j]) {
                    visited[nextI][nextJ] = true;
                    queue.offer(new Pos(nextI,nextJ));
                }
            }
        }

        return result;
    }

    private static void moveDice(int d) {
        switch(d) {
            case 0:
                int temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
                break;
            case 1:
                temp = dice[1][2]; // 3
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = temp;
                break;
            case 2:
                temp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp;
                break;
            case 3:
                temp = dice[1][0]; // 4
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = temp;
                break;
        }
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<M) return true;
        return false;
    }


}

