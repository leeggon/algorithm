package BOJ.G2.스타트_택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19238 {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,M,fuel,taxiI,taxiJ;
    static int[][] map;
    static boolean[][] customers;
    static Map<Integer, int[]> destinations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        customers = new boolean[N][N];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        taxiI = Integer.parseInt(st.nextToken());
        taxiJ = Integer.parseInt(st.nextToken());

        destinations = new HashMap<>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int startI = Integer.parseInt(st.nextToken());
            int startJ = Integer.parseInt(st.nextToken());
            customers[startI][startJ] = true;

            int destI = Integer.parseInt(st.nextToken());
            int destJ = Integer.parseInt(st.nextToken());
            destinations.put(i,new int[]{destI,destJ});
        }

        boolean allTransfer = true;
        for (int i=0; i<M; i++) {
            if (!transferSuccess()) {
                allTransfer = false;
                break;
            }
        }

        System.out.println(allTransfer ? fuel : -1);

    }

    private static boolean transferSuccess() {
        if (!bfsToCustomer()) return false;
        if (!bfsToDest()) return false;
        return true;
    }

    private static boolean bfsToCustomer() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        visited[taxiI][taxiJ] = true;
        queue.offer(new int[]{taxiI,taxiJ,0});
        while(!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curI = curPos[0];
            int curJ = curPos[1];
            int curLen = curPos[2];

            if (customers[curI][curJ]) { // 손님 찾았다면
                fuel -= curLen;

            } else { // 아직 못찾았다면
                if (curLen >= fuel) return false;

                for (int d=0; d<4; d++) {
                    int nextI = curI + di[d];
                    int nextJ = curJ + dj[d];
                    if (!validCoordinate(nextI,nextJ)) continue;
                    if (visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.offer(new int[]{nextI,nextJ,curLen+1});
                }
            }
        }
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }
}
