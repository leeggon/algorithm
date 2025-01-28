package swea.D4.SW문제해결기본_9일차_사칙연산_유효성_검사;

import java.io.*;
import java.util.*;

public class SW문제해결기본_9일차_사칙연산_유효성_검사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc=0; tc<10; tc++) {
            int N = Integer.parseInt(br.readLine());

            int answer = 1;
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);

                if (st.hasMoreTokens()) { // 리프 노드가 아닌 경우
                    if (Character.isDigit(c)) {
                        answer = 0;
                    }
                } else { // 리프 노드인 경우
                    if (!Character.isDigit(c)) {
                        answer = 0;
                    }
                }
            }

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
