package boj.G3.색상환;

import java.util.Scanner;

public class BOJ2482_fail {
    static int N,K;
    static boolean[] colored;
    static long result = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        colored = new boolean[N];
        dfs(0,0);

        System.out.println(result%1000000003);

    }

    private static void dfs(int n, int cnt) {

        if (cnt > K) return;

        if (n == N) {
            if (cnt == K) {
//                System.out.println(Arrays.toString(colored));
                result++;
            }
            return;
        }

        if (n==0) {
            colored[0] = true;
            dfs(n+1, cnt+1);
            colored[0] = false;
            dfs(n+1, cnt);
        } else if (n==N-1) {
            if (colored[n-1] || colored[0]) {
                dfs(n+1,cnt);
            } else {
                colored[n] = true;
                dfs(n+1, cnt+1);
                colored[n] = false;
                dfs(n+1, cnt);
            }
        } else {
            if (colored[n-1]) { // 직전꺼가 색칠돼있으면
                dfs(n+1,cnt);
            } else {
                colored[n] = true;
                dfs(n+1, cnt+1);
                colored[n] = false;
                dfs(n+1, cnt);
            }
        }

    }
}
