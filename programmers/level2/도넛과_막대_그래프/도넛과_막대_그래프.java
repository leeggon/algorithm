package programmers.level2.도넛과_막대_그래프;

import java.util.*;

class 도넛과_막대_그래프 {
    public int[] solution(int[][] edges) {
        int[] result = {0,0,0,0}; // 생성 정점 번호, 도넛,막대,8자

        int totalGraphCnt = 0;
        Map<Integer,int[]> map = new HashMap<>();
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            map.putIfAbsent(from,new int[]{0,0});
            map.putIfAbsent(to,new int[]{0,0});
            map.get(from)[1]++;
            map.get(to)[0]++;
        }

        for(int key: map.keySet()) {
            // 도넛은 판단 불가. 다 똑같이 생김.
            // if (map.get(key)[0] >=1 && map.get(key)[1] == 1) {
            //     result[1]++;
            // }
            // 막대
            if (map.get(key)[0] >= 1 && map.get(key)[1] == 0) {
                result[2]++;
            }
            // 8자
            else if (map.get(key)[0] >= 2 && map.get(key)[1] == 2) {
                result[3]++;
            }
            // 추가한 정점
            else if (map.get(key)[0] == 0 && map.get(key)[1] >= 2) {
                result[0] = key;
                totalGraphCnt = map.get(key)[1];
            }
        }

        result[1] = totalGraphCnt - result[2] - result[3];

        return result;
    }
}
