package boj.G5.상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21608 {
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,-1,0,1};
	static int N;
	static int[][] map;
	static Map<Integer, List<Integer>> likingStudentsMap;
	static int[] studentOrders;

	static class Pos implements Comparable<Pos>{
		int i;
		int j;
		int adjacentLikingCnt;
		int adjacentEmpty;

		public Pos (int i, int j, int adjacentLikingCnt, int adjacentEmpty) {
			this.i = i;
			this.j = j;
			this.adjacentLikingCnt = adjacentLikingCnt;
			this.adjacentEmpty = adjacentEmpty;
		}

		public int getI() {
			return this.i;
		}

		public int getJ() {
			return this.j;
		}

		public int getAdjacentLikingCnt() {
			return this.adjacentLikingCnt;
		}

		public int getAdjacentEmpty() {
			return this.adjacentEmpty;
		}

		@Override
		public int compareTo(Pos other) {
			if (this.adjacentLikingCnt != other.adjacentLikingCnt) {
				return Integer.compare(other.adjacentLikingCnt, this.adjacentLikingCnt); // other가 앞에 나오면(튀면) 내림차순
			}

			if (this.adjacentEmpty != other.adjacentEmpty) {
				return Integer.compare(other.adjacentEmpty, this.adjacentEmpty);
			}

			if (this.i != other.i) {
				return Integer.compare(this.i, other.i);
			}

			return Integer.compare(this.j, other.j);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		studentOrders = new int[N*N];
		likingStudentsMap = new HashMap<>();

		for (int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());

			List<Integer> list = new ArrayList<>();
			for (int j=0; j<4; j++) {
				int to = Integer.parseInt(st.nextToken());
				list.add(to);
			}
			likingStudentsMap.put(from, list);
			studentOrders[i] = from;
		}

		for (int studentNumber : studentOrders) {
			// 현재 학생
			int curStudent = studentNumber;
			// 좋아하는 학생들
			List<Integer> likingStudents = likingStudentsMap.get(studentNumber);

			PriorityQueue<Pos> pq = new PriorityQueue<>();

			// map 각 좌석 확인
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j] > 0) continue; // 이미 누가 있는 자리면 continue

					// 인접 체크
					int adjacentLikingCnt = 0;
					int adjacentEmptyCnt = 0;
					for (int d=0; d<4; d++) {
						int nextI = i + di[d];
						int nextJ = j + dj[d];
						if (!validCoordinate(nextI,nextJ)) continue;
						if (map[nextI][nextJ] == 0) adjacentEmptyCnt++;
						if (likingStudents.contains(map[nextI][nextJ])) adjacentLikingCnt++;
					}

					Pos addPos = new Pos(i, j, adjacentLikingCnt, adjacentEmptyCnt);
					pq.offer(addPos);
				}
			}

			// pq 젤 높은 좌석 추출. 그 자리로 좌석 배치. adjacentLikings도 기록
			Pos seat = pq.peek();
			map[seat.i][seat.j] = curStudent;
		}

		// 좌석 배치는 끝. 만족도 추출
		int[] scores = new int[]{0,1,10,100,1000};
		// 다시 맵 확인하면서 만족도 계산
		int totalScore = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int adjacentLikingCnt = 0;
				for (int d=0; d<4; d++) {
					int nextI = i + di[d];
					int nextJ = j + dj[d];
					if (!validCoordinate(nextI, nextJ)) continue;
					if (likingStudentsMap.get(map[i][j]).contains(map[nextI][nextJ])) adjacentLikingCnt++;
				}
				totalScore += scores[adjacentLikingCnt];
			}
		}

		System.out.println(totalScore);
	}

	private static boolean validCoordinate(int i, int j) {
		if (0<=i && i<N && 0<=j && j<N) return true;
		return false;
	}
}
