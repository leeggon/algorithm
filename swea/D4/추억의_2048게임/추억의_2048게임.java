package swea.D4.추억의_2048게임;

import java.io.*;
import java.util.*;

public class 추억의_2048게임 {
    static String d;
    static int N;
    static int[][] map;
    static int[][] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            d = st.nextToken();

            map = new int[N][N];
            for (int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            answer = new int[N][N];
            if (d.equals("up")) {
                for (int j=0; j<N; j++) {
                    int temp = -1;
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i=0; i<N; i++) {
                        if (map[i][j] == 0) continue;

                        if (temp != -1 && temp == map[i][j]) {
                            list.add(temp * 2);
                            temp = -1;
                        } else if (temp != -1 && temp != map[i][j]){
                            list.add(temp);
                            temp = map[i][j];
                        } else { // temp == -1
                            temp = map[i][j];
                        }
                    }

                    if (temp != -1) {
                        list.add(temp);
                    }


                    for (int i=0; i<list.size(); i++) {
                        answer[i][j] = list.get(i);
                    }
                }
            } else if (d.equals("down")) {
                for (int j=0; j<N; j++) {
                    int temp = -1;
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i=N-1; i>=0; i--) {
                        if (map[i][j] == 0) continue;

                        if (temp != -1 && temp == map[i][j]) {
                            list.add(temp * 2);
                            temp = -1;
                        } else if (temp != -1 && temp != map[i][j]){
                            list.add(temp);
                            temp = map[i][j];
                        } else { // temp == -1
                            temp = map[i][j];
                        }
                    }

                    if (temp != -1) {
                        list.add(temp);
                    }


                    for (int i=0; i<list.size(); i++) {
                        answer[N-1-i][j] = list.get(i);
                    }
                }

            } else if (d.equals("left")) {

                for (int i=0; i<N; i++) {
                    int temp = -1;
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j=0; j<N; j++) {
                        if (map[i][j] == 0) continue;

                        if (temp != -1 && temp == map[i][j]) {
                            list.add(temp * 2);
                            temp = -1;
                        } else if (temp != -1 && temp != map[i][j]){
                            list.add(temp);
                            temp = map[i][j];
                        } else { // temp == -1
                            temp = map[i][j];
                        }
                    }

                    if (temp != -1) {
                        list.add(temp);
                    }


                    for (int j=0; j<list.size(); j++) {
                        answer[i][j] = list.get(j);
                    }
                }

            } else {

                for (int i=0; i<N; i++) {
                    int temp = -1;
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j=N-1; j>=0; j--) {
                        if (map[i][j] == 0) continue;

                        if (temp != -1 && temp == map[i][j]) {
                            list.add(temp * 2);
                            temp = -1;
                        } else if (temp != -1 && temp != map[i][j]){
                            list.add(temp);
                            temp = map[i][j];
                        } else { // temp == -1
                            temp = map[i][j];
                        }
                    }

                    if (temp != -1) {
                        list.add(temp);
                    }


                    for (int j=0; j<list.size(); j++) {
                        answer[i][N-1-j] = list.get(j);
                    }
                }

            }


            sb.append("#").append(tc+1).append("\n");
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    sb.append(answer[i][j]);
                    if (j < N-1) {
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }

        }

        System.out.println(sb.toString().trim());
    }
}
