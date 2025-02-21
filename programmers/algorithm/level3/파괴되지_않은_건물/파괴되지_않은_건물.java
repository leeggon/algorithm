package programmers.algorithm.level3.파괴되지_않은_건물;

class 파괴되지_않은_건물 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;

        int[][] accumSum = new int[N+1][M+1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            switch(type) {
                case 1 :
                    accumSum[r1][c1] -= degree;
                    accumSum[r2+1][c1] += degree;
                    accumSum[r1][c2+1] += degree;
                    accumSum[r2+1][c2+1] -= degree;
                    break;

                case 2:
                    accumSum[r1][c1] += degree;
                    accumSum[r2+1][c1] -= degree;
                    accumSum[r1][c2+1] -= degree;
                    accumSum[r2+1][c2+1] += degree;
                    break;

                default:
                    System.out.println("wrong type");

            }
        }

        for (int r=0; r<=N; r++) {
            for (int c=1; c<=M; c++) {
                accumSum[r][c] = accumSum[r][c-1] + accumSum[r][c];
            }
        }

        for (int c=0; c<=M; c++) {
            for (int r=1; r<=N; r++) {
                accumSum[r][c] = accumSum[r-1][c] + accumSum[r][c];
            }
        }

        for (int r=0; r<N; r++) {
            for (int c=0; c<M; c++) {
                if (board[r][c] + accumSum[r][c] > 0) answer++;
            }
        }


        return answer;
    }
}
