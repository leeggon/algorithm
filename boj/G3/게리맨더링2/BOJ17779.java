package boj.G3.게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17779 {
	static int result = Integer.MAX_VALUE;
	static int N;
	static int x, y, d1, d2;
	static int[][] populations;
	static int[] populationByArea;
	static int[] permRepResult = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		populations = new int[N][N];
		for (int i=0; i<N; i++) {
			populations[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				x = i; // x값 확정
				y = j; // y값 확정
				permRep(0);
			}
		}

		System.out.println(result);
	}

	private static void permRep(int cnt) {
		if (cnt == 2) {
			// System.out.println(Arrays.toString(permRepResult));
			d1 = permRepResult[0];
			d2 = permRepResult[1];

			if (!checkCriteria()) {
				return;
			}

			populationByArea = new int[5];
			calcPopulationByArea();

			int maxPopulation = Arrays.stream(populationByArea).max().getAsInt();
			int minPopulation = Arrays.stream(populationByArea).min().getAsInt();
			result = Math.min(result, maxPopulation - minPopulation);

			return;
		}

		for (int i=1; i<=N-1; i++) {
			permRepResult[cnt] = i;
			permRep(cnt+1);
		}
	}

	private static boolean checkCriteria() {
		if (d1<=y && d2<= N-1-y && d1+d2 <= N-1-x) return true;
		return false;
	}

	// 구역별로 총 인구 합계 계산
	private static void calcPopulationByArea() {
		boolean[][] border = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];

		// 경계선 세팅
		for (int i=0; i<=d1; i++) {
			border[x+i][y-i] = true;
			border[x+d2+i][y+d2-i] = true;
		}
		for (int i=0; i<=d2; i++) {
			border[x+i][y+i] = true;
			border[x+d1+i][y-d1+i] = true;
		}

		// 1구역 populationByArea[0]
		for (int r=0; r<x; r++) {
			for (int c=0; c<=y; c++) {
				populationByArea[0] += populations[r][c];
				visited[r][c] = true;
			}
		}
		for (int r=x; r<x+d1; r++) {
			for (int c=0; c<N; c++) {
				if (border[r][c]) break;
				populationByArea[0] += populations[r][c];
				visited[r][c] = true;
			}
		}

		// 2구역 populationByArea[1]
		for (int r=0; r<x; r++) {
			for (int c=N-1; c>y; c--) {
				populationByArea[1] += populations[r][c];
				visited[r][c] = true;
			}
		}
		for (int r=x; r<=x+d2; r++) {
			for (int c=N-1; c>=0; c--) {
				if (border[r][c]) break;
				populationByArea[1] += populations[r][c];
				visited[r][c] = true;
			}
		}

		// 3구역 populationByArea[2]
		for (int r=x+d1; r<=x+d1+d2; r++) {
			for (int c=0; c<N; c++) {
				if (border[r][c]) break;
				populationByArea[2] += populations[r][c];
				visited[r][c] = true;
			}
		}
		for (int r=x+d1+d2+1; r<N; r++) {
			for (int c=0; c<y-d1+d2; c++) {
				populationByArea[2] += populations[r][c];
				visited[r][c] = true;
			}
		}

		// 4구역 populationByArea[3]
		for (int r=x+d2+1; r<=x+d1+d2; r++) {
			for (int c=N-1; c>=0; c--) {
				if (border[r][c]) break;
				populationByArea[3] += populations[r][c];
				visited[r][c] = true;
			}
		}
		for (int r=x+d1+d2+1; r<N; r++) {
			for (int c=N-1; c>=y-d1+d2; c--) {
				populationByArea[3] += populations[r][c];
				visited[r][c] = true;
			}
		}

		// 5구역 populationByArea[4]
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				if (!visited[r][c]) populationByArea[4] += populations[r][c];
			}
		}
	}
}
