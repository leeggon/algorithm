package BOJ.G4.도둑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++) {
            int result = 0;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] money = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] sum = new int[N];

            int firstSum = 0;
            for (int i=0; i<M; i++) {
                firstSum += money[i];
            }

            // N == M일 때 처리
            if (N == M) {
                if (firstSum < K) sb.append(1).append("\n");
                else sb.append(0).append("\n");
                continue;
            }

            sum[0] = firstSum;
            if (sum[0] < K) result++;

            int left = 0;
            int right = M-1;
            for (int i=1; i<N; i++) {
                left++;
                right = (right+1) % N;
                sum[i] = sum[i-1] - money[left-1] + money[right];
                if (sum[i] < K) result++;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
