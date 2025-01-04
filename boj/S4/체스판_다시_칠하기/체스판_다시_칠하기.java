package boj.S4.체스판_다시_칠하기;

import java.io.*;
import java.util.*;

public class 체스판_다시_칠하기 {
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        int answer = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i=0; i<N; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        for (int i=0; i+7<N; i++) {
            for (int j=0; j+7<M; j++) {
                // 첫 자리 흑 검사
                // 첫 자리 백 검사
                int firstBlackSum = 0;
                int firstWhiteSum = 0;
                for (int k=0; k<8; k++) {
                    for (int l=0; l<8; l++) {
                        int chessI = i + k;
                        int chessJ = j + l;
                        if ((k%2 == 0 && l%2 == 0) || (k%2 == 1 && l%2 == 1)) {
                            if (map[chessI][chessJ] != 'B') {
                                firstBlackSum++;
                            } else {
                                firstWhiteSum++;
                            }
                        } else {
                            if (map[chessI][chessJ] != 'W') {
                                firstBlackSum++;
                            } else {
                                firstWhiteSum++;
                            }
                        }
                    }
                }

                answer = Math.min(answer, firstBlackSum);
                answer = Math.min(answer, firstWhiteSum);

            }
        }

        System.out.println(answer);

    }
}
