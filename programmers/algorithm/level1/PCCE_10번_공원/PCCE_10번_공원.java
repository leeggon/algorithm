package programmers.algorithm.level1.PCCE_10번_공원;

import java.util.*;

class PCCE_10번_공원 {
    public int solution(int[] mats, String[][] park) {

        Arrays.sort(mats);
        for (int k=mats.length-1; k>=0; k--) {
            int mat = mats[k];
            for (int i=0; i<park.length; i++) {
                for (int j=0; j<park[0].length; j++) {
                    if (park[i][j].equals("-1")) {
                        if (canPlace(park, mat, i, j)) {
                            return mat;
                        }
                    }
                }
            }


        }

        return -1;
    }

    private boolean canPlace(String[][] park, int mat, int I, int J) {
        int N = park.length;
        int M = park[0].length;

        for (int i=I; i<I+mat; i++) {
            for (int j=J; j<J+mat; j++) {
                if (!isValidCoordinate(i, j, N, M)) return false;
                if (!park[i][j].equals("-1")) return false;
            }
        }

        return true;
    }

    private boolean isValidCoordinate(int i, int j, int N, int M) {
        return 0<=i && i<N && 0<=j && j<M;
    }

}
