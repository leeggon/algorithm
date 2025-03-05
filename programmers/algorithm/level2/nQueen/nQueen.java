package programmers.algorithm.level2.nQueen;

class nQueen {
    static int answer = 0;
    static int N;
    static boolean[] visited;
    static boolean[] leftBottomVisited;
    static boolean[] rightBottomVisited;

    public static int solution(int n) {
        N = n;
        visited = new boolean[N];
        leftBottomVisited = new boolean[2*(N-1) + 1];
        rightBottomVisited = new boolean[2*(N-1) + 1];

        dfs(0);

        return answer;
    }

    private static void dfs(int n) { // n행 상황
        if (n == N) {
            answer++;
        }

        for (int j=0; j<N; j++) {
            if (visited[j]) continue;
            if (leftBottomVisited[n+j]) continue;
            if (rightBottomVisited[n-j + (N-1)]) continue;

            visited[j] = true;
            leftBottomVisited[n+j] = true;
            rightBottomVisited[n-j + (N-1)] = true;
            dfs(n+1);
            visited[j] = false;
            leftBottomVisited[n+j] = false;
            rightBottomVisited[n-j + (N-1)] = false;

        }
    }
}
