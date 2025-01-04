package boj.G4.부분합;

import java.io.*;
import java.util.*;

public class 부분합 {
    static int N, S;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int s = 0;
        int e = 0;
        int sum = 0;

        int answer = Integer.MAX_VALUE;
        while (e < N) {
            // e 포인터를 증가시키며 부분합 계산
            sum += numbers[e];
            e++;

            // 부분합이 S 이상일 때 최소 길이를 업데이트하고 s를 증가
            while (sum >= S) {
                answer = Math.min(answer, e - s);
                sum -= numbers[s];
                s++;
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println("0");
        } else {
            System.out.println(answer);
        }

    }
}
