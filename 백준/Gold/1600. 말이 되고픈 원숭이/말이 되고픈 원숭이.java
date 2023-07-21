import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 987654321;
    static int[][] Deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] Horse = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[][] arr = new int[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])];
        for (int i = 0; i < arr.length; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        boolean[][][] visited = new boolean[arr.length][arr[0].length][k+1];
        bfs(k, arr, visited);
        bw.write((Result == 987654321 ? -1 : Result) + "\n");
        bw.flush();
        bw.close();
    }

    static void bfs(int k, int[][] arr, boolean[][][] visited) {
        int n = arr.length;
        int m = arr[0].length;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.r == n - 1 && node.c == m - 1){
                Result = Math.min(Result, node.cnt);
                return;
            }
            //k가 기준 이하면 대각선 방향 뛰어서 큐에 추가
            if (node.k < k) {
                for (int h = 0; h < 8; h++) {
                    int cr = node.r + Horse[h][0];
                    int cc = node.c + Horse[h][1];
                    if (cr >= 0 && cc >= 0 && cr < n && cc < m && arr[cr][cc] != 1 && !visited[cr][cc][node.k + 1]) {
                        visited[cr][cc][node.k + 1] = true;
                        queue.offer(new Node(cr, cc, node.k + 1, node.cnt + 1));
                    }
                }
            }
            //사방탐색은 정상적으로 수행.
            for (int d = 0; d < 4; d++) {
                int cr = node.r + Deltas[d][0];
                int cc = node.c + Deltas[d][1];
                if (cr >= 0 && cc >= 0 && cr < n && cc < m && arr[cr][cc] != 1 && !visited[cr][cc][node.k]) {
                    visited[cr][cc][node.k] = true;
                    queue.offer(new Node(cr, cc, node.k, node.cnt + 1));
                }
            }
        }
    }

    static class Node {
        int r, c, k, cnt;

        public Node(int r, int c, int k, int cnt) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
        }
    }
}