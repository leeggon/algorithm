package programmers.level2.순위_검색;

import java.util.*;

class 순위_검색 {
    static Map<String,List<Integer>> map = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        for (String infos : info) {
            String[] infoSplit = infos.split(" ");
            makeMap(0, "", infoSplit);
        }

        for (String key: map.keySet()) {
            Collections.sort(map.get(key));
        }

        int[] result = new int[query.length];
        for (int i=0; i<query.length; i++) {
            String[] querySplit = query[i].split(" ");
            String key = "";
            for (int j=0; j<querySplit.length-1; j+=2) {
                key += querySplit[j];
            }

            if (map.containsKey(key)) {
                int target = Integer.parseInt(querySplit[querySplit.length-1]);
                result[i] = binarySearch(map.get(key),target);
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    private static void makeMap(int n, String key, String[] infoSplit) {
        if (n == 4) {
            if (!map.containsKey(key)) {
                List<Integer> list = new ArrayList<>();
                map.put(key,list);
            }
            map.get(key).add(Integer.parseInt(infoSplit[4]));

            return;
        }

        makeMap(n+1, key+"-", infoSplit);
        makeMap(n+1, key+infoSplit[n], infoSplit);
    }

    private static int binarySearch(List<Integer> grades, int target) {
        int left = 0;
        int right = grades.size()-1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > grades.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return grades.size() - left;
    }

}
