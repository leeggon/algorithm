package BOJ.G4.미세먼지_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17144 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int R,C,T;
    static int[][] map;
    static int[][] extra;
    static Pos[] cleaners = new Pos[2]; // 공기 청정기 좌표 저장. 0: 위에꺼 1: 밑에꺼
    static class Pos {
        int i;
        int j;
        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public String toString() {
            return "Pos [i=" + i + ", j=" + j + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int index = 0;
        for (int i=0; i<R; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<C; j++) {
                if (map[i][j] == -1) {
                    cleaners[index++] = new Pos(i,j);
                }
            }
        }

//		for (int[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
//		for (Pos pos : cleaners) {
//			System.out.println(pos);
//		}

        for (int t=0; t<T; t++) {
            extra = new int[R][C];
            // 1. 미세먼지 확산
            for (int i=0; i<R; i++) {
                for (int j=0; j<C; j++) {
                    if (map[i][j] > 0) { // 먼지가 있는 구역만
                        int cnt = 0;
                        for (int d=0; d<4; d++) {
                            int nextI = i + di[d];
                            int nextJ = j + dj[d];
                            if (!validCoordinate(nextI,nextJ)) continue;
                            if (map[nextI][nextJ] == -1) continue; // 옆에 공기청정기 있어도 확산 못함.
                            cnt++;
                            extra[nextI][nextJ] += map[i][j] / 5; // extra 배열 처리
                        }
                        map[i][j] -= (map[i][j]/5) * cnt; // map 처리
                    }
                }
            }

            for (int i=0; i<R; i++ ) {
                for (int j=0; j<C; j++) {
                    map[i][j] += extra[i][j];
                }
            }

//			for (int i=0; i<R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

            // 2. 공기 청정기
            // 위에꺼
            moveDustUpCleaner();
            // 밑에꺼
            moveDustDownCleaner();

//			for (int i=0; i<R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("==");

        }

        System.out.println(calcTotalDust());
    }

    private static int calcTotalDust() {
        int cnt = 0;
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] == -1) continue;
                cnt += map[i][j];
            }
        }

        return cnt;
    }

    private static void moveDustUpCleaner() {
        int curI = cleaners[0].i - 1;
        int curJ = cleaners[0].j;
        int d = 0;
        while(true) {
            if (curI == cleaners[0].i && curJ == cleaners[0].j+1) {
                map[curI][curJ] = 0;
                break;
            }

            int nextI = curI + di[d];
            int nextJ = curJ + dj[d];
            if (!validCoordinateUpCleaner(nextI, nextJ)) {
                d = (d+1)%4; // 시계 방향
                continue;
            }

            map[curI][curJ] = map[nextI][nextJ];
            curI = nextI;
            curJ = nextJ;
        }
    }

    private static void moveDustDownCleaner() {
        int curI = cleaners[1].i + 1;
        int curJ = cleaners[1].j;
        int d = 2;
        while(true) {
            if (curI == cleaners[1].i && curJ == cleaners[1].j+1) {
                map[curI][curJ] = 0;
                break;
            }

            int nextI = curI + di[d];
            int nextJ = curJ + dj[d];
            if (!validCoordinateDownCleaner(nextI, nextJ)) {
                d = (d+3)%4;
                continue;
            }

            map[curI][curJ] = map[nextI][nextJ];
            curI = nextI;
            curJ = nextJ;
        }
    }



    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<R && 0<=j && j<C) return true;
        return false;
    }

    private static boolean validCoordinateUpCleaner(int i, int j) {
        if (0<=i && i<=cleaners[0].i && 0<=j && j<C) return true;
        return false;
    }

    private static boolean validCoordinateDownCleaner(int i, int j) {
        if (cleaners[1].i<=i && i<R && 0<=j && j<C) return true;
        return false;
    }
}
