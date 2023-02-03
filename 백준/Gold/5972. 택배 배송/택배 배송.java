import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, result;
    public static List<Node>[] list;
    public static boolean[] visited;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        String[] temp;
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            list[Integer.parseInt(temp[0])].add(new Node(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
            list[Integer.parseInt(temp[1])].add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[2])));
        }
        bfs();
        bw.write(dist[N] + "\n");
        bw.flush();
        bw.close();

    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.num])
                continue;
            visited[node.num] = true;
            for (Node current : list[node.num]) {
                if (dist[current.num] > dist[node.num] + current.cost) {
                    dist[current.num] = dist[node.num] + current.cost;
                    pq.add(new Node(current.num, dist[current.num]));
                }

            }

        }
    }

    public static class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }


}