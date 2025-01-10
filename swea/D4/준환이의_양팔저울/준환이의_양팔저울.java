package swea.D4.준환이의_양팔저울;

import java.io.*;
import java.util.*;

public class 준환이의_양팔저울 {
    static int T, N, answer;
    static int[] weights;
    static boolean[] isSelected;
    static int[] permResult;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            isSelected = new boolean[N];
            permResult = new int[N];
            perm(0);

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
    
    private static void perm(int n) {
        if (n == N) {
            // 순열 조합 완성
            // System.out.println(Arrays.toString(permResult));
            dfs(0, 0, 0);

            return;
        }

        for (int i=0; i<N; i++) {
            if (isSelected[i]) continue;

            isSelected[i] = true;
            permResult[n] = i;
            perm(n+1);
            isSelected[i] = false;
        }
    }

    private static void dfs(int n, int leftWeight, int rightWeight) {
        if (leftWeight < rightWeight) return;

        if (n == N) {
            answer++;
            return;
        }

        dfs(n+1, leftWeight + weights[permResult[n]], rightWeight);
        dfs(n+1, leftWeight, rightWeight + weights[permResult[n]]);
    }
}
