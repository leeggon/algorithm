package programmers.algorithm.level3.자물쇠와_열쇠;

import java.util.*;

class 자물쇠와_열쇠 {
    public boolean solution(int[][] key, int[][] lock) {

        int totalLockCnt = 0;
        for (int i=0; i<lock.length; i++) {
            for (int j=0; j<lock[0].length; j++) {
                if (lock[i][j] == 0) {
                    totalLockCnt++;
                }
            }
        }

        int M = key.length;
        int N = lock.length;

        for (int d=0; d<4; d++) {
            if (d>0) {
                key = rotate90(key);
            }

            // 증감량이 정해진 상태에서
            for (int di=-(N-1); di<=N-1; di++) {
                outer: for (int dj=-(N-1); dj<=N-1; dj++) {

                    // (dx, dy)만큼 key를 움직였을 때 key 좌표값 계산, cnt 계산
                    int cnt = 0;

                    for (int i=0; i<M; i++) {
                        for (int j=0; j<M; j++) {
                            int keyI = i + di;
                            int keyJ = j + dj;

                            // lock의 범위를 벗어나다면 배제
                            if (keyI<0 || keyI>=N || keyJ<0 || keyJ>=N) continue;

                            // 돌기끼리 만난 경우 (다른 di, dj 증감 범위로 넘어가야함)
                            if (key[i][j] == 1 && lock[keyI][keyJ] == 1) {
                                continue outer;
                            }

                            if (key[i][j] == 1 && lock[keyI][keyJ] == 0) {
                                cnt++;
                            }
                        }
                    }

                    if (cnt == totalLockCnt) return true;
                }
            }

        }


        return false;
    }

    private int[][] rotate90 (int[][] key) {
        int M = key.length;
        int[][] tempKey = new int[M][M];
        for (int i=0; i<M; i++) {
            for (int j=0; j<M; j++) {
                tempKey[j][M-1-i] = key[i][j];
            }
        }

        return tempKey;
    }
}
