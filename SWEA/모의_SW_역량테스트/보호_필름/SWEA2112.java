package SWEA.모의_SW_역량테스트.보호_필름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2112 {
    static StringBuilder sb;
    static int D,W,K;
    static int result;
    static int[][] map;
    static int[][] tempMap;
    static int[] permResult;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++) {
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            permResult = new int[D];
            for (int i=0; i<D; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            dfs(0);

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int n) {
        if (n == D) {
            tempMap = new int[D][W];
            for (int i=0; i<D; i++) {
                if(permResult[i] == 0) {
                    tempMap[i] = map[i].clone();
                } else if (permResult[i] == 1) {
                    Arrays.fill(tempMap[i],0);
                } else {
                    Arrays.fill(tempMap[i],1);
                }
            }

            if (checkMap()) {
                result = Math.min(result,medicineCnt());
            }

            return;
        }

        for (int i=0; i<3; i++) {
            permResult[n] = i;
            if (medicineCnt() < result) {
                dfs(n + 1);
            }
            permResult[n] = 0;
        }

    }


//    private static boolean checkMap() {
//        String zeroCmpr = "";
//        String oneCmpr = "";
//        for (int i=0; i<K; i++) {
//            zeroCmpr += "0";
//            oneCmpr += "1";
//        }
//
//        for (int j=0; j<W; j++) { // 각 열에 대해
//            String columnStr = "";
//            for (int i=0; i<D; i++) {
//                columnStr += tempMap[i][j];
//            }
//
//            if (!columnStr.contains(zeroCmpr) && !columnStr.contains(oneCmpr)) return false;
//        }
//
//        return true;
//    }

    private static int medicineCnt() {
        int cnt = 0;
        for (int i=0; i<D; i++) {
            if (permResult[i] > 0) cnt++;
        }

        return cnt;
    }

    private static boolean checkMap() {
        for (int j=0; j<W; j++) {
            int count = 1;
            int prev = tempMap[0][j];
            int max = 0;
            for (int i=1; i<D; i++) {
                if (tempMap[i][j] == prev) {
                    count++;
                } else {
                    count = 1;
                }
                prev = tempMap[i][j];
                max = Math.max(max,count);
            }
            if (max < K) return false;
        }

        return true;
    }


}
