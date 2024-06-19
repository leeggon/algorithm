package boj.G4.마법사_상어와_파이어볼;

import java.io.*;
import java.util.*;

public class BOJ20056 {
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static ArrayList<Fireball>[][] map;
    static ArrayList<Fireball> fireballs;
    static int N;
    static class Fireball {
        int r;
        int c;
        int m;
        int s;
        int d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        fireballs = new ArrayList<>();

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r,c,m,s,d));
        }

        for (int k=0; k<K; k++) {
            placeFireballs();
            mergeAndDivide();
            updateFireballsList();
        }

        getMResult();
    }


    // fireballs 리스트의 현재 fireball들을 이동 고려하여 map에 위치시킴
    private static void placeFireballs() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] = new ArrayList<Fireball>();
            }
        }

        for (Fireball fireball : fireballs) {
            int nr = fireball.r + di[fireball.d] * fireball.s;
            int nc = fireball.c + dj[fireball.d] * fireball.s;

            nr = calculateModular(nr);
            nc = calculateModular(nc);

            map[nr][nc].add(new Fireball(nr,nc,fireball.m,fireball.s,fireball.d));
        }
    }

    private static int calculateModular(int n) {
        if (n>=0) {
            n %= N;
        } else {
            if (n%N == 0) {
                return 0;
            }
            n = N + (n%N);
        }
        return n;
    }

    // 2개 이상인 칸에 대하여 map의 fireball 변경
    private static void mergeAndDivide() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j].size() >= 2) { // 2개 이상일때만
                    int fireballCnt = map[i][j].size();
                    int mSum = 0;
                    int sSum = 0;
                    int dOddCnt = 0;
                    int dEvenCnt = 0;

                    for(Fireball fireball : map[i][j]) {
                        mSum += fireball.m;
                        sSum += fireball.s;
                        if (fireball.d % 2 == 0) {
                            dEvenCnt++;
                        } else {
                            dOddCnt++;
                        }
                    }

                    map[i][j].clear();
                    if (mSum/5 == 0) continue; // 질량이 0인 경우

                    if ((dOddCnt>0 && dEvenCnt==0) || (dOddCnt==0 && dEvenCnt>0)) { // 방향 모두 홀수 or 모두 짝수
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,0));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,2));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,4));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,6));
                    } else {
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,1));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,3));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,5));
                        map[i][j].add(new Fireball(i,j,mSum/5,sSum/fireballCnt,7));
                    }

                }
            }
        }
    }

    // fireballs 리스트 초기화, map 순회하며 다시 fireballs 리스트 update.
    private static void updateFireballsList() {
        fireballs.clear();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j].size()>0) {
                    for (Fireball fireball : map[i][j]) {
                        fireballs.add(fireball);
                    }
                }
            }
        }
    }
    private static void getMResult() {
        int mSum = 0;
        for (Fireball fireball : fireballs) {
            mSum += fireball.m;
        }

        System.out.println(mSum);
    }
}
