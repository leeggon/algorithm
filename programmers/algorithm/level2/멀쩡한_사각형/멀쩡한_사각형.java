package programmers.algorithm.level2.멀쩡한_사각형;

class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        long answer = 0;

        double divided = (double) h / w;
        for (int i=1; i<=w; i++) {
            answer += Math.floor(h - divided * i);
        }

        return answer * 2;
    }
}
