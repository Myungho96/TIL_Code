import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        Node start = null;
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 2) {
                    start = new Node(i, j, 0);
                }
                if(arr[i][j] == 1){
                    arr[i][j] = -1;
                }
            }
        }

        bfs(start);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }

    private static void bfs(Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.i][start.j] = true;
        arr[start.i][start.j] = start.cnt;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = node.i + deltas[i][0];
                int cc = node.j + deltas[i][1];
                if (isIn(cr, cc) && !visited[cr][cc] && arr[cr][cc] == -1) {
                    visited[cr][cc] = true;
                    arr[cr][cc] = node.cnt + 1;
                    queue.offer(new Node(cr, cc, node.cnt + 1));
                }
            }
        }
    }

    private static boolean isIn(int cr, int cc) {
        return cr >= 0 && cc >= 0 && cr < N && cc < M;
    }

    public static class Node {
        int i, j, cnt;

        public Node(int i, int j, int cnt) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }


}