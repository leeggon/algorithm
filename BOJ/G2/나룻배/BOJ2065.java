package BOJ.G2.나룻배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2065 {
    static int M,t,N;
    static int[] result;
    static Customer[] customers;
    static int shipDir = 0; // 0:오른쪽으로 1:왼쪽으로
    static int shipLoc = 0; // 0 ~ t
    static int[] custPerTime;

    static class Customer implements Comparable<Customer> {
        int initIndex;
        int arriveTime;
        String arrivePlace;

        public Customer(int initIndex, int arriveTime, String arrivePlace) {
            this.initIndex = initIndex;
            this.arriveTime = arriveTime;
            this.arrivePlace = arrivePlace;
        }

        @Override
        public int compareTo(Customer o) {
            return Integer.compare(this.arriveTime, o.arriveTime);
        }

        @Override
        public String toString() {
            return "Customer [initIndex=" + initIndex + ", arriveTime=" + arriveTime + ", arrivePlace=" + arrivePlace
                    + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        result = new int[N];
        customers = new Customer[N];
        custPerTime = new int[100020];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int arriveTime = Integer.parseInt(st.nextToken());
            String arrivePlace = st.nextToken();
            customers[i] = new Customer(i,arriveTime,arrivePlace);
            custPerTime[arriveTime]++;
        }
        Arrays.sort(customers);

//		System.out.println(Arrays.toString(customers));
//		System.out.println(Arrays.toString(custPerTime));

        Queue<Customer> left = new ArrayDeque<>();
        Queue<Customer> right = new ArrayDeque<>();
        Queue<Customer> ship = new ArrayDeque<>();

        int time = 0;
        int index = 0;
        int byeCnt = 0;
        while(true) {

            // 다 내려드렸으면 break하라
            if (byeCnt == N) break;

            // 사람 넣기
            if (time <= 100000) {
                for (int i=0; i<custPerTime[time]; i++) {
                    Customer customer = customers[index++];
                    if (customer.arrivePlace.equals("left")) {
                        left.offer(customer);
                    } else {
                        right.offer(customer);
                    }
                }
            }
//			System.out.println("time: " + time);
//			System.out.println("left: " + left);
//			System.out.println("right: " + right);
//			System.out.println("ship: " + ship);
//			System.out.println("shipLoc : " + shipLoc);
//			System.out.println("shipDir : " + shipDir);
//			System.out.println("=====");

            if (shipLoc ==0) { // 왼쪽 끝 배
                // 내려
                int r = ship.size();
                for (int i=0; i<r; i++) {
                    Customer byeCustomer = ship.poll();
                    result[byeCustomer.initIndex] = time;
                    byeCnt++;
                }

                if (!left.isEmpty()) { // 왼쪽 큐에 사람 있으면
                    // 태우고
                    int x = Math.min(left.size(), M);
                    for (int i=0; i<x; i++) {
                        ship.offer(left.poll());
                    }

                    // 무조건 배 이동
                    shipDir = 0;
                    shipLoc++;

                } else { // 왼쪽 큐에 사람 없으면
                    if (!right.isEmpty()) { // 오른쪽에 사람잇으면
                        // 배 이동
                        shipDir = 0;
                        shipLoc++;
                    }
                }


            } else if (shipLoc == t) { // 오른쪽 끝 배
                int r = ship.size();
                for (int i=0; i<r; i++) {
                    Customer byeCustomer = ship.poll();
                    result[byeCustomer.initIndex] = time;
                    byeCnt++;
                }

                if (!right.isEmpty()) { // 왼쪽 큐에 사람 있으면
                    // 태우고
                    int x = Math.min(right.size(), M);
                    for (int i=0; i<x; i++) {
                        ship.offer(right.poll());
                    }

                    // 무조건 배 이동
                    shipDir = 1;
                    shipLoc--;

                } else { // 왼쪽 큐에 사람 없으면
                    if (!left.isEmpty()) { // 오른쪽에 사람잇으면
                        // 배 이동
                        shipDir = 1;
                        shipLoc--;
                    }
                }

            } else { // 이동중

                if (shipDir == 0) {
                    shipLoc++;
                } else {
                    shipLoc--;
                }

            }

            time++;
        }

//		System.out.println(Arrays.toString(result));
        for (int res: result) {
            sb.append(res).append("\n");
        }
        System.out.println(sb.toString());


    }
}
