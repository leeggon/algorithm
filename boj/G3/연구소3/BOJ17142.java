package boj.G3.연구소3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,M;
    static int[][] map;
    static ArrayList<Virus> initVirusList;
    static Virus[] combResult;
    static int totalSpaceCnt;
    static int result;
    static class Virus {
        int i;
        int j;
        public Virus(int i, int j ) {
            super();
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        combResult = new Virus[M];
        totalSpaceCnt = 0;
        result = Integer.MAX_VALUE;

        map = new int[N][N];
        initVirusList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) { // 바이러스들 위치
                    initVirusList.add(new Virus(i, j));
                } else if (map[i][j] == 0) {
                    totalSpaceCnt++;
                }
            }
        }

        if (totalSpaceCnt == 0) {
            System.out.println(0);
            return;
        }

//		for (int[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}

        comb(0,0);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);


    }

    private static void comb(int n, int start) {
        if (n == M) {
//			System.out.println(Arrays.toString(combResult));

            Queue<Virus> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][N];
            for (int i=0; i<M; i++) {
                Virus virus = combResult[i];
                visited[virus.i][virus.j] = true;
                queue.offer(virus);
            }

            int spaceCnt = 0;
            int time = 0;
            while (!queue.isEmpty()) {
                time++;

                int queueSize = queue.size();
                for (int i=0; i<queueSize; i++) {
                    Virus virus = queue.poll();

                    for (int d=0; d<4; d++) {
                        int nextI = virus.i + di[d];
                        int nextJ = virus.j + dj[d];
                        if (!validCoordinate(nextI,nextJ)) continue;
                        if (visited[nextI][nextJ]) continue;
                        if (map[nextI][nextJ] == 1) continue;
                        visited[nextI][nextJ] = true;
                        queue.offer(new Virus(nextI,nextJ));
                        if (map[nextI][nextJ] == 0) {
                            if (++spaceCnt == totalSpaceCnt) {
                                result = Math.min(result, time);
                                return;
                            }
                        }
                    }

                }
            }


            return;
        }

        for (int i=start; i<initVirusList.size(); i++) {
            combResult[n] = initVirusList.get(i);
            comb(n+1, i+1);
        }

    }


    private static boolean validCoordinate (int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }
}

