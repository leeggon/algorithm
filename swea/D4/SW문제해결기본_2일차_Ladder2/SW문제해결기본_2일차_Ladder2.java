package swea.D4.SW문제해결기본_2일차_Ladder2;

import java.io.*;
import java.util.*;

public class SW문제해결기본_2일차_Ladder2 {
    static int[] di = {0,0,1};
    static int[] dj = {-1,1,0};
    static int[][] map;
    static boolean[][] visited;
    static int minDist;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc=0; tc<10; tc++) {
            int t = Integer.parseInt(br.readLine());

            map = new int[100][100];
            for (int i=0; i<100; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            visited = new boolean[100][100];

            minDist = Integer.MAX_VALUE;
            answer = -1;
            for (int j=0; j<100; j++) {
                if (map[0][j] == 1) {
                    dfs(0,j,1, j);
                }
            }

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void dfs(int i, int j, int dist, int startJ) {

        if (i == 99) {
            if (dist < minDist) {
                minDist = dist;
                answer = startJ;
            }

            return;
        }

        for (int d=0; d<3; d++) {
            int nextI = i + di[d];
            int nextJ = j + dj[d];
            if (!isValidCoordinate(nextI, nextJ)) continue;
            if (map[nextI][nextJ] == 1 && !visited[nextI][nextJ]) {
                visited[nextI][nextJ] = true;
                dfs(nextI, nextJ, dist + 1, startJ);
                visited[nextI][nextJ] = false;
                break; // 왼, 오, 아래 순으로 그쪽 1이라면 dfs 진행하고 종료
            }
        }
    }

    private static boolean isValidCoordinate(int i, int j) {
        return 0<=i && i<100 && 0<=j && j<100;
    }
}

