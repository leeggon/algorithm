package 삼성SW역량테스트.마법의숲탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법의숲탐색 {
	static int result = 0;
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	static Pos[] exits;
	static Pos[] checkPossibleBottoms = {new Pos(1,-1), new Pos(2,0), new Pos(1,1)};
	static Pos[] checkPossibleWests = {new Pos(-1,-1), new Pos(0,-2), new Pos(1,-2), new Pos(1,-1), new Pos(2,-1)};
	static Pos[] checkPossibleEasts = {new Pos(-1,1), new Pos(0,2), new Pos(1,2), new Pos(1,1), new Pos(2,1)};
	static int R,C,K;
	static int[][] map;

	static class Pos {
		int i;
		int j;

		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "i : " + i + " j : " + j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R+3][C];
		initializeMap();

		exits = new Pos[K];

		outer : for (int i=0; i<K; i++) {

			st = new StringTokenizer(br.readLine());
			int curI = 1;
			int curJ = Integer.parseInt(st.nextToken()) - 1;
			int curD = Integer.parseInt(st.nextToken());

			// 남쪽 체크
			outerSouth : while (true) {
				if (curI == R+1) break outerSouth; // 끝자락이라면 서쪽 검색하러 넘겨버림

				for (Pos pos : checkPossibleBottoms) {
					int nextI = curI + pos.i;
					int nextJ = curJ + pos.j;
					if (map[nextI][nextJ] > -1) { // 못 내려가는 상황
						break outerSouth;

					}
				}

				// 모든 조건 통과했다면
				curI += 1;
			}

			// 서쪽 체크
			outerWest : while (true) {
				for (Pos pos : checkPossibleWests) {
					int nextI = curI + pos.i;
					int nextJ = curJ + pos.j;
					if (!isValidCoordinate(nextI, nextJ) || map[nextI][nextJ] > -1) {
						break outerWest;
					}
				}

				// 모든 조건 통과했다면
				curI += 1;
				curJ -= 1;
				curD = (curD + 3) % 4;
			}

			// 동쪽 체크
			outerEast : while (true) {
				for (Pos pos : checkPossibleEasts) {
					int nextI = curI + pos.i;
					int nextJ = curJ + pos.j;
					if (!isValidCoordinate(nextI, nextJ) || map[nextI][nextJ] > -1) {
						break outerEast;
					}
				}

				// 모든 조건 통과했다면
				curI += 1;
				curJ += 1;
				curD = (curD + 1) % 4;
			}

			// 다 내려왔음.
			// 근데 여전히 몸 일부 밖
			if (curI <= 3) {
				initializeMap();
				continue outer;
			}

			// map 색칠
			map[curI][curJ] = i;
			for (int d=0; d<4; d++) {
				int nextI = curI + di[d];
				int nextJ = curJ + dj[d];
				map[nextI][nextJ] = i;
			}

			// exit 갱신
			exits[i] = new Pos(curI + di[curD], curJ + dj[curD]);

			// BFS
			result += bfs(curI, curJ);

		}

		System.out.println(result);
	}

	private static int bfs(int i, int j) {
		int maxI = 0;

		boolean[][] visited = new boolean[R+3][C];
		visited[i][j] = true;
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(i, j));

		while (!queue.isEmpty()) {
			Pos curPos = queue.poll();
			maxI = Math.max(maxI, curPos.i);
			int exitI = exits[map[curPos.i][curPos.j]].i;
			int exitJ = exits[map[curPos.i][curPos.j]].j;

			if (curPos.i == exitI && curPos.j == exitJ) { // 현재 출구라면
				for (int d=0; d<4; d++) {
					int nextI = curPos.i + di[d];
					int nextJ = curPos.j + dj[d];
					if (!isValidCoordinate(nextI, nextJ)) continue;
					if (!visited[nextI][nextJ] && map[nextI][nextJ] > -1) {
						visited[nextI][nextJ] = true;
						queue.offer(new Pos(nextI, nextJ));
					}
				}
			} else { // 출구 아닌 일반 장소면
				for (int d=0; d<4; d++) {
					int nextI = curPos.i + di[d];
					int nextJ = curPos.j + dj[d];
					if (!isValidCoordinate(nextI, nextJ)) continue;
					if (!visited[nextI][nextJ] && map[nextI][nextJ] == map[curPos.i][curPos.j]) {
						visited[nextI][nextJ] = true;
						queue.offer(new Pos(nextI, nextJ));
					}
				}
			}

		}

		return maxI - 2;
	}

	private static void initializeMap() {
		for (int i=0; i<R+3; i++) {
			Arrays.fill(map[i], -1);
		}
		exits = new Pos[K];
	}

	private static boolean isValidCoordinate(int i, int j) {
		if (0<=i && i<R+3 && 0<=j && j<C) return true;
		return false;
	}
}
