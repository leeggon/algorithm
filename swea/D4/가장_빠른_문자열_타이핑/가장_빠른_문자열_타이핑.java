package swea.D4.가장_빠른_문자열_타이핑;

import java.io.*;
import java.util.*;

public class 가장_빠른_문자열_타이핑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();

            String C = A.replace(B, "c");

            sb.append("#").append(tc+1).append(" ").append(C.length()).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
