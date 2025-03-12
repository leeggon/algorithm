package programmers.algorithm.level1.택배_상자_꺼내기;

import java.util.*;

class 택배_상자_꺼내기 {
    int[] di = {0,-1,0,-1};
    int[] dj = {1,0,-1,0};

    public int solution(int n, int w, int num) {
        int answer = 0;

        if (w == 1) {
            return (n+1) - num;
        }

        int h = (n-1) / w + 1;
        int[][] box = new int[h][w];

        int curI = h-1;
        int curJ = 0;
        int curD = 0;
        for (int N=1; N<=n; N++) {
            box[curI][curJ] = N;

            int nextI = curI + di[curD];
            int nextJ = curJ + dj[curD];

            if (curD == 1 || curD == 3) {
                curD = (curD + 1) % 4;
                nextI = curI + di[curD];
                nextJ = curJ + dj[curD];
                curI = nextI;
                curJ = nextJ;

                continue;
            }

            if (!isValidCoordinate(nextI, nextJ, h, w)) {
                curD = (curD + 1) % 4;
                nextI = curI + di[curD];
                nextJ = curJ + dj[curD];
            }

            curI = nextI;
            curJ = nextJ;
        }

        int boxI = -1;
        int boxJ = -1;
        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
                if (box[i][j] == num) {
                    boxI = i;
                    boxJ = j;
                }
            }
        }

        for (int i=0; i<=boxI; i++) {
            if (box[i][boxJ] > 0) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isValidCoordinate(int i, int j, int h, int w) {
        return 0<=i && i<h && 0<=j && j<w;
    }
}