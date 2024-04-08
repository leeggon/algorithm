package BOJ.G2.트리의_지름;

import java.io.*;
import java.util.*;

public class BOJ1167 {
    static int V;
    static ArrayList<Integer>[] graph;
    static Map<Edge,Integer> edges;
    static int[] dp;
    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

//        @Override
//        public boolean equals(Object o) {
//            if (o != null && getClass() == o.getClass()) {
//                Edge edge = (Edge) o;
//                return this.from == edge.from && this.to == edge.to;
//            }
//            return false;
//        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V+1];
        for (int i=1; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }
        edges = new HashMap<>();

        for (int i=1; i<=V; i++) {
            st = new StringTokenizer(br.readLine());
            int connectCnt = (st.countTokens()-2)/2;
            int from = Integer.parseInt(st.nextToken());

            for (int j=0; j<connectCnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                edges.put(new Edge(from,to), cost);
            }

        }

        List<Edge> keySet = new ArrayList<>(edges.keySet());
        keySet.sort((o1, o2) -> Integer.compare(edges.get(o2), edges.get(o1)));

        dp = new int[V+1];
        Arrays.fill(dp,-1);
        int biggestV1 = 0;
        int biggestV2 = 0;
        for (Edge edge : keySet) {
            biggestV1 = edge.from;
            biggestV2 = edge.to;
            dp[biggestV1] = 0;
            dp[biggestV2] = 0;
            break;
        }

        System.out.println(dfs(biggestV1) + dfs(biggestV2) + edges.get(new Edge(biggestV1,biggestV2)));


    }

    private static int dfs(int curV) {

        dp[curV] = 0;
        for (int nextV : graph[curV]) {
            if (dp[nextV] == -1) {
                dp[curV] = Math.max(dp[curV], dfs(nextV) + edges.get(new Edge(curV,nextV)));
            }
        }

        return dp[curV];
    }
}
