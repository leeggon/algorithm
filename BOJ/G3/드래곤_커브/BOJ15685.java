package BOJ.G3.드래곤_커브;

import java.io.*;
import java.util.*;

public class BOJ15685 {
    static int[] di = {0,-1,0,1};
    static int[] dj = {1,0,-1,0};
    static int N;
    static boolean[][] map = new boolean[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragon(i,j,d,g);
        }

        System.out.println(allVertexCnt());
    }

    private static void dragon(int i, int j, int d, int g) {
        Deque<Integer> mainDeque = new ArrayDeque<>();
        mainDeque.offer(d);
        Deque<Integer> firstDeque = new ArrayDeque<>();
        Deque<Integer> secondDeque = new ArrayDeque<>();

        for (int n=1; n<=g; n++) {
            while(!mainDeque.isEmpty()) {
                int dir = mainDeque.pollLast();
                firstDeque.offerFirst(dir);
                secondDeque.offer((dir+1)%4);
            }

            while(!firstDeque.isEmpty()) mainDeque.offer(firstDeque.poll());
            while(!secondDeque.isEmpty()) mainDeque.offer(secondDeque.poll());
        }

        int curI = i;
        int curJ = j;
        map[curI][curJ] = true;
        while(!mainDeque.isEmpty()) {
            int curD = mainDeque.poll();
            int nextI = curI + di[curD];
            int nextJ = curJ + dj[curD];
            curI = nextI;
            curJ = nextJ;
            map[curI][curJ] = true;
        }

    }

    private static int allVertexCnt() {
        int result = 0;
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) result++;
            }
        }

        return result;
    }
}
