import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = -1;

    public static void main(String[] args) throws IOException {
        List<Node> lists = new ArrayList<>();
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            lists.add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
        Collections.sort(lists);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int end = 0;
        int sum = 0;

        while (end < k) {
            pq.offer(lists.get(end).like);
            sum += lists.get(end).like;
            if (pq.size() > n) {
                sum -= pq.poll();
            }
            if (pq.size() == n && m <= sum) {
                Result = lists.get(end).level;
                break;
            }
            end++;
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int like, level;

        Node(int like, int level) {
            this.like = like;
            this.level = level;
        }

        @Override
        public int compareTo(Node o) {
            if (this.level == o.level)
                return Integer.compare(this.like, o.like) * -1;
            return Integer.compare(this.level, o.level);
        }

        public String toString() {
            return like + " " + level;
        }
    }
}