package SWEA.D4.장훈이의_높은_선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1486 {
    static int N,B;
    static int[] heights;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++) {
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//          System.out.println(Arrays.toString(heights));

            dfs(0,0);
            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int n, int sum) {

        if (sum - B > result) {

            return;
        }

        if (n == N) {
            if (sum >= B) {
                result = Math.min(result, sum-B);
            }
            return;
        }

        dfs(n+1,sum);
        dfs(n+1,sum + heights[n]);

    }
}
