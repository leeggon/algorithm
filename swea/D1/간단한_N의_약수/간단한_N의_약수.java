package swea.D1.간단한_N의_약수;

import java.io.*;

public class 간단한_N의_약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i=1; i<=N; i++) {
            if (N%i ==0) {
                sb.append(i).append(" ");
            }

        }

        System.out.println(sb.toString().trim());
    }
}
