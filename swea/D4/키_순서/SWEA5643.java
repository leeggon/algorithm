package swea.D4.키_순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA5643 {
    static int N,M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] known;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine()); // N개 정점
            M = Integer.parseInt(br.readLine());
            graph = new ArrayList[N+1];
            for (int i=0; i<N+1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }

            known = new int[N+1];
            // i번 정점에서 시작하는 dfs
            for (int i=1; i<=N; i++) {
                visited = new boolean[N+1];
                dfs(i,i);
            }

//          System.out.println(Arrays.toString(known));
            int result = 0;
            for (int i=1; i<=N; i++) {
                if (known[i] == N-1) result++;
            }

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int curV, int startV) {
        visited[curV] = true;
        if (curV != startV) {
            known[startV]++;
            known[curV]++;
        }

        for (int nextV : graph[curV]) {
            if (!visited[nextV]) {
                dfs(nextV,startV);
            }
        }

    }
}
