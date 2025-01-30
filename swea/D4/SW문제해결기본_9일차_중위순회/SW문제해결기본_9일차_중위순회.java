package swea.D4.SW문제해결기본_9일차_중위순회;

import java.io.*;
import java.util.*;

public class SW문제해결기본_9일차_중위순회 {
    static int N;
    static ArrayList<Integer>[] graph;
    static char[] letters;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        for (int tc=0; tc<10; tc++) {
            sb.append("#").append(tc+1).append(" ");
            N = Integer.parseInt(br.readLine());
            graph = new ArrayList[N+1];
            for (int i=1; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }

            letters = new char[N+1];
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);
                letters[n] = c;

                if (st.hasMoreTokens()) {
                    graph[n].add(Integer.parseInt(st.nextToken()));
                }

                if (st.hasMoreTokens()) {
                    graph[n].add(Integer.parseInt(st.nextToken()));
                }
            }

            dfs(1);

            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void dfs(int n) {

        if (graph[n].size() >= 1) {
            dfs(graph[n].get(0));
        }
        // sb 처리, visited 처리
        sb.append(letters[n]);

        if (graph[n].size() >= 2) {
            dfs(graph[n].get(1));
        }
    }

}
