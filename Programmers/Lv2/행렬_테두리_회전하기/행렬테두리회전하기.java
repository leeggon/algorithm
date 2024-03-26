package Programmers.Lv2.행렬_테두리_회전하기;

import java.util.*;

class 행렬테두리회전하기 {
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};
    static int startI, startJ, endI, endJ;
    static int[][] map;

    public static int[] solution(int rows, int columns, int[][] queries) {
        // 초기 맵 생성
        map = new int[rows][columns];
        int cnt = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                map[i][j] = ++cnt;
            }
        }

        // result 배열 생성
        int[] result = new int[queries.length];

        // 실행문마다(1번의 회전마다)
        for (int i=0; i<queries.length; i++) {
            startI = queries[i][0]-1;
            startJ = queries[i][1]-1;
            endI = queries[i][2]-1;
            endJ = queries[i][3]-1;

            int res = map[startI][startJ];
            int temp = map[startI][startJ];
            int nextTemp = 0;

            int curI = startI;
            int curJ = startJ;
            int d = 0;
            while(true) {
                int nextI = curI + di[d];
                int nextJ = curJ + dj[d];
                if (!validCoordinate(nextI,nextJ)) {
                    if (++d == 4) break;
                } else {
                    nextTemp = map[nextI][nextJ];
                    res = Math.min(res,nextTemp);

                    map[nextI][nextJ] = temp;

                    temp = nextTemp;

                    curI = nextI;
                    curJ = nextJ;
                }
            }

            result[i] = res;
        }

        return result;
    }

    private static boolean validCoordinate(int i, int j) {
        if (startI<=i && i<=endI && startJ<=j && j<=endJ) return true;
        return false;
    }
}
