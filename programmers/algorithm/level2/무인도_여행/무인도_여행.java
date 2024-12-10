package programmers.algorithm.level2.무인도_여행;

import java.util.*;

class 무인도_여행 {
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};

	static int N, M;
	static boolean[][] visited;
	static ArrayList<Integer> result;

	static class Pos {
		int i;
		int j;

		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static List<Integer> solution(String[] maps) {
		N = maps.length;
		M = maps[0].length();
		visited = new boolean[N][M];

		result = new ArrayList<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visited[i][j] && Character.isDigit(maps[i].charAt(j))) {
					result.add(bfs(i,j,maps));
				}
			}
		}

		if (result.size() == 0) return new ArrayList<>(Arrays.asList(-1));

		Collections.sort(result);
		return result;
	}

	private static int bfs(int i, int j, String[] maps) {
		int result = 0;

		visited[i][j] = true;
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(i,j));

		while (!queue.isEmpty()) {
			Pos curPos = queue.poll();
			result += maps[curPos.i].charAt(curPos.j) - '0';

			for (int d=0; d<4; d++) {
				int nextI = curPos.i + di[d];
				int nextJ = curPos.j + dj[d];
				if (!isValidCoordinate(nextI, nextJ)) continue;
				if (visited[nextI][nextJ] || Character.isLetter(maps[nextI].charAt(nextJ))) continue;
				visited[nextI][nextJ] = true;
				queue.offer(new Pos(nextI, nextJ));
			}
		}

		return result;
	}

	private static boolean isValidCoordinate(int i, int j) {
		if (0<=i && i<N && 0<=j && j<M) return true;
		return false;
	}

}
