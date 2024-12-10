package programmers.level2.테이블_해시_함수;

import java.util.*;

class 테이블_해시_함수 {
    static int N;

    static class Element implements Comparable<Element> {
        int[] numbers;

        public Element(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public String toString() {
            return Arrays.toString(numbers);
        }

        @Override
        public int compareTo(Element o) {
            if (this.numbers[N-1] != o.numbers[N-1]) {
                return Integer.compare(this.numbers[N-1], o.numbers[N-1]);
            }

            return Integer.compare(o.numbers[0], this.numbers[0]);
        }
    }

    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        N = col;
        Element[] elements = new Element[data.length];

        for (int i=0; i<data.length; i++) {
            Element element = new Element(data[i]);
            elements[i] = element;
        }

        Arrays.sort(elements);
        // System.out.println(Arrays.toString(elements));

        int result = 0;
        for (int i=row_begin-1; i<row_end; i++) { // i = 1, 2
            Element element = elements[i];
            int sum = 0;
            for (int j=0; j<element.numbers.length; j++) {
                sum += (element.numbers[j]) % (i + 1);
            } // 나머지들의 합 구하고            

            if (i == row_begin-1) {
                result = sum;
            } else {
                result = result ^ sum;
            }
        }

        return result;
    }
}