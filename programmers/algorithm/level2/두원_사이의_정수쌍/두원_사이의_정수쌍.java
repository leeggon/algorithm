package programmers.algorithm.level2.두원_사이의_정수쌍;

class 두원_사이의_정수쌍 {
    public long solution(int r1, int r2) {
        long axisCnt = r2 - r1 + 1;
        long planeCnt = 0;
        for (int x=1; x<r1; x++) {
            int high = (int) Math.sqrt((long)r2*r2 - (long)x*x);
            int low = (int) Math.ceil(Math.sqrt((long)r1*r1 - (long)x*x));
            planeCnt += (high - low) + 1;
        }

        for (int x=r1; x<r2; x++) {
            int high = (int) Math.sqrt((long)r2*r2 - (long)x*x);
            planeCnt += high;
        }

        return (axisCnt + planeCnt) * 4;
    }
}
