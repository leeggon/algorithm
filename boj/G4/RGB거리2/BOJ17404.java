package boj.G4.RGB거리2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17404 {
    static int N;
    static int[] R,G,B;
    static int[][] firstRedDp;
    static int[][] firstGreenDp;
    static int[][] firstBlueDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        R = new int[N];
        G = new int[N];
        B = new int[N];

        firstRedDp = new int[3][N];
        firstGreenDp = new int[3][N];
        firstBlueDp = new int[3][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        makeFirstRedDp();
        makeFirstGreenDp();
        makeFirstBlueDp();

        int[] candidates = {firstRedDp[0][N - 1], firstRedDp[1][N - 1], firstRedDp[2][N - 1],
                            firstGreenDp[0][N - 1], firstGreenDp[1][N - 1], firstGreenDp[2][N - 1],
                            firstBlueDp[0][N - 1], firstBlueDp[1][N - 1], firstBlueDp[2][N - 1]
        };

        System.out.println(Arrays.stream(candidates).min().getAsInt());
    }

    private static void makeFirstRedDp() {
        firstRedDp[0][0] = R[0];
        firstRedDp[1][1] = firstRedDp[0][0] + G[1];
        firstRedDp[2][1] = firstRedDp[0][0] + B[1];

        fillDp(firstRedDp);

        firstRedDp[0][N-1] = Integer.MAX_VALUE;
    }

    private static void makeFirstGreenDp() {
        firstGreenDp[1][0] = G[0];
        firstGreenDp[0][1] = firstGreenDp[1][0] + R[1];
        firstGreenDp[2][1] = firstGreenDp[1][0] + B[1];

        fillDp(firstGreenDp);

        firstGreenDp[1][N-1] = Integer.MAX_VALUE;
    }

    private static void makeFirstBlueDp() {
        firstBlueDp[2][0] = B[0];
        firstBlueDp[0][1] = firstBlueDp[2][0] + R[1];
        firstBlueDp[1][1] = firstBlueDp[2][0] + G[1];

        fillDp(firstBlueDp);

        firstBlueDp[2][N-1] = Integer.MAX_VALUE;
    }

    private static void fillDp(int[][] arr) {
        for (int i=2; i<N; i++) {
            if (i == 2) {
                if (Math.min(arr[1][i-1],arr[2][i-1]) == 0) {
                    arr[0][i] = Math.max(arr[1][i-1],arr[2][i-1]) + R[i];
                } else {
                    arr[0][i] = Math.min(arr[1][i-1],arr[2][i-1]) + R[i];
                }

                if (Math.min(arr[0][i-1],arr[2][i-1]) == 0) {
                    arr[1][i] = Math.max(arr[0][i-1],arr[2][i-1]) + G[i];
                } else {
                    arr[1][i] = Math.min(arr[0][i-1],arr[2][i-1]) + G[i];
                }

                if (Math.min(arr[0][i-1],arr[1][i-1]) == 0) {
                    arr[2][i] = Math.max(arr[0][i-1],arr[1][i-1]) + B[i];
                } else {
                    arr[2][i] = Math.min(arr[0][i-1],arr[1][i-1]) + B[i];
                }

            } else {
                arr[0][i] = Math.min(arr[1][i-1],arr[2][i-1]) + R[i];
                arr[1][i] = Math.min(arr[0][i-1],arr[2][i-1]) + G[i];
                arr[2][i] = Math.min(arr[0][i-1],arr[1][i-1]) + B[i];
            }
        }
    }

}
