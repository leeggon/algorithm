package programmers.algorithm.level2.후보키;

import java.util.*;

class 후보키 {
    static Set<String> combSet = new HashSet<>();
    static int answer = 0;
    static int[] combResult;
    static int C;
    public static int solution(String[][] relation) {
        C = relation[0].length;
        for (int i=1; i<=C; i++) {
            combResult = new int[i];
            // 속성 C개 중에 i개 뽑기
            comb(0,0,i,relation);
        }

        return answer;
    }

    private static void comb(int cnt, int start, int toChoose, String[][] relation) {
        if (cnt == toChoose) {

            int distinctCnt = 0;

            Set<String> set = new HashSet<>();
            for (int i=0; i<relation.length; i++) {
                String[] student = relation[i];
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<combResult.length; j++) {
                    sb.append(student[combResult[j]]);
                }

                String sbToString = sb.toString();

                if (!set.contains(sbToString)) {
                    distinctCnt++;
                    set.add(sbToString);
                }
            }

            if (distinctCnt == relation.length) {
                StringBuilder combSb = new StringBuilder();
                for (int c : combResult) {
                    combSb.append(String.valueOf(c));
                }

                String combSbToString = combSb.toString();

                boolean isAvail = true;
                outer : for (String combS : combSet) {
                    int sameCnt = 0;
                    for (int i=0; i<combSbToString.length(); i++) {
                        char c = combSbToString.charAt(i);
                        for (int j=0; j<combS.length(); j++) {
                            if (c == combS.charAt(j)) {
                                sameCnt++;
                            }
                        }
                    }

                    if (sameCnt >= combS.length()) {
                        isAvail = false;
                        break outer;
                    }
                }

                if (isAvail) {
                    answer++;
                    combSet.add(combSbToString);
                }
            }

            return;
        }

        for (int i=start; i<C; i++) {
            combResult[cnt] = i;
            comb(cnt+1, i+1, toChoose,relation);
        }
    }
}
