package programmers.algorithm.level3.호텔_방_배정;

import java.util.*;

class 호텔_방_배정 {
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        if (n == 1) {
            return new long[]{room_number[0]};
        }

        long[] answer = new long[n];
        Map<Long, Long> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            long number = room_number[i];

            if (!map.containsKey(number)) {
                map.put(number, number+1);
                answer[i] = number;
                continue;
            }

            List<Long> list = new ArrayList<>();
            long child = number;

            while (map.containsKey(child)) {
                list.add(child);
                long parent = map.get(child);

                child = parent;
            }

            map.put(child, child+1);
            for (long l : list) {
                map.put(l, child+1);
            }
            answer[i] = child;
        }


        return answer;
    }
}
