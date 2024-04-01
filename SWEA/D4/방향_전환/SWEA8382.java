package SWEA.D4.방향_전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8382 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            int result = 0;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int x = Math.abs(x1-x2);
            int y = Math.abs(y1-y2);
            // x가 항상 긴 변으로
            if (x<y) {
                int temp = x;
                x = y;
                y = temp;
            }

            result += 2*y;
            if((x-y)%2 == 0) {
                result += 4*((x-y)/2);
            } else {
                result += 4*((x-y)/2)+1;
            }

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
