package swea.D4.정식이의_은행업무;

import java.io.*;
import java.util.*;

public class 정식이의_은행업무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            int answer = -1;
            String binary = br.readLine();
            String tri = br.readLine();

            Set<Integer> set = new HashSet<>();

            int b = 0;
            for (int i=0; i<binary.length(); i++) {
                b += (int) Math.pow(2, binary.length() - 1 - i) * (binary.charAt(i) - '0');
            }

            for (int i=0; i<binary.length(); i++) {
                for (int change=0; change<=1; change++) {
                    if (binary.charAt(i) - '0' == change) continue;

                    int temp = b;
                    temp += (int) Math.pow(2, binary.length() - 1 - i) * (change - (binary.charAt(i) - '0'));
                    set.add(temp);
                }
            }

            int t = 0;
            for (int i=0; i<tri.length(); i++) {
                t += (int) Math.pow(3, tri.length() - 1 - i) * (tri.charAt(i) - '0');
            }

            outer : for (int i=0; i<tri.length(); i++) {
                for (int change=0; change<=2; change++) {
                    if (tri.charAt(i) - '0' == change) continue;

                    int temp = t;
                    temp += (int) Math.pow(3, tri.length() - 1 - i) * (change - (tri.charAt(i) - '0'));
                    if (set.contains(temp)) {
                        answer = temp;
                        break outer;
                    }
                }
            }

            sb.append("#").append(tc+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
