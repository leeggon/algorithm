package boj.G4.인구이동;

import java.io.*;
import java.util.*;

public class BOJ16234 {
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static int N, L, R;
	static int[][] A;
	static Map<Pos, List<Pos>> graph;
	static boolean[][] visited;

	static class Pos {
		int i;
		int j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;

			Pos o = (Pos) obj;
			return this.i == o.i && this.j == o.j;
		}

		@Override
		public int hashCode() {
			return 31 * Integer.hashCode(i) + Integer.hashCode(j);
		}

		@Override
		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int i=0; i<N; i++) {
			A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		// 각 테케에 대해서
		for (int t=0; t<2001; t++) {
			
			// 연결 그래프 생성
			graph = new HashMap<>();
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					Pos curPos = new Pos(i, j);
					for (int d=0; d<4; d++) {
						int nextI = i + di[d];
						int nextJ = j + dj[d];
						if (!isValidCoordinate(nextI, nextJ)) continue;
						Pos nextPos = new Pos(nextI, nextJ);

						int popDiff = Math.abs(A[i][j] - A[nextI][nextJ]);
						if (L<=popDiff && popDiff<=R) {
							if (!graph.containsKey(curPos)) graph.put(curPos, new ArrayList<>());
							graph.get(curPos).add(nextPos);
						}
					}
				}
			}

			if (graph.isEmpty()) {
				System.out.println(t);
				return;
			}

			// 쭉 훑으면서 되는 애들끼리 bfs
			visited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					Pos pos = new Pos(i, j);
					if (!graph.containsKey(pos)) continue;
					if (!visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
		}

	}

	private static void bfs(int i, int j) {
		List<Pos> bfsResults = new ArrayList<>();

		visited[i][j] = true;
		Pos initPos = new Pos(i, j);
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(initPos);
		bfsResults.add(initPos);
		int totalPrice = A[i][j];

		while (!queue.isEmpty()) {
			Pos curPos = queue.poll();
			for (int d=0; d<4; d++) {
				int nextI = curPos.i + di[d];
				int nextJ = curPos.j + dj[d];
				Pos nextPos = new Pos(nextI, nextJ);
				if (!isValidCoordinate(nextI, nextJ)) continue;

				if (!visited[nextI][nextJ] && graph.get(curPos).contains(nextPos)) {
					visited[nextI][nextJ] = true;
					queue.offer(new Pos(nextI, nextJ));
					bfsResults.add(nextPos);
					totalPrice += A[nextI][nextJ];
				}

			}
		}

		int newPrice = totalPrice / bfsResults.size();
		for (Pos pos : bfsResults) {
			A[pos.i][pos.j] = newPrice;
		}
	}

	private static boolean isValidCoordinate(int i, int j) {
		if (0<=i && i<N && 0<=j && j<N) return true;
		return false;
	}
}
