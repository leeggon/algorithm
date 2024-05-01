package BOJ.G3.양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        int[] chu = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int G = Integer.parseInt(br.readLine());
        int[] guseul = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> avail = new HashSet<>();
        for (int i=0; i<C; i++) {
            int toAddChu = chu[i];

            // avail Set 순회 먼저, 가능한 모든 경우의 수 temp Set에 저장.
            Set<Integer> temp = new HashSet<>();
            for (int av : avail) {
                temp.add(Math.abs(av - toAddChu));
                temp.add(av + toAddChu);
            }

            for (int t : temp) {
                avail.add(t);
            }
            avail.add(toAddChu);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<G; i++) {
            if (avail.contains(guseul[i])) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }

        System.out.println(sb.toString());
    }
}
