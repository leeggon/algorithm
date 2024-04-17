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
    static ArrayList<Integer>[][] customers; // 몇번 손님이 있는지
    static Map<Integer, int[]> destinations; // 몇번 손님의 목적지가 어디인지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        customers = new ArrayList[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                customers[i][j] = new ArrayList<>();
            }
        }
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        taxiI = Integer.parseInt(st.nextToken()) - 1; // (초기) 택시 i위치
        taxiJ = Integer.parseInt(st.nextToken()) - 1; // (초기) 택시 j위치
        destinations = new HashMap<>();

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int startI = Integer.parseInt(st.nextToken())-1;
            int startJ = Integer.parseInt(st.nextToken())-1;
            customers[startI][startJ].add(i);

            int destI = Integer.parseInt(st.nextToken())-1;
            int destJ = Integer.parseInt(st.nextToken())-1;
            destinations.put(i,new int[]{destI,destJ});
        }

//        for (int[] arr: customers) {
//            System.out.println(Arrays.toString(arr));
//        }
        boolean allTransfer = true;
        for (int i=1; i<=M; i++) {
            if (!transferSuccess()) {
                allTransfer = false;
                break;
            }
        }

//        System.out.println("==");
        System.out.println(allTransfer ? fuel : -1);

    }

    private static boolean transferSuccess() {
//        System.out.println(bfsToCustomer());
        int custNo = bfsToCustomer();
        if (custNo == -1) return false; // 손님 번호 return

        int[] dest = destinations.get(custNo);
        if (!bfsToDest(dest)) return false;

        return true;
    }

    private static boolean bfsToDest(int[] dest) {
        int destI = dest[0];
        int destJ = dest[1];

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[taxiI][taxiJ] = true;
        queue.offer(new int[]{taxiI,taxiJ,0});

        while(!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curI = curPos[0];
            int curJ = curPos[1];
            int curLen = curPos[2];

            if (curI == destI && curJ == destJ) { // 목적지 도달
                fuel -= curLen;
                fuel += curLen*2;
                taxiI = curI;
                taxiJ = curJ;
            } else { // 미도달
                if (curLen >= fuel) return false; // 도착도 안했는데 연료없다?

                for (int d=0; d<4; d++) {
                    int nextI = curI + di[d];
                    int nextJ = curJ + dj[d];
                    if (!validCoordinate(nextI,nextJ)) continue;
                    if (visited[nextI][nextJ]) continue;
                    if (map[nextI][nextJ] == 1) continue;
                    visited[nextI][nextJ] = true;
                    queue.offer(new int[]{nextI,nextJ,curLen+1});
                }
            }
        }


        return true;
    }

    private static int bfsToCustomer() {
        int custMinI = Integer.MAX_VALUE;
        int custMinJ = Integer.MAX_VALUE;
        int useFuel = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        visited[taxiI][taxiJ] = true;
        queue.offer(new int[]{taxiI,taxiJ,0});
        int bfsDepth = Integer.MAX_VALUE; // 최단 손님 거리 3이라면 3까지는 손님 전부 bfs하기 위함.
        while(!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curI = curPos[0];
            int curJ = curPos[1];
            int curLen = curPos[2];

            if (curLen <= bfsDepth) {
                if (customers[curI][curJ].size() > 0) {
                    bfsDepth = curLen;
                    useFuel = curLen;
                    if (curI < custMinI) {
                        custMinI = curI;
                        custMinJ = curJ;
                    } else if (curI == custMinI) {
                        if (curJ < custMinJ) {
                            custMinJ = curJ;
                        }
                    }

                } else {
                    if (curLen >= fuel) return -1;

                    for (int d=0; d<4; d++) {
                        int nextI = curI + di[d];
                        int nextJ = curJ + dj[d];
                        if (!validCoordinate(nextI,nextJ)) continue;
                        if (visited[nextI][nextJ]) continue;
                        if (map[nextI][nextJ] == 1) continue;
                        visited[nextI][nextJ] = true;
                        queue.offer(new int[]{nextI,nextJ,curLen+1});
                    }
                }
            } else {
                break;
            }

        }

        if (custMinI == Integer.MAX_VALUE || custMinJ == Integer.MAX_VALUE) return -1;
//        System.out.println("custMinI = " + custMinI);
//        System.out.println("custMinJ = " + custMinJ);
        int custNo = customers[custMinI][custMinJ].get(0);
        customers[custMinI][custMinJ].remove(0);
        fuel -= useFuel;
        taxiI = custMinI;
        taxiJ = custMinJ;

        return custNo;
    }

    private static boolean validCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<N) return true;
        return false;
    }
}
