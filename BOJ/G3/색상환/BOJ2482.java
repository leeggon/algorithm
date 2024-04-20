package BOJ.G3.색상환;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2482 {
    static int N,K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        int[][][] dp = new int[2][K+1][N];
        for (int j=0; j<N; j++) {
            dp[0][0][j] = 1;
        }
        dp[1][1][0] = 1;

        for (int j=1; j<N; j++) {
            for (int i=1; i<=K; i++) {
                dp[0][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1];
                dp[1][i][j] = dp[0][i][j-1];
            }
        }

        for (int[] arr : dp[0]) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----");
        for (int[] arr : dp[1]) {
            System.out.println(Arrays.toString(arr));
        }

        long result = Math.max(dp[0][K][N-1], dp[1][K][N-1]);
        System.out.println(result%1000000003);

    }
}
