import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, R, C, result;

    public static int[][] arr, deltas;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        String []temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        arr = new int[R][C];
        visited = new boolean[R][C];
        result = -1;
        for (int i = 0; i < R; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }

        N = Integer.parseInt(br.readLine());
        deltas = new int[N][2];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            deltas[i][0] = Integer.parseInt(temp[0]);
            deltas[i][1] = Integer.parseInt(temp[1]);
        }
        bfs();
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < C; i++) {
            if (arr[0][i] == 1) {
                queue.offer(new Node(0, i, 0));
                visited[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.r==R-1){
                result=node.cnt;
                return;
            }
            for (int i = 0; i < N; i++) {
                int cr = node.r + deltas[i][0];
                int cc = node.c + deltas[i][1];
                if(isIn(cr,cc) && !visited[cr][cc] && arr[cr][cc]==1){
                    visited[cr][cc] = true;
                    queue.offer(new Node(cr,cc,node.cnt+1));
                }
            }
        }
    }

    public static class Node {
        int r, c, cnt;

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}