package boj.G2.색종이_붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17136 {
    static int[][] map = new int[10][];
    static boolean[][] visited = new boolean[10][10];
    static int[] papers = {0,5,5,5,5,5};
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<10; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0,0);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    private static void dfs(int n, int paperCnt) {
        if (n == 100) {
            result = Math.min(result,paperCnt);
            return;
        }

        if (paperCnt >= result) return;

        int startI = n/10;
        int startJ = n%10;

        if (map[startI][startJ] == 1 && !visited[startI][startJ]) {
            for (int size = 5; size >= 1; size--) {
                if (papers[size] > 0 && canAttach(startI, startJ, size)) {
                    papers[size]--;
                    attach(startI, startJ, size);
                    dfs(n + 1, paperCnt + 1);
                    papers[size]++;
                    detach(startI, startJ, size);
                }
            }
        } else {
            dfs(n+1,paperCnt);
        }



    }

    private static boolean canAttach(int startI, int startJ, int size) {
        for (int i=startI; i<startI+size; i++) {
            for (int j=startJ; j<startJ+size; j++) {
                if (!validCoordinate(i,j) || map[i][j]==0 || visited[i][j]) return false;
            }
        }

        return true;
    }

    private static void attach(int startI, int startJ, int size) {
        for (int i=startI; i<startI+size; i++) {
            for (int j = startJ; j < startJ + size; j++) {
                visited[i][j] = true;
            }
        }
    }
    private static void detach(int startI, int startJ, int size) {
        for (int i=startI; i<startI+size; i++) {
            for (int j = startJ; j < startJ + size; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<10 && 0<=j && j<10) return true;
        return false;
    }
}

