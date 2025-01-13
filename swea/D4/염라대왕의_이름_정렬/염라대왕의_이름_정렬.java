package swea.D4.염라대왕의_이름_정렬;

import java.io.*;
import java.util.*;

public class 염라대왕의_이름_정렬 {
    static int T, N;
    static String[] strings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());

            strings = new String[N];
            for (int i=0; i<N; i++) {
                strings[i] = br.readLine().trim();
            }

            Set<String> set = new HashSet<>();
            for (int i=0; i<N; i++) {
                set.add(strings[i]);
            }

            List<String> strList = new ArrayList<>();
            for (String s : set) {
                strList.add(s);
            }

//            System.out.println(strList);

            // 정렬 (1순위: 길이, 2순위: 알파벳순)
            Collections.sort(strList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    int lengthCompare = Integer.compare(s1.length(), s2.length());
                    if (lengthCompare != 0) return lengthCompare;

                    return s1.compareTo(s2);
                }
            });

            sb.append("#").append(tc+1).append("\n");
            for (int i=0; i<strList.size(); i++) {
                sb.append(strList.get(i)).append("\n");
            }
        }

        System.out.println(sb.toString().trim());
    }
}
