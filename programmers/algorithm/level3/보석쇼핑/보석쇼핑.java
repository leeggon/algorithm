package programmers.algorithm.level3.보석쇼핑;

import java.util.*;

class 보석쇼핑 {
	public int[] solution(String[] gems) {
		int result = Integer.MAX_VALUE;
		int resultLeft = 0;
		int resultRight = 0;

		int left = 0;
		int right = 0;

		Set<String> set = new HashSet<>();
		for (int i=0; i<gems.length; i++) {
			set.add(gems[i]);
		}

		Map<String, Integer> map = new HashMap<>();
		while (right <= gems.length - 1) {

			map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
			if (map.size() < set.size()) {
				right++;
				continue;
			}

			// right 증가시키다가 map.size == set.size가 된 경우
			// left 극한으로 끌어댕기기
			while (true) {
				if (map.get(gems[left]) > 1) {
					map.put(gems[left], map.get(gems[left]) - 1);
					left++;
					continue;
				}

				// left 극한으로 끌어댕겼는데, result보다 오,왼 차이 작은 경우 result 업데이트 침.
				if ((right - left + 1) < result) {
					resultLeft = left;
					resultRight = right;
					result = right - left + 1;
				}

				// left 더 못 땡기니깐 map에서 없애고 left++
				map.remove(gems[left]);
				left++;
				break;
			}

			right++;
		}

		return new int[]{resultLeft + 1, resultRight + 1};
	}
}