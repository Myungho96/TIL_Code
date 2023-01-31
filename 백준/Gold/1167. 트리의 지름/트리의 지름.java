import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max, node;
    static List<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        String[] temp;
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            int num = Integer.parseInt(temp[0]);
            int cnt = 1;
            while (true) {
                if (Integer.parseInt(temp[cnt]) == -1) {
                    break;
                } else {
                    list[num].add(new Node(Integer.parseInt(temp[cnt]), Integer.parseInt(temp[cnt + 1])));
                    cnt += 2;
                }
            }
        }
        visited = new boolean[N + 1];
        dfs(1, 0);
        visited = new boolean[N + 1];
        dfs(node, 0);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int num, int dis) {
        if (dis > max) {
            max = dis;
            node = num;
        }
        visited[num] = true;

        for (int i = 0; i < list[num].size(); i++) {
            Node node = list[num].get(i);
            if (visited[node.num] == false) {
                dfs(node.num, node.dis + dis);
                visited[node.num] = true;
            }
        }
    }


    private static class Node {
        int num, dis;

        public Node(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }
    }

    private static class Edge {
        int node, cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}