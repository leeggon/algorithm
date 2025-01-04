package boj.G3.나머지_합;

import java.io.*;
import java.util.*;

public class 나머지_합 {
    static long answer = 0;
    static int N;
    static int M;
    static long[] partSums;
    static int[] candidateCnt; // 나머지 별로 가능한 후보군 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        partSums = new long[N];
        candidateCnt = new int[M];
        st = new StringTokenizer(br.readLine());
        // 누적합 배열 생성
        partSums[0] = Long.parseLong(st.nextToken());
        for (int i=1; i<N; i++) {
            partSums[i] = partSums[i-1] + Long.parseLong(st.nextToken());
        }

        // 누적합 자체의 나머지가 0인 경우 바로 answer 1 증가
        // 나머지별 후보군 Cnt에 각 나머지별로 후보군을 카운트해준다.
        for (int i=0; i<N; i++) {
            int remainder = (int) (partSums[i] % M);
            if (remainder == 0) answer++;
            candidateCnt[remainder]++;
        }

        // 각 후보군별로 개수가 2개 이상이라면 2개를 뽑는 조합의 수만큼 answer를 증가시켜준다.
        // partSums[i-1] == partSums[j]의 경우
        for (int i=0; i<M; i++) {
            if (candidateCnt[i] >= 2) {
                answer += ((long) candidateCnt[i] * (candidateCnt[i] - 1) / 2);
            }
        }

        System.out.println(answer);
    }
}
