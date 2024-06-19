package boj.G3.ACM_Craft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005 {
    static StringBuilder sb;
    static int N,K,W;
    static int[] constTime;
    static int[] startTime;
    static ArrayList<Integer>[] graph;
    static int[] in; // 진입차수 저장 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            for (int i=1; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }
            in = new int[N+1];

            st = new StringTokenizer(br.readLine());
            constTime = new int[N+1];
            for (int i=1; i<=N; i++) {
                constTime[i] = Integer.parseInt(st.nextToken());
            }
            startTime = new int[N+1];
            Arrays.fill(startTime,0);

            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                in[e]++;
            }

//            System.out.println(Arrays.toString(in));

            W = Integer.parseInt(br.readLine());

            TopologicalSort();
        }

        System.out.println(sb.toString().trim());
    }

    private static void TopologicalSort() {

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            if (in[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curV = queue.poll();
            if (curV == W) {
                sb.append(startTime[curV] + constTime[curV]).append("\n");
                break;
            }

            for (int i=0; i<graph[curV].size(); i++){
                int nextV = graph[curV].get(i);
                if (--in[nextV] == 0) queue.offer(nextV);
                startTime[nextV] = Math.max(startTime[nextV], startTime[curV] + constTime[curV]);
            }

        }

    }
}
