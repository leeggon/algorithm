package programmers.algorithm.level2.점_찍기;

class 점_찍기 {
    public long solution(int k, int d) {
        long answer = 0;

        // x축 세기
        answer += (d / k) + 1;

        // 그 외
        for (int i=k; i<=(d/k)*k; i+=k) {

            int m = (int) Math.sqrt((long) d*d - (long) i*i) / k;
            answer += m + 1;
        }

        return answer;
    }
}
