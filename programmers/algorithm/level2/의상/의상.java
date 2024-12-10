package programmers.algorithm.level2.의상;

import java.util.*;

class 의상 {
    public int solution(String[][] clothes) {
        Map<String,Integer> map = new HashMap<>();
        for (int i=0; i<clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }

        Set<String> keys = map.keySet();
        int result = 1;
        for (String key : keys) {
            System.out.println(map.get(key));
            result *= (map.get(key) + 1);
        }

        return result - 1;
    }
}