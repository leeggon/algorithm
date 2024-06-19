package boj.G1.비숍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ1799 {
    static int N;
    static int[][] map;
    static boolean[] ltDiagonal;
    static boolean[] rtDiagonal;
    static ArrayList<Pos>[] ltCandidates;
    static int result = 0;
    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ltDiagonal = new boolean[2*(N-1) + 1];
        rtDiagonal = new boolean[2*(N-1) + 1];
        ltCandidates = new ArrayList[2*(N-1) + 1];
        for (int i=0; i<2*(N-1)+1; i++) {
            ltCandidates[i] = new ArrayList<>();
        }
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<N; j++) {
                if (map[i][j] == 1) {
                    ltCandidates[(i-j) + N-1].add(new Pos(i,j));
                }
            }
        }


//        for (int i=0; i<ltCandidates.length; i++) {
//        	System.out.print("n : " + i + " ");
//            System.out.println(ltCandidates[i]);
//        }
//        System.out.println("ltCandidates.length : " + ltCandidates.length);

        dfs(0,0);
        System.out.println(result);

    }

    private static void dfs(int n, int cnt) {
        if (n == 2*(N-1)+1) {
            result = Math.max(result, cnt);
            return;
        }

        // 남은거 다 칠해도 최대 개수 result 못이길 때
        if ((cnt + (2*(N-1)+1 - n)) <= result) return;

        if (ltCandidates[n].size() == 0) dfs(n+1, cnt);
        else {
            for (int i=0; i<ltCandidates[n].size(); i++) {
                Pos pos = ltCandidates[n].get(i);
                if (!rtDiagonal[pos.i + pos.j]) {
                    rtDiagonal[pos.i + pos.j] = true;
                    dfs(n+1, cnt+1);
                    rtDiagonal[pos.i + pos.j] = false;
                } else {
                    dfs(n+1, cnt);
                }
            }
        }



    }

}

