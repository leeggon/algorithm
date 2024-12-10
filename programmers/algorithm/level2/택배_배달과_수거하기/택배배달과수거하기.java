package programmers.algorithm.level2;

class 택배배달과수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int startD = n;
        int startP = n;
        long result = 0;

        while (startD>0 || startP>0) {
            if (startD >=1) {
                while(deliveries[startD-1] == 0) {
                    if (--startD == 0) {
                        break;
                    }
                }
            }
            if (startP >= 1) {
                while(pickups[startP-1] == 0) {
                    if (--startP == 0) {
                        break;
                    }
                }
            }

            result += Math.max(startD,startP) * 2;

            int curCap = cap;
            if (startD >= 1) {
                while (true) {
                    if ((curCap - deliveries[startD-1]) <= 0) {
                        deliveries[startD-1] -= curCap;
                        break;
                    } else {
                        curCap -= deliveries[startD-1];
                        deliveries[startD-1] = 0;
                        if (--startD == 0) {
                            break;
                        }
                    }
                }
            }
            curCap = cap;
            if (startP >= 1) {
                while (true) {
                    if ((curCap - pickups[startP-1]) <= 0) {
                        pickups[startP-1] -= curCap;
                        break;
                    } else {
                        curCap -= pickups[startP-1];
                        pickups[startP-1] = 0;
                        if (--startP == 0) {
                            break;
                        }
                    }
                }
            }

        }

        return result;
    }
}
