import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, result, cnt;
    public static List<Node>[] list;
    public static boolean[] visited;

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
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            list[Integer.parseInt(temp[0])].add(new Node(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
            list[Integer.parseInt(temp[1])].add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[2])));
        }
        bfs();
        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Node node : list[1]) {
            pq.add(new Node(node.num, node.cost));
        }
        result = 0;
        visited[1] = true;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.num == N) {
                result = node.cost;
                return;
            }
            if (visited[node.num])
                continue;
            visited[node.num] = true;
            for (Node num : list[node.num]) {
                pq.add(new Node(num.num, node.cost + num.cost));
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