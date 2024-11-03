package programmers.level2.숫자카드나누기;

class 숫자카드나누기 {
	public int solution(int[] arrayA, int[] arrayB) {
		int result = 0;

		if (arrayA.length == 1) {
			if (arrayB[0] % arrayA[0] != 0) {
				result = Math.max(result, arrayA[0]);
			}

			if (arrayA[0] % arrayB[0] != 0) {
				result = Math.max(result, arrayB[0]);
			}
		}

		// A
		int candidateA = arrayA[0];
		for (int i=1; i<arrayA.length; i++) {
			candidateA = calcGCD(candidateA, arrayA[i]);
		}

		int bCnt = 0;
		for (int i=0; i<arrayB.length; i++) {
			if (arrayB[i] % candidateA == 0) bCnt++;
		}

		if (bCnt == 0) result = Math.max(result, candidateA);

		// B
		int candidateB = arrayB[0];
		for (int i=1; i<arrayB.length; i++) {
			candidateB = calcGCD(candidateB, arrayB[i]);
		}

		int aCnt = 0;
		for (int i=0; i<arrayA.length; i++) {
			if (arrayA[i] % candidateB == 0) aCnt++;
		}

		if (aCnt == 0) result = Math.max(result, candidateB);


		return result;

	}

	public int calcGCD(int a, int b) {
		if (b == 0) {
			return a;
		}

		return calcGCD(b, a % b);
	}
}