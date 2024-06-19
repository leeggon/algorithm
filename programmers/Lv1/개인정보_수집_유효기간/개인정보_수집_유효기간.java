package programmers.Lv1.개인정보_수집_유효기간;

import java.util.*;

class 개인정보_수집_유효기간 {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        int todayNum = convertToNum(today);
        System.out.println("todayNum : " + todayNum);

        Map<String,Integer> validMonthMap = new HashMap<>();
        for(String term: terms) {
            String[] temp = term.split(" ");
            validMonthMap.put(temp[0],Integer.parseInt(temp[1]));
        }

        for(int i=0; i<privacies.length; i++) {
            String[] temp = privacies[i].split(" ");
            int privacyNum = convertToNum(temp[0]) + 28*validMonthMap.get(temp[1]);
            if(privacyNum <= todayNum) {
                result.add(i+1);
            }
        }

        return result;
    }

    // 2022.05.19
    private int convertToNum(String str) {
        String[] temp = str.split("\\.");
        int year = Integer.parseInt(temp[0]);
        int month = Integer.parseInt(temp[1]);
        int date = Integer.parseInt(temp[2]);

        return 28*12*year + 28*month + date;
    }
}
