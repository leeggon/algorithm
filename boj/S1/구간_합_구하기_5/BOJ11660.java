package boj.S1.구간_합_구하기_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {
    static int N,M;
    static int[][] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sum = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                if (i==0) { // 첫번째 행이라면
                    if (j==0) sum[i][j] = Integer.parseInt(st.nextToken());
                    else sum[i][j] = sum[i][j-1] + Integer.parseInt(st.nextToken());
                } else { // 다른 행
                    if (j==0) {
                        sum[i][j] = sum[i-1][j] + Integer.parseInt(st.nextToken());
                    } else {
                        sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + Integer.parseInt(st.nextToken());
                    }
                }
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int i1 = Integer.parseInt(st.nextToken())-1;
            int j1 = Integer.parseInt(st.nextToken())-1;
            int i2 = Integer.parseInt(st.nextToken())-1;
            int j2 = Integer.parseInt(st.nextToken())-1;
            int result = convert(i2,j2) - convert(i1-1,j2) - convert(i2,j1-1) + convert(i1-1,j1-1);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int convert(int i, int j) {
        if (i<0 || j<0) return 0;
        return sum[i][j];
    }
}

