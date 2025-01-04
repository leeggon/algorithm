package boj.G4.N_Queen;

import java.io.*;
import java.util.*;

public class N_Queen {
    static int answer = 0;
    static int N;
    static boolean[] visited;
    static boolean[] plusVisited;
    static boolean[] minusVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        plusVisited = new boolean[2*(N-1) + 1];
        minusVisited = new boolean[2*(N-1) + 1];

        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int n) {
        if (n == N) {
            answer++;
            return;
        }

        for (int j=0; j<N; j++) {
            if (!visited[j] && !plusVisited[n+j] && !minusVisited[n-j+(N-1)]) {
                visited[j] = true;
                plusVisited[n+j] = true;
                minusVisited[n-j+(N-1)] = true;
                dfs(n+1);
                visited[j] = false;
                plusVisited[n+j] = false;
                minusVisited[n-j+(N-1)] = false;
            }
        }
    }
}
