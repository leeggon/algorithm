package programmers.algorithm.level3.다단계_칫솔_판매;

import java.util.*;

class 다단계_칫솔_판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] result = new int[enroll.length];

        Map<String, String> map = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i=0; i<enroll.length; i++) {
            map.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }

        for (int i=0; i<seller.length; i++) {
            int money = amount[i] * 100;
            String earnPerson = seller[i];

            while (true) {
                int index = indexMap.get(earnPerson);
                result[index] += money - (int) Math.floor(money * 0.1);

                if ((int) Math.floor(money * 0.1) == 0) break;

                if (map.get(earnPerson).equals("-")) {
                    break;
                }

                money = (int) Math.floor(money * 0.1);
                earnPerson = map.get(earnPerson);
            }
        }

        return result;
    }
}
