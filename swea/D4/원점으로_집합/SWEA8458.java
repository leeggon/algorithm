package swea.D4.원점으로_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            sb.append("#").append(tc+1).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] dotDists = new int[N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                dotDists[i] = calcDistance(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int oddCnt = 0;
            int evenCnt = 0;
            for (int i=0; i<N; i++) {
                if (dotDists[i]%2 ==0) {
                    evenCnt++;
                } else {
                    oddCnt++;
                }
            }
            if (evenCnt>0 && oddCnt>0) {
                sb.append(-1).append("\n");
                continue;
            }

            int x = 0;
            while(true) {
//              System.out.println(x);
//              System.out.println(Arrays.toString(dotDists));
                if(isAllZero(dotDists)) {
                    sb.append(x).append("\n");
                    break;
                }
                x++;
                for(int i=0; i<dotDists.length; i++) {
                    if((dotDists[i]-x)>=0) dotDists[i] -= x;
                    else {
                        dotDists[i] = (x-dotDists[i])%2;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static int calcDistance(int i, int j) {
        return Math.abs(i) + Math.abs(j);
    }

    private static boolean isAllZero(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            if (arr[i] != 0) return false;
        }
        return true;
    }

}