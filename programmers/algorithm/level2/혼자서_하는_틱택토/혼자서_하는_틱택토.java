package programmers.algorithm.level2.혼자서_하는_틱택토;

import java.util.*;

class 혼자서_하는_틱택토 {
    static int oCnt;
    static int xCnt;
    static int answer = 0;
    static int[] oPermResult;
    static int[] xPermResult;

    static boolean[] isOSelected;
    static boolean[] isXSelected;

    static List<Integer> oList;
    static List<Integer> xList;

    public static int solution(String[] board) {
        oList = new ArrayList<>();
        xList = new ArrayList<>();

        for (int i=0; i<board.length; i++) {
            String s = board[i];
            for (int j=0; j<s.length(); j++) {
                if (s.charAt(j) == 'O') {
                    oList.add(3*i + j);
                } else if (s.charAt(j) == 'X') {
                    xList.add(3*i + j);
                }
            }
        }

        oCnt = oList.size();
        xCnt = xList.size();
        if (oCnt == 0 && xCnt == 0) return 1;
        if (oCnt < xCnt || oCnt > xCnt+1) return 0;

        // 각 순열 후 카르테시안 경우의 수
        oPermResult = new int[oCnt];
        isOSelected = new boolean[oCnt];
        oPerm(0);

        return answer;
    }

    private static void oPerm(int cnt) {
        if (cnt == oCnt) {

            if (answer == 1) return;

            xPermResult = new int[xCnt];
            isXSelected = new boolean[xCnt];
            xPerm(0);

            return;
        }

        for (int i=0; i<oCnt; i++) {
            if (isOSelected[i]) continue;

            isOSelected[i] = true;
            oPermResult[cnt] = oList.get(i);
            oPerm(cnt+1);
            isOSelected[i] = false;
        }


    }

    private static void xPerm(int cnt) {
        if (cnt == xCnt) {

            Set<Integer> oSet = new HashSet<>();
            Set<Integer> xSet = new HashSet<>();

            int totalTurn = oCnt + xCnt;
            int turnIndex = 0;
            outer : while (turnIndex < totalTurn) {
                if (turnIndex == totalTurn - 1) {
                    answer = 1;
                }

                if (turnIndex%2 == 0) { // O 공격
                    oSet.add(oPermResult[turnIndex / 2]);
                } else { // X 공격
                    xSet.add(xPermResult[turnIndex / 2]);
                }

                if ((oSet.contains(0) && oSet.contains(1) && oSet.contains(2)) ||
                        (oSet.contains(3) && oSet.contains(4) && oSet.contains(5)) ||
                        (oSet.contains(6) && oSet.contains(7) && oSet.contains(8)) ||
                        (oSet.contains(0) && oSet.contains(3) && oSet.contains(6)) ||
                        (oSet.contains(1) && oSet.contains(4) && oSet.contains(7)) ||
                        (oSet.contains(2) && oSet.contains(5) && oSet.contains(8)) ||
                        (oSet.contains(0) && oSet.contains(4) && oSet.contains(8)) ||
                        (oSet.contains(2) && oSet.contains(4) && oSet.contains(6))) {
                    if (turnIndex == totalTurn - 1) {
                        answer = 1;
                    } else {
                        break outer;
                    }
                }

                if ((xSet.contains(0) && xSet.contains(1) && xSet.contains(2)) ||
                        (xSet.contains(3) && xSet.contains(4) && xSet.contains(5)) ||
                        (xSet.contains(6) && xSet.contains(7) && xSet.contains(8)) ||
                        (xSet.contains(0) && xSet.contains(3) && xSet.contains(6)) ||
                        (xSet.contains(1) && xSet.contains(4) && xSet.contains(7)) ||
                        (xSet.contains(2) && xSet.contains(5) && xSet.contains(8)) ||
                        (xSet.contains(0) && xSet.contains(4) && xSet.contains(8)) ||
                        (xSet.contains(2) && xSet.contains(4) && xSet.contains(6))) {
                    if (turnIndex == totalTurn - 1) {
                        answer = 1;
                    } else {
                        break outer;
                    }
                }

                turnIndex++;
            }

            return;
        }

        for (int i=0; i<xCnt; i++) {
            if (isXSelected[i]) continue;

            isXSelected[i] = true;
            xPermResult[cnt] = xList.get(i);
            xPerm(cnt+1);
            isXSelected[i] = false;
        }
    }
}
