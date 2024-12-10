package programmers.algorithm.level3.징검다리_건너기;

class 징검다리_건너기 {
	static int N;
	static int K;
	public static int solution(int[] stones, int k) {
		K = k;
		N = stones.length;

		return binarySearch(stones, 1, 200000000);
	}

	private static int binarySearch(int[] stones, int left, int right) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (checkAvailableCross(stones, mid)) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	private static boolean checkAvailableCross(int[] stones, int dec) {
		int[] tempStones = new int[N];
		for (int i=0; i<N; i++) {
			int stone = stones[i] - dec;
			tempStones[i] = (stone < 0) ? 0 : stone;
		}

		// K로 판별
		int tempMaxCnt = 0;
		for (int i=0; i<N; i++) {
			if (tempStones[i] == 0) { // 0인 경우
				if (++tempMaxCnt >= K) {
					return false;
				}
			} else { // 0 아닌 경우
				tempMaxCnt = 0;
			}
		}

		return true;
	}
}
