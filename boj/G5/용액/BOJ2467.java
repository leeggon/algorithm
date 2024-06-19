package boj.G5.용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = N-1;
        int resultLeft = 0;
        int resultRight = N-1;
        int resultDiff = Integer.MAX_VALUE;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (Math.abs(sum) < resultDiff) {
                resultDiff = Math.abs(sum);
                resultLeft = left;
                resultRight = right;
            }
            if (sum > 0) right--;
            else if (sum < 0) left++;
            else {
                System.out.println(nums[left] + " " + nums[right]);
                return;
            }
        }

        System.out.println(nums[resultLeft] + " " + nums[resultRight]);
    }
}
