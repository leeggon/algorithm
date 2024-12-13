package programmers.algorithm.level3.경주로_건설;

import java.util.*;

class 경주로_건설 {
    int[] di = {-1,0,1,0};
    int[] dj = {0,-1,0,1};
    int[][][] cost;
    int N, M;

    class Node {
        int i;
        int j;
        int cost;
        int direction;

        public Node(int i, int j, int cost, int direction) {
            this.i = i;
            this.j = j;
            this.cost = cost;
            this.direction = direction;
        }
    }

    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;
        cost = new int[4][N][M];
        for (int i=0; i<4; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0,0,0,-1)); // 맨 처음 케이스만 임시로 방향 -1을 넣어준다.
        cost[0][0][0] = 0;
        cost[1][0][0] = 0;
        cost[2][0][0] = 0;
        cost[3][0][0] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            for (int d=0; d<4; d++) {
                int nextI = curNode.i + di[d];
                int nextJ = curNode.j + dj[d];
                if (!isValidCoordinate(nextI, nextJ) || board[nextI][nextJ] == 1) continue;

                int newCost = curNode.cost;
                if (curNode.direction == -1 || curNode.direction == d) {
                    newCost += 100;
                } else {
                    newCost += 600;
                }

                if (newCost < cost[d][nextI][nextJ]) {
                    cost[d][nextI][nextJ] = newCost;
                    queue.offer(new Node(nextI, nextJ, newCost, d));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int d=0; d<4; d++) {
            result = Math.min(result, cost[d][N-1][M-1]);
        }

        return result;
    }

    private boolean isValidCoordinate(int i, int j) {
        if (0<=i && i<N && 0<=j && j<M) return true;
        return false;
    }
}
