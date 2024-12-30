package programmers.algorithm.level3.가장_긴_팰린드롬;

class 가장_긴_팰린드롬 {
    public int solution(String s) {
        int answer = 0;
        int n = s.length();

        // 홀수 팰린드롬
        for (int i=0; i<n; i++) {
            int move = 0;
            while ((i - move) >= 0 && (i + move) < s.length() && s.charAt(i - move) == s.charAt(i + move)) {
                move++;
            }
            answer = Math.max(answer, 2 * move - 1);
        }

        // 짝수 팰린드롬
        for (int i=0; i<n-1; i++) {
            int move = 0;
            while ((i - move) >= 0 && (i + move + 1) < s.length() && s.charAt(i - move) == s.charAt(i + move + 1)) {
                move++;
            }
            answer = Math.max(answer, 2 * move);
        }

        return answer;
    }
}
