package programmers.algorithm.level2.줄서는방법;

import java.util.*;

class 줄서는방법 {
	public int[] solution(int n, long k) {
		List<Integer> numList = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			numList.add(i);
		}

		long dividor = 1;
		for (int i=1; i<n; i++) {
			dividor = dividor * i;
		}

		int[] result = new int[n];
		k--;
		for (int i=n; i>=1; i--) {
			long q = k / dividor;
			long r = k % dividor;
			result[n - i] = numList.get((int) q);
			numList.remove((int) q);

			k = r;

			if (i > 1) {
				dividor /= (i - 1);
			}
		}

		return result;
	}
}
