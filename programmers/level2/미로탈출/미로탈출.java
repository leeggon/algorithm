package programmers.level2.미로탈출;

import java.util.*;

class 미로탈출 {
	static int N, M;
	static int startI, startJ;
	static int exitI, exitJ;
	static int lebberI, lebberJ;
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static class Pos {
		int i;
		int j;

		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static int solution(String[] maps) {
		N = maps.length;
		M = maps[0].length();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (maps[i].charAt(j) == 'S') {
					startI = i;
					startJ = j;
				} else if (maps[i].charAt(j) == 'E') {
					exitI = i;
					exitJ = j;
				} else if (maps[i].charAt(j) == 'L') {
					lebberI = i;
					lebberJ = j;
				}
			}
		}

		int result = 0;
		int distance = bfs(startI, startJ, lebberI, lebberJ, maps);
		if (distance == -1) return -1;
		result += distance;

		distance = bfs(lebberI, lebberJ, exitI, exitJ, maps);
		if (distance == -1) return -1;
		result += distance;

		return result;
	}

	private static int bfs(int startI, int startJ, int endI, int endJ, String[] maps) {
		int result = -1;
		boolean[][] visited = new boolean[N][M];
		Queue<Pos> queue = new ArrayDeque<>();

		visited[startI][startJ] = true;
		queue.offer(new Pos(startI, startJ));

		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			result++;
			for (int i=0; i<queueSize; i++) {
				Pos curPos = queue.poll();
				if (curPos.i == endI && curPos.j == endJ) return result;

				for (int d=0; d<4; d++) {
					int nextI = curPos.i + di[d];
					int nextJ = curPos.j + dj[d];
					if (!isValidCoordinate(nextI, nextJ)) continue;
					if (visited[nextI][nextJ]) continue;
					if (maps[nextI].charAt(nextJ) == 'X') continue;

					visited[nextI][nextJ] = true;
					queue.offer(new Pos(nextI, nextJ));
				}
			}

		}

		return -1;
	}

	private static boolean isValidCoordinate (int i, int j) {
		if (0<=i && i<N && 0<=j && j<M) return true;
		return false;
	}
}
