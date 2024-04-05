package SWEA.모의_SW_역량테스트.활주로_건설;

import java.io.*;
import java.util.*;

public class SWEA4014 {
    static int[][] map;
    static int N,L;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc=0; tc<T; tc++) {
            result = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][];
            for (int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            calcPossibleRowPath();
            calcPossibleColPath();
            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void calcPossibleRowPath() {
        for (int i=0; i<N; i++) {
            boolean[] slopeBuilt = new boolean[N]; // 경사로 세웠는지 여부
            boolean validPath = true;
            int j=1;
            while (j<N) { // 해당 행 탐색
                if (Math.abs(map[i][j] - map[i][j-1]) > 1) { // 절대값 차이 1이상이면 걍 안됨.
                    validPath = false;
                    break; // 해당 행 탐색 즉시 종료
                }

                if (map[i][j] == map[i][j-1]) j++;
                else if (map[i][j] == map[i][j-1] + 1) { // 1 증가할 때
                    boolean validLSearch = true;
                    for (int k=1; k<=L; k++) {
                        int prevJ = j - k;
                        if (prevJ < 0) {
                            validLSearch = false;
                            break;
                        }

                        if (slopeBuilt[prevJ] || map[i][prevJ] != map[i][j]-1) {
                            validLSearch = false;
                            break;
                        }
                        slopeBuilt[prevJ] = true;
                    }

                    if (!validLSearch) {
                        validPath = false;
                        break; // 해당 행 탐색 즉시 종료
                    }
                    j++;

                } else if (map[i][j] == map[i][j-1] - 1) { // 1 감소할 때
                    slopeBuilt[j] = true;
                    boolean validLSearch = true;
                    for (int k=1; k<=L-1; k++) {
                        int nextJ = j + k;
                        if (nextJ >= N) {
                            validLSearch = false;
                            break;
                        }

                        if (map[i][nextJ] != map[i][j]) {
                            validLSearch = false;
                            break;
                        }
                        slopeBuilt[nextJ] = true;
                    }

                    if (!validLSearch) {
                        validPath = false;
                        break; // 해당 행 탐색 즉시 종료
                    }
                    j += L;
                }

            }
            if (validPath) {
                result++;
            }
        }
    }

    private static void calcPossibleColPath() {
        for (int j=0; j<N; j++) {
            boolean[] slopeBuilt = new boolean[N]; // 경사로 세웠는지 여부
            boolean validPath = true;
            int i=1;
            while (i<N) { // 해당 열 탐색
                if (Math.abs(map[i][j] - map[i-1][j]) > 1) { // 절대값 차이 1이상이면 걍 안됨.
                    validPath = false;
                    break; // 해당 열 탐색 즉시 종료
                }

                if (map[i][j] == map[i-1][j]) i++;
                else if (map[i][j] == map[i-1][j] + 1) { // 1 증가할 때
                    boolean validLSearch = true;
                    for (int k=1; k<=L; k++) {
                        int prevI = i - k;
                        if (prevI < 0) {
                            validLSearch = false;
                            break;
                        }

                        if (slopeBuilt[prevI] || map[prevI][j] != map[i][j]-1) {
                            validLSearch = false;
                            break;
                        }
                        slopeBuilt[prevI] = true;
                    }

                    if (!validLSearch) {
                        validPath = false;
                        break; // 해당 행 탐색 즉시 종료
                    }
                    i++;

                } else if (map[i][j] == map[i-1][j] - 1) { // 1 감소할 때
                    slopeBuilt[i] = true;
                    boolean validLSearch = true;
                    for (int k=1; k<=L-1; k++) {
                        int nextI = i + k;
                        if (nextI >= N) {
                            validLSearch = false;
                            break;
                        }

                        if (map[nextI][j] != map[i][j]) {
                            validLSearch = false;
                            break;
                        }
                        slopeBuilt[nextI] = true;
                    }

                    if (!validLSearch) {
                        validPath = false;
                        break; // 해당 행 탐색 즉시 종료
                    }
                    i += L;
                }

            }
            if (validPath) {
                result++;
            }
        }
    }
}
