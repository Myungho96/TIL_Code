import java.io.*;
import java.util.PriorityQueue;

public class Main {
    static int[][] Deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int Result = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        String[] temp;
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        bfs(n,arr);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int r, c, cnt;
        
        Node(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    private static void bfs(int n, int [][]arr) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [][]visited = new boolean[n][n];
        queue.offer(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited[node.r][node.c])
                continue;
            visited[node.r][node.c] = true;
            if (node.r == n - 1 && node.c == n - 1) {
                Result = node.cnt;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int cr = node.r + Deltas[i][0];
                int cc = node.c + Deltas[i][1];
                if (cr>=0 && cc>=0 && cr<n && cc<n) {
                    if (arr[cr][cc] == 1) {
                        queue.offer(new Node(cr, cc, node.cnt));
                    } else {
                        queue.offer(new Node(cr, cc, node.cnt + 1));
                    }
                }
            }
        }
    }

}