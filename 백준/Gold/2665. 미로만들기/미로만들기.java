import java.io.*;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static boolean[][] visited;
    static int[][] arr;
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        bw.write(bfs() + "\n");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    private static int bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited[node.x][node.y])
                continue;
            else if (node.x == N - 1 && node.y == N - 1) {
                return node.cnt;
            }
            visited[node.x][node.y] = true;
            for (int i = 0; i < 4; i++) {
                int cr = node.x + deltas[i][0];
                int cc = node.y + deltas[i][1];
                if (isin(cr, cc)) {
                    if (arr[cr][cc] == 1) {
                        queue.offer(new Node(cr, cc, node.cnt));
                    } else {
                        queue.offer(new Node(cr, cc, node.cnt + 1));
                    }
                }
            }
        }
        return 0;
    }

    private static boolean isin(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}