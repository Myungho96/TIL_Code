import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static double Result = 0;

    public static void main(String[] args) throws IOException {
        String[] temp;
        int v = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[v + 1];
        double[][] arr = new double[v + 1][2];
        List<Node>[] lists = new List[v + 1];
        for (int i = 0; i <= v; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 1; i <= v; i++) {
            temp = br.readLine().split(" ");
            double a = Double.parseDouble(temp[0]);
            double b = Double.parseDouble(temp[1]);
            arr[i][0] = a;
            arr[i][1] = b;

        }


        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                double range = Math.sqrt(Math.pow(arr[j][0]-arr[i][0],2) + Math.pow(arr[j][1]-arr[i][1],2));
                lists[i].add(new Node(j, range));
                lists[j].add(new Node(i, range));
            }


        }

        prim(lists, visited);
        bw.write(Math.round(Result * 100) / 100.0 + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static void prim(List<Node>[] lists, boolean[] visited) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Node node : lists[1]) {
            pq.offer(new Node(node.num, node.cnt));
        }
        visited[1] = true;
        double sum = 0;
        int cnt = 1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.num])
                continue;
            visited[node.num] = true;
            sum += node.cnt;
            if (++cnt == visited.length - 1) {
                Result = sum;
                return;
            }
            for (Node temp : lists[node.num]) {
                pq.offer(new Node(temp.num, temp.cnt));
            }
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        double cnt;

        Node(int num, double cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cnt, o.cnt);
        }

    }


}