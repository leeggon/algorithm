package BOJ.G3.IQ_Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1111 {
    static int a,b;
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (N == 1) {
            System.out.println("A");
            return;
        }
        if (N == 2) {
            if (arr[0] == arr[1]) {
                System.out.println(arr[0]);
                return;
            }

            System.out.println("A");
            return;
        }

        
        // N >= 3인 상황
        if (arr[0] == arr[1]) { // 0으로 나누기 방지
            a = 1;
            b = 0;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - a * arr[0];
        }

        if (!validCheck()) {
            System.out.println("B");
            return;
        }

        System.out.println(a * arr[N-1] + b);

    }

    private static boolean validCheck() {
        for (int i=0; i<N-1; i++) {
            if (arr[i+1] != a * arr[i] + b) return false;
        }
        return true;
    }
}
