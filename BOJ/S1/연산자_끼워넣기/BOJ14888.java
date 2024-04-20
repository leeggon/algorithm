package BOJ.S1.연산자_끼워넣기;

import java.io.*;
import java.util.*;

public class BOJ14888 {
    static int N;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;
    static int[] nums;
    static int[] opCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        opCnt = new int[4];
        opCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0,nums[0]);

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    // 연산자 N-1개 뽑기 dfs
    private static void dfs(int n, int opResult) {
        if (n == N-1) {
            maxResult = Math.max(maxResult, opResult);
            minResult = Math.min(minResult, opResult);

            return;
        }

        for (int i=0; i<4; i++) {
            if (opCnt[i] > 0) {
                opCnt[i] -= 1;
                if (i==0) {
                    dfs(n+1, opResult + nums[n+1]);
                } else if (i==1) {
                    dfs(n+1, opResult - nums[n+1]);
                } else if (i==2) {
                    dfs(n+1, opResult * nums[n+1]);
                } else {
                    dfs(n+1, opResult / nums[n+1]);
                }

                opCnt[i] += 1;
            }
        }

    }
}

