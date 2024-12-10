package programmers.algorithm.level2.리코쳇_로봇;

import java.util.*;

class 리코쳇_로봇 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,-1,0,1};

    static int N, M;
    static int startI, startJ;
    static int endI, endJ;
    static char[][] map;

    static class Pos {
        int i;
        int j;
        int moveCnt;

        public Pos(int i, int j, int moveCnt) {
            this.i = i;
            this.j = j;
            this.moveCnt = moveCnt;
        }

        @Override
        public String toString() {
            return "i : " + i + " j : " + j + " moveCnt : " + moveCnt;
        }
    }

    public static int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        for (int i=0; i<N; i++) {
            map[i] = board[i].toCharArray();
            for (int j=0; j<M; j++) {
                if (map[i][j] == 'R') {
                    startI = i;
                    startJ = j;
                } else if (map[i][j] == 'G') {
                    endI = i;
                    endJ = j;
                }
            }
        }

        return bfs();

    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> queue = new ArrayDeque<>();

        visited[startI][startJ] = true;
        queue.offer(new Pos(startI, startJ, 0));

        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            if (curPos.i == endI && curPos.j == endJ) {
                return curPos.moveCnt;
            }

            // 4가지 방향에 대해
            for (int d=0; d<4; d++) {
                int curI = curPos.i;
                int curJ = curPos.j;

                while (true) {
                    // nextI, nextJ 이동시키기
                    int nextI = curI + di[d];
                    int nextJ = curJ + dj[d];
                    // 탈출 조건 (탈출 전에 해당 자리 좌표 visited 처리)
                    if (!isValidCoordinate(nextI, nextJ) || map[nextI][nextJ] == 'D') {
                        if (!visited[curI][curJ]) {
                            visited[curI][curJ] = true;
                            queue.offer(new Pos(curI, curJ, curPos.moveCnt + 1));
                        }
                        break;
                    }

                    curI = nextI;
                    curJ = nextJ;
                }
            }
        }

        return -1;
    }

    private static boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<M) return true;
        return false;
    }
}
