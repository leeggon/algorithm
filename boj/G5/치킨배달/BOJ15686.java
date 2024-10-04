package boj.G5.치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {
	static int result = Integer.MAX_VALUE;
	static int N, M;
	static int[][] map;
	static Pos[] chickenCombResult;
	static boolean[] isSelected;
	static List<Pos> homes = new ArrayList<>();
	static List<Pos> chickens = new ArrayList<>();

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
		map = new int[N][N];
		chickenCombResult = new Pos[M];
		for (int i=0; i<N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j=0; j<N; j++) {
				if (map[i][j] == 1) {
					homes.add(new Pos(i, j));
				} else if (map[i][j] == 2) {
					chickens.add(new Pos(i, j));
				}
			}
		}

		comb(0, 0);

		System.out.println(result);
	}

	// chickens.size() 중에 chicken M개 뽑기
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			// chickenCombResult에 치킨집 M개 다 뽑았다
			int totalDistance = 0;
			for (int i=0; i<homes.size(); i++) {
				int minDistancePerHome = Integer.MAX_VALUE;
				for (int j=0; j<chickenCombResult.length; j++) {
					int calculatedDistance = calcDistance(homes.get(i), chickenCombResult[j]);
					minDistancePerHome = Math.min(minDistancePerHome, calculatedDistance);
				}
				totalDistance += minDistancePerHome;
			}

			result = Math.min(result, totalDistance);

			return;
		}

		for (int i=start; i<chickens.size(); i++) {
			chickenCombResult[cnt] = chickens.get(i);
			comb(cnt+1, i+1);
		}
	}

	// 거리 계산
	private static int calcDistance(Pos p1, Pos p2) {
		return Math.abs(p1.i - p2.i) + Math.abs(p1.j - p2.j);
	}
}
