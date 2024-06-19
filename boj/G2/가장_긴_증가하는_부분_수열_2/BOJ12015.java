package boj.G2.가장_긴_증가하는_부분_수열_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        List<Integer> list = new ArrayList<>();
        list.add(0);

        for(int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int value = nums[i] = Integer.parseInt(st.nextToken());
            if (value > list.get(list.size() - 1)) list.add(value);
            else{
                int l = 0;
                int r = list.size() - 1;

                while(l < r){
                    int mid = (l + r) / 2;
                    if(list.get(mid) >= value){
                        r = mid;
                    }else{
                        l = mid + 1;
                    }
                }
                list.set(r, value);
            }
        }
        System.out.println(list.size() - 1);
    }
}
