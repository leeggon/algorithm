package boj.G4.미세먼지안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int result = 0;
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static int R,C,T;
	static int[][] map;
	static int[][] increments;
	static Pos[] cleaners = new Pos[2];

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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		increments = new int[R][C];
		int cleanerIndex = 0;

		for (int i=0; i<R; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j=0; j<C; j++) {
				if (map[i][j] == -1) {
					cleaners[cleanerIndex++] = new Pos(i, j);
				}
			}
		}

		// T초 간 해프닝
		for (int i=0; i<T; i++) {
			diffuseDust();
			clean();
		}

		// T초 후 미세먼지 양 출력
		getTotalDust();

		System.out.println(result);
	}

	private static boolean isValidCoordinate(int i, int j) {
		if (0<=i && i<R && 0<=j && j<C) return true;
		return false;
	}

	// 미세먼지 확산
	private static void diffuseDust() {
		// increments 배열 작성
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j] == -1 || map[i][j] == 0) continue;

				// 인접 확인
				int x = map[i][j] / 5;
				int diffusionCnt = 0;
				for (int d=0; d<4; d++) {
					int nextI = i + di[d];
					int nextJ = j + dj[d];
					if (!isValidCoordinate(nextI, nextJ)) continue;
					if (map[nextI][nextJ] == -1) continue;

					increments[nextI][nextJ] += x;
					diffusionCnt++;
				}

				increments[i][j] -= x * diffusionCnt;
			}
		}
		
		// map 업데이트
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j] == -1) continue;
				map[i][j] += increments[i][j];
			}
		}
		
		// increments 배열 초기화
		for (int i=0; i<R; i++) {
			Arrays.fill(increments[i], 0);
		}
	}

	private static void clean() {
		int dIncrement = 0;
		
		for (int i=0; i<cleaners.length; i++) { // 위 청소기, 아래 청소기
			Pos cleaner = cleaners[i]; // 청소기
			int d = 3;

			if (i == 0) dIncrement = 1;
			else if (i == 1) dIncrement = -1;

			// 시작 위치
			int curI = cleaner.i;
			int curJ = cleaner.j;
			int nextI;
			int nextJ;
			int temp = 0;
			int nextTemp = 0;

			while(true) {
				nextI = curI + di[d];
				nextJ = curJ + dj[d];
				if(!isValidCoordinate(nextI, nextJ)) { // 다음 쓸 곳이 좌표 벗어나면
					d = (d + dIncrement) % 4;
					continue;
				}
				if (map[nextI][nextJ] == -1) break; // 다음 쓸 곳이 공청이라면 break

				nextTemp = map[nextI][nextJ];
				map[nextI][nextJ] = temp;
				temp = nextTemp;

				curI = nextI;
				curJ = nextJ;
			}
		}
	}

	private static void getTotalDust() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j] == -1 || map[i][j] == 0) continue;
				result += map[i][j];
			}
		}
	}
}
