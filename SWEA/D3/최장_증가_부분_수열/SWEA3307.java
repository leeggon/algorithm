package SWEA.D3.최장_증가_부분_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA3307 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


            // dp[i]: 나 i번째를 마지막으로 포함시키는 최장증가수열
            int[] dp = new int[N];
            Arrays.fill(dp, 1);

            for (int i=1; i<N; i++) {
                for (int j=0; j<i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }

            sb.append("#").append(tc+1).append(" ");
            sb.append(Arrays.stream(dp).max().getAsInt()).append("\n");
        }

        System.out.println(sb.toString());
    }

}

