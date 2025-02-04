package programmers.algorithm.level2.문자열_압축;

class 문자열_압축 {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for (int len=s.length(); len>0; len--) {
            int tempAns = 0;

            String prev = "";
            int cnt = 0;
            String curS = "";
            for (int i=0; i < s.length(); i+= len) {
                // System.out.println("i : " + i);
                if (i + len > s.length()) {
                    curS = s.substring(i, s.length());
                } else {
                    curS = s.substring(i, i+len);
                }

                // System.out.println("curS : " + curS);
                if (curS.equals(prev)) {
                    cnt++;
                } else {
                    if (cnt > 0) {
                        int numLen = Integer.toString(cnt+1).length();
                        tempAns += numLen + len;

                        cnt = 0;
                    } else {
                        if (!prev.equals("")) {
                            tempAns += len;
                        }
                    }

                    prev = curS;
                }
            }

            if (cnt >= 1) {
                int numLen = Integer.toString(cnt+1).length();
                tempAns += numLen + len;
            } else {
                tempAns += curS.length();
            }

            // System.out.println("tempAns : " + tempAns);
            answer = Math.min(answer, tempAns);
        }

        return answer;
    }
}
