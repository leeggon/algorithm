package swea.D4.러시아_국기_같은_깃발;

import java.io.*;
import java.util.*;

public class 러시아_국기_같은_깃발 {
    static int N, M;
    static int answer;
    static char[][] map;
    static int[] combResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            for (int i=0; i<N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            combResult = new int[2];
            comb(0, 0);

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void comb (int n, int start) {
        if (n == 2) {
            int cnt = 0;
            for (int i=0; i<=combResult[0]; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j] != 'W') cnt++;
                }
            }

            for (int i=combResult[0]+1; i<=combResult[1]; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j] != 'B') cnt++;
                }
            }

            for (int i=combResult[1]+1; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j] != 'R') cnt++;
                }
            }

            answer = Math.min(answer, cnt);

            return;
        }

        for (int i=start; i<N-1; i++) {
            combResult[n] = i;
            comb(n+1, i+1);
        }
    }
}
