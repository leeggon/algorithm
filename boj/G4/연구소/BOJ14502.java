package boj.G4.연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
	static int result = 0;
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static int N, M;
	static int[][] map;
	static List<Pos> empties = new ArrayList<>();
	static List<Pos> viruses = new ArrayList<>();
	static Pos[] combResult = new Pos[3];

	static class Pos {
		int i;
		int j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) {
					empties.add(new Pos(i, j));
				}
			}
		}

		comb(0,0);
		System.out.println(result);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			calcSafeAreaCnt();
			return;
		}

		for (int i=start; i<empties.size(); i++) {
			combResult[cnt] = empties.get(i);
			comb(cnt+1, i+1);
		}
	}

	// 안전영역 세기 (벽 세우고, 확산)
	private static void calcSafeAreaCnt() {
		int[][] tempMap = constructWalls();
		int safeAreaCnt = diffuseViruses(tempMap);

		result = Math.max(result, safeAreaCnt);
	}

	private static int[][] constructWalls() {
		// tempMap : map 깊은 복사
		int[][] tempMap = new int[N][];
		for (int i=0; i<N; i++) {
			tempMap[i] = map[i].clone();
		}
		
		// tempMap에 현재 조합한 3개의 벽 세우기
		for (int i=0; i<3; i++) {
			Pos wall = combResult[i];
			tempMap[wall.i][wall.j] = 1;
		}

		return tempMap;
	}

	// BFS로 바이러스 확산 (초기 바이러스들 전부 큐에 넣고 시작)
	private static int diffuseViruses(int[][] tempMap) {
		boolean[][] visited = new boolean[N][M];
		Queue<Pos> queue = new ArrayDeque<>();

		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (tempMap[i][j] == 2) {
					visited[i][j] = true;
					queue.offer(new Pos(i,j));
				}
			}
		}

		// BFS 시작
		while (!queue.isEmpty()) {
			Pos curPos = queue.poll();
			for (int d=0; d<4; d++) {
				int nextI = curPos.i + di[d];
				int nextJ = curPos.j + dj[d];
				if (!isValidCoordinate(nextI, nextJ)) continue;
				if (!visited[nextI][nextJ] && tempMap[nextI][nextJ] == 0) {
					visited[nextI][nextJ] = true;
					queue.offer(new Pos(nextI, nextJ));

					tempMap[nextI][nextJ] = 2;
				}
			}
		}

		// 바이러스 다 채워졌으니, 안전영역 계산
		int safeAreaCnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (tempMap[i][j] == 0) safeAreaCnt++;
			}
		}

		return safeAreaCnt;
	}

	private static boolean isValidCoordinate(int i, int j) {
		if (0<=i && i<N && 0<=j && j<M) return true;
		return false;
	}
}
