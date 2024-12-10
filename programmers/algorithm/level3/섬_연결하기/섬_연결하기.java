package programmers.algorithm.level3.섬_연결하기;

import java.util.*;

class 섬_연결하기 {
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		public Edge (int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.cost != o.cost) {
				return Integer.compare(this.cost, o.cost);
			}

			return 0;
		}

		@Override
		public String toString() {
			return "from : " + from + " to : " + to + " cost : " + cost;
		}
	}

	public static int solution(int n, int[][] costs) {
		Edge[] edges = new Edge[costs.length];
		for (int i=0; i<costs.length; i++) {
			int[] infos = costs[i];
			int from = infos[0];
			int to = infos[1];
			int cost = infos[2];
			edges[i] = new Edge(from, to, cost);
		}

		Arrays.sort(edges);

		makeSet(n);

		int result = 0;
		int cnt = 0;
		for (int i=0; i<edges.length; i++) {
			Edge edge = edges[i];
			// 병합 과정
			if (!union(edge.from, edge.to)) continue;
			// 병합 성공했다면
			result += edge.cost;
			if (++cnt == n - 1) return result;
		}

		return 0;
	}

	private static void makeSet(int n) {
		parents = new int[n];
		for (int i=0; i<n; i++) {
			parents[i] = i;
		}
	}

	private static int findParent(int x) {
		if (parents[x] == x) return x;

		return parents[x] = findParent(parents[x]);
	}

	private static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if (aRoot == bRoot) return false;

		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}

		return true;
	}
}
