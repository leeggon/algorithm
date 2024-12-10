package programmers.algorithm.level3.여행경로;

import java.util.*;

class 여행경로 {
    static int N;
    static Ticket[] sortedTickets;
    static List<String> result;
    static boolean[] visited;

    static class Ticket implements Comparable<Ticket> {
        String dep;
        String des;

        public Ticket(String dep, String des) {
            this.dep = dep;
            this.des = des;
        }

        @Override
        public String toString() {
            return "dep : " + dep + " des : " + des;
        }

        @Override
        public int compareTo(Ticket o) {
            int depComparison = this.dep.compareTo(o.dep);
            if (depComparison != 0) {
                return depComparison;
            }

            return this.des.compareTo(o.des);
        }

    }

    public static List<String> solution(String[][] tickets) {
        N = tickets.length;
        sortedTickets = new Ticket[N];
        visited = new boolean[N];
        result = new ArrayList<>(Arrays.asList("ICN"));
        // System.out.println(result);

        for (int i=0; i<N; i++) {
            Ticket ticket = new Ticket(tickets[i][0], tickets[i][1]);
            sortedTickets[i] = ticket;
        }

        Arrays.sort(sortedTickets);
        // System.out.println(Arrays.toString(sortedTickets));

        dfs("ICN", 0);

        return result;

    }

    private static boolean dfs(String dep, int cnt) {
        if (cnt == N) return true;

        for (int i=0; i<N; i++) {
            Ticket ticket = sortedTickets[i];
            if (!visited[i] && ticket.dep.equals(dep)) {
                visited[i] = true;
                result.add(ticket.des);
                if (dfs(ticket.des, cnt + 1)) {
                    return true;
                }
                visited[i] = false;
                result.remove(result.size() - 1);
            }
        }

        return false;
    }

}
