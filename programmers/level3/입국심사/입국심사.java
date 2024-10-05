package programmers.level3.입국심사;

import java.util.Arrays;

public class 입국심사 {
	public long solution(int n, int[] times) {
		long result = 0;
		Arrays.sort(times);

		long left = 0;
		long right = times[times.length-1] * (long)n;
		long mid;

		while(left <= right) {
			mid = (left + right) / 2;
			long availPeople = 0;
			for (int time : times) {
				availPeople += mid / time;
			}

			if (n > availPeople) {
				left = mid + 1;
			} else {
				result = mid;
				right = mid - 1;
			}
		}

		return result;
	}
}
