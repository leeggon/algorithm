package BOJ.G5.숨바꼭질3;

import java.util.*;

public class BOJ13549 {
    static class Pos {
        int curLoc;
        int curTime;

        public Pos(int curLoc, int curTime) {
            this.curLoc = curLoc;
            this.curTime = curTime;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "curLoc=" + curLoc +
                    ", curTime=" + curTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100020];

        queue.offer(new Pos(start,0));
        visited[start] = true;
//        System.out.println("queue : " + queue);
        while (!queue.isEmpty()) {
            // 하나 뽑아서
            Pos pos = queue.poll();
            int loc = pos.curLoc;
            if (loc == end) {
                System.out.println(pos.curTime);
                return;
            }

            if (loc > 0) {
                while (loc * 2 <= 100000) {
                    loc *= 2;
                    if (!visited[loc]) {
                        visited[loc] = true;
                        queue.offer(new Pos(loc, pos.curTime));
                    }
                }
            }

            int prev = pos.curLoc - 1;
            if (prev >= 0 && !visited[prev]) {
                visited[prev] = true;
                queue.offer(new Pos(prev, pos.curTime + 1));
            }
            int next = pos.curLoc + 1;
            if (next <= 100000 && !visited[next]) {
                visited[next] = true;
                queue.offer(new Pos(next, pos.curTime + 1));
            }

//            System.out.println("queue : " + queue);

        }


    }
}
