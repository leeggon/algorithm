package programmers.algorithm.level2.이모티콘_할인행사;

class 이모티콘_할인행사 {
    static int emtiCnt;
    static int[] permResult;
    static int[] discounts = {10,20,30,40};
    static int maxServicePeople = 0;
    static int maxSell = 0;
    public static int[] solution(int[][] users, int[] emoticons) {

        emtiCnt = emoticons.length;
        permResult = new int[emtiCnt];
        perm(0, users, emoticons);

        return new int[]{maxServicePeople, maxSell};
    }

    private static void perm(int n, int[][] users, int[] emoticons) {
        if (n == emtiCnt) {
            int servicePeople = 0;
            int sell = 0;
            // System.out.println(Arrays.toString(permResult));
            for (int[] user: users) {
                int userCost = 0;
                int userMinDiscount = user[0];
                int userMaxMoney = user[1];

                for (int i=0; i<permResult.length; i++) {
                    int emtiDisc = permResult[i];
                    if (emtiDisc >= userMinDiscount) {
                        userCost += emoticons[i] * (100-emtiDisc) / 100;
                    }
                }

                if (userCost >= userMaxMoney) {
                    servicePeople++;
                } else {
                    sell += userCost;
                }
            }

            if (servicePeople > maxServicePeople) {
                maxServicePeople = servicePeople;
                maxSell = sell;
            } else if (servicePeople == maxServicePeople) {
                if (sell > maxSell) {
                    maxSell = sell;
                }
            }

            return;
        }

        for (int i=0; i<4; i++) {
            permResult[n] = discounts[i];
            perm(n+1, users, emoticons);
        }

    }

}
