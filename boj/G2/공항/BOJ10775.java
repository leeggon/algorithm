package boj.G2.공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10775 {
    static int G,P;
    static int[] arrivalGates;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        arrivalGates = new int[P];
        for (int i=0; i<P; i++) {
            arrivalGates[i] = Integer.parseInt(br.readLine());
        }

        makeSet();
        System.out.println(getMaxAirplanes());
    }

    private static int getMaxAirplanes() {
        int result = 0;
        for (int i=0; i< arrivalGates.length; i++) {
            int arrivalGate = arrivalGates[i];
            int arrivalGateParent = findParent(arrivalGate); // 현재 gate의 최고 조상 gate를 찾음.
            if (arrivalGateParent == 0) break; // 그게 0이라면 더이상 비행기 도킹할 곳 없음을 뜻함.

            union(arrivalGateParent-1, arrivalGateParent); // union(최고조상, 최고조상-1)
            result++;
        }

        return result;
    }

    private static void makeSet() {
        parents = new int[G+1];
        for (int i=1; i<=G; i++) {
            parents[i] = i;
        }
    }

    private static int findParent(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findParent(parents[a]);
    }

    private static void union(int a, int b) {
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if (aRoot == bRoot) return;
        parents[bRoot] = aRoot;
    }
}
