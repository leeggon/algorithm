package programmers.algorithm.level2.광물_캐기;

import java.util.*;

class 광물_캐기 {

    class Mineral {
        int diamond;
        int iron;
        int stone;

        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }

        @Override
        public String toString() {
            return "diamond : " + diamond + " iron : " + iron + " stone : " + stone;
        }
    }

    int[][] scoreBoard;
    List<Mineral> list;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        scoreBoard = new int[][]{{1,1,1}, {5,1,1}, {25,5,1}};

        int totalPicks = Arrays.stream(picks).sum();
        list = new ArrayList<>();

        for (int i=0; i<minerals.length; i+=5) {
            if (totalPicks == 0) break; // 곡괭이 하나씩 쓸 때마다

            int dia = 0;
            int iron = 0;
            int stone = 0;
            for (int j=i; j<i+5; j++) {
                if (j == minerals.length) break;

                // 현재 순회중인 광물
                String mineral = minerals[j];
                int val = mineral.equals("diamond") ? 0 : mineral.equals("iron") ? 1 : 2;

                // 각각 다이아, 철, 돌 곡괭이로 채굴했을 때 드는 비용
                dia += scoreBoard[0][val];
                iron += scoreBoard[1][val];
                stone += scoreBoard[2][val];
            }

            list.add(new Mineral(dia, iron, stone));
            totalPicks--; // 곡괭이 하나씩 소모
        }

        System.out.println(list);
        // 돌로 캤을 때 가장 피로도가 높은 순서로 list 정렬.
        Collections.sort(list, (o1, o2) -> (o2.stone - o1.stone));

        // 돌로 캤을 때 가장 피로도가 높은 경우를 최대한 다이아몬드 곡괭이를 활용해서 캐야함. 다이아 -> 철 -> 돌 곡괭이 순으로 순회
        for (Mineral m : list) {
            int dia = m.diamond;
            int iron = m.iron;
            int stone = m.stone;

            if (picks[0] > 0) {
                answer += dia;
                picks[0]--;
                continue;
            }

            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }

            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }

        return answer;
    }


}
