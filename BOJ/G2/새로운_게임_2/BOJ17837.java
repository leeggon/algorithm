package BOJ.G2.새로운_게임_2;

import java.io.*;
import java.util.*;

public class BOJ17837 {
    static int[] di = {0,0,0,-1,1};
    static int[] dj = {0,1,-1,0,0};
    static int N,K;
    static int[][] mapInfo; // 지도 배경(밑바닥)
    static Deque<Horse>[][] map; // 지도 상황
    static Horse[] horses; // 각 번호의 말마다 위치 저장하기 위함.
    static class Horse {
        int no;
        int i;
        int j;
        int d;

        public Horse(int no, int i, int j, int d) {
            this.no = no;
            this.i = i;
            this.j = j;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Horse{" +
                    "no=" + no +
                    ", i=" + i +
                    ", j=" + j +
                    ", d=" + d +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mapInfo = new int[N][N];
        for (int i=0; i<N; i++) {
            mapInfo[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        map = new Deque[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] = new ArrayDeque<>();
            }
        }

        horses = new Horse[K+1];
        for (int k=1; k<=K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            Horse horse = new Horse(k,i,j,d);
            map[i][j].offer(horse);
            horses[k] = horse;
        }

        System.out.println(getResult());
    }

    private static int getResult() {
        int gameCnt = 0;

        while(true) { // 1번말~K번말 1 cycle
            gameCnt++;
            if (gameCnt == 1001) break;

            for (int k=1; k<=K; k++) {
                Horse curHorse = horses[k]; // 인덱싱용. 현재 몇번 말 옮기고 있어? horses 배열에서 가져옴.
                int curI = curHorse.i;
                int curJ = curHorse.j;

                int nextI = curI + di[curHorse.d];
                int nextJ = curJ + dj[curHorse.d];
                if (!validCoordinate(nextI,nextJ) || mapInfo[nextI][nextJ] == 2) { // 다음 파란색
                    moveToBlue(curI,curJ,curHorse);
                } else if (mapInfo[nextI][nextJ] == 0) { // 다음 흰색
                    moveToWhite(curI,curJ,nextI,nextJ,curHorse);
                } else { // 다음 빨강.
                    moveToRed(curI,curJ,nextI,nextJ,curHorse);
                }

                if (checkFour()) return gameCnt; // 4개 이상 겹쳐진 말이 있다면
            }

        }

        return gameCnt == 1001? -1 : gameCnt;
    }

    private static boolean checkFour() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j].size() >= 4) return true;
            }
        }

        return false;
    }

    private static void moveToBlue (int curI, int curJ, Horse curHorse) {
        int oppD = oppDir(curHorse.d);
        int mapCurSize = map[curI][curJ].size();
        for (int i=0; i<mapCurSize; i++) {
            Horse h = map[curI][curJ].poll();
            if (h.no == curHorse.no) {
                h.d = oppD;
                horses[h.no] = h;
            }
            map[curI][curJ].offer(h);
        }

        int nextI = curI + di[oppD];
        int nextJ = curJ + dj[oppD];
        if (!validCoordinate(nextI,nextJ) || mapInfo[nextI][nextJ] == 2) return; // 또 파란색
        if (mapInfo[nextI][nextJ] == 0) {
            moveToWhite(curI,curJ,nextI,nextJ,curHorse);
        } else {
            moveToRed(curI,curJ,nextI,nextJ,curHorse);
        }
    }

    private static void moveToWhite (int curI, int curJ, int nextI, int nextJ, Horse curHorse) {
        boolean found = false;
        int mapCurSize = map[curI][curJ].size();
        for (int i=0; i<mapCurSize; i++) {
            
            Horse h = map[curI][curJ].poll();
            if (h.no == curHorse.no) {
                found = true;
                h.i = nextI;
                h.j = nextJ;
                map[nextI][nextJ].offer(h);
                horses[h.no] = h;
            } else {
                if (!found) {
                    map[curI][curJ].offer(h);
                } else {
                    h.i = nextI;
                    h.j = nextJ;
                    map[nextI][nextJ].offer(h);
                    horses[h.no] = h;
                }
            }
        }
    }

    private static void moveToRed (int curI, int curJ, int nextI, int nextJ, Horse curHorse) {
        int mapCurSize = map[curI][curJ].size();
        for (int i=0; i<mapCurSize; i++) {
            Horse h = map[curI][curJ].pollLast();
            if (h.no == curHorse.no) {
                h.i = nextI;
                h.j = nextJ;
                map[nextI][nextJ].offer(h);
                horses[h.no] = h;
                break;
            } else {
                h.i = nextI;
                h.j = nextJ;
                map[nextI][nextJ].offer(h);
                horses[h.no] = h;
            }
        }
    }

    private static boolean validCoordinate (int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }

    private static int oppDir (int d) {
        switch(d) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }
}
