package programmers.algorithm.level3.거리두기_확인하기;

import java.util.*;

class 거리두기_확인하기 {
    char[][] map;
    int[] result = new int[5];
    int[] di = {-1,0,1,0};
    int[] dj = {0,-1,0,1};

    class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "i : " + i + " j : " + j;
        }
    }

    public int[] solution(String[][] places) {
        for (int i=0; i<places.length; i++) { // 대기실마다
            map = new char[5][5];
            for (int j=0; j<5; j++) {
                for (int k=0; k<5; k++) {
                    map[j][k] = places[i][j].charAt(k);
                }
            }

            result[i] = verify(map);
        }

        return result;
    }

    private int verify(char[][] map) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (map[i][j] == 'P') { // 해당 위치가 사람 자리라면 탐색 시작 (2회)
                    boolean[][] visited = new boolean[5][5];
                    Queue<Pos> queue = new ArrayDeque<>();

                    visited[i][j] = true;
                    queue.offer(new Pos(i, j));

                    for (int k=0; k<3; k++) {
                        int queueSize = queue.size();
                        for (int l=0; l<queueSize; l++) {
                            Pos curPos = queue.poll();
                            if (!(curPos.i == i && curPos.j == j) && map[curPos.i][curPos.j] == 'P') {
                                return 0;
                            }

                            for (int d=0; d<4; d++) {
                                int nextI = curPos.i + di[d];
                                int nextJ = curPos.j + dj[d];
                                if (!isValidCoordinate(nextI, nextJ) || map[nextI][nextJ] == 'X') continue;
                                if (!visited[nextI][nextJ]) {
                                    visited[nextI][nextJ] = true;
                                    queue.offer(new Pos(nextI, nextJ));
                                }
                            }
                        }

                    }
                }
            }
        }

        return 1;
    }

    private boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<5 && 0<=j && j<5) return true;
        return false;
    }
}
