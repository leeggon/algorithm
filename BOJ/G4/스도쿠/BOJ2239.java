package BOJ.G4.스도쿠;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ2239 {
    static boolean found = false;
    static int [][] map;
    static ArrayList<Integer>[] row;
    static ArrayList<Integer>[] col;
    static ArrayList<Integer>[][] square;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 선언
        row = new ArrayList[9];
        col = new ArrayList[9];
        square = new ArrayList[3][3];
        for (int i=0; i<9; i++) {
            row[i] = new ArrayList<>();
            col[i] = new ArrayList<>();
        }
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                square[i][j] = new ArrayList<>();
            }
        }

        // 초기화
        map = new int[9][9];
        for (int i=0; i<9; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<9; j++) {
                if (map[i][j] != 0) {
                    row[i].add(map[i][j]);
                    col[j].add(map[i][j]);
                    square[i/3][j/3].add(map[i][j]);
                }
            }
        }

        for(int n=0; n<9*9; n++) {
            dfs(n);
        }


    }


    private static void dfs(int n) {
        if (n==9*9) {
            found = true;

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<9; i++) {
                for (int j=0; j<9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
            return;
        }

        int i = n/9;
        int j = n%9;

        if (!found) {
            if (map[i][j]==0) {
                for (int k=1; k<=9; k++) {
                    if (!row[i].contains(k) && !col[j].contains(k) && !square[i/3][j/3].contains(k)) {
                        map[i][j] = k;
                        row[i].add(k);
                        col[j].add(k);
                        square[i/3][j/3].add(k);
                        dfs(n+1);
                        map[i][j] = 0;
                        row[i].remove(row[i].indexOf(k));
                        col[j].remove(col[j].indexOf(k));
                        square[i/3][j/3].remove(square[i/3][j/3].indexOf(k));
                    }

                }

            } else {
                dfs(n+1);
            }
        }


    }


}

