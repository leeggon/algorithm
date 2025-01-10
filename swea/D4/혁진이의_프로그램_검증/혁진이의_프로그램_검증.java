package swea.D4.혁진이의_프로그램_검증;

import java.io.*;
import java.util.*;

public class 혁진이의_프로그램_검증 {

    static class State {
        int r, c;     // 현재 행, 열
        int d;        // 현재 방향 (0=왼, 1=오른, 2=위, 3=아래)
        int memory;   // 메모리 값(0~15)
        State(int r, int c, int d, int memory) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.memory = memory;
        }
    }

    // 방향별 행/열 변화량 (0=왼,1=오른,2=위,3=아래)
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {-1, 1, 0, 0};

    static int T, R, C;
    static char[][] insts;
    // visited[memory][dir][row][col]
    // 메모리(0~15) x 방향(0~3) x R x C
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            insts = new char[R][C];
            for (int i = 0; i < R; i++) {
                insts[i] = br.readLine().toCharArray();
            }

            // visited[m][d][r][c] : 메모리값 m, 방향 d, 좌표 (r,c)에 방문했는지
            visited = new boolean[16][4][R][C];

            // BFS를 위한 큐
            Queue<State> q = new LinkedList<>();
            // 시작 상태: (0,0), 방향=1(오른쪽), 메모리=0
            q.offer(new State(0, 0, 1, 0));

            boolean canStop = false;  // 종료(@)까지 갈 수 있는지 여부

            // BFS 시작
            while (!q.isEmpty()) {
                State cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int d = cur.d;
                int mem = cur.memory;

                // 이미 방문한 상태라면 스킵
                if (visited[mem][d][r][c]) continue;
                // 방문 체크
                visited[mem][d][r][c] = true;

                // 현재 칸의 명령어
                char cmd = insts[r][c];

                // '@' : 프로그램 종료
                if (cmd == '@') {
                    canStop = true;
                    break;
                }

                // 다음 상태를 구하기 위한 임시 변수
                int nextD = d;
                int nextMem = mem;

                switch (cmd) {
                    case '<':
                        nextD = 0;
                        break;
                    case '>':
                        nextD = 1;
                        break;
                    case '^':
                        nextD = 2;
                        break;
                    case 'v':
                        nextD = 3;
                        break;
                    case '_':
                        // 메모리가 0이면 오른쪽, 아니면 왼쪽
                        nextD = (mem == 0 ? 1 : 0);
                        break;
                    case '|':
                        // 메모리가 0이면 아래쪽, 아니면 위쪽
                        nextD = (mem == 0 ? 3 : 2);
                        break;
                    case '?':
                        // 4개 방향 전부 enqueue
                        for (int nd = 0; nd < 4; nd++) {
                            int nr = (r + di[nd] + R) % R;
                            int nc = (c + dj[nd] + C) % C;
                            q.offer(new State(nr, nc, nd, mem));
                        }
                        // '?'는 여기서 바로 continue로 처리 끝.
                        // 아래쪽에서 단일 방향 이동을 또 처리하면 안됨
                        continue;
                    case '+':
                        nextMem = (mem + 1) % 16;
                        break;
                    case '-':
                        nextMem = (mem + 15) % 16;
                        break;
                    default:
                        // 숫자일 경우 (0~9)
                        if (Character.isDigit(cmd)) {
                            nextMem = cmd - '0';  // ASCII 코드가 아닌 실제 0~9
                        }
                        // '.' (아무 것도 안 함)인 경우도 포함해서
                        // nextD, nextMem은 그대로 둠
                        break;
                }

                // 이제 위에서 확정된 nextD, nextMem에 따라 "한 번" 이동
                int nr = (r + di[nextD] + R) % R;
                int nc = (c + dj[nextD] + C) % C;

                // 큐에 다음 상태 삽입
                q.offer(new State(nr, nc, nextD, nextMem));
            }

            sb.append("#").append(tc).append(" ")
                    .append(canStop ? "YES" : "NO").append("\n");
        }

        System.out.println(sb.toString());
    }
}