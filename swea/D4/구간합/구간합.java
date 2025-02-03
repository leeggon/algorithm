package swea.D4.구간합;

import java.io.*;
import java.util.*;

public class 구간합 {
    static long A, B, place, answer;
    static long[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            nums = new long[10];
            answer = 0;
            place = 1; // 1의 자리 수부터

            if (A == 0) A++;

            while (A <= B) {

                while (A % 10 != 0 && A<=B) {
                    calc(A);
                    A++;
                }

                if (A > B) break;

                while (B % 10 != 9 && A<=B) {
                    calc(B);
                    B--;
                }

                long diff = (B/10) - (A/10) + 1;
                for (int i=0; i<=9; i++) {
                    nums[i] += diff * place;
                }

                place *= 10;
                A /= 10;
                B /= 10;
            }

            for (int i=1; i<=9; i++) {
                answer += i * nums[i];
            }

            sb.append(String.format("#%d %d\n", tc+1, answer));
        }

        System.out.println(sb.toString().trim());
    }

    // 각각의 자리 수 체크
    private static void calc(long num) {
        for (long n = num; n>0; n/=10) {
            String str = Long.toString(n);

            int i = str.charAt(str.length() - 1) - '0';
            nums[i] += place;
        }
    }
}
