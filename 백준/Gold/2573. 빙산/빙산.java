import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int cnt;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");

        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int zeroCnt = 0;
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 0)
                    zeroCnt++;
            }
        }
        int time = 0;
        cnt = n * m - zeroCnt;
        while (!check()) {
//            System.out.println(check());
            time++;
            bfs(n, m);
            if (cnt <= 0) {
                time = 0;
                break;
            }

        }

        bw.write(time + "\n");
        bw.flush();
        bw.close();
    }

    static void bfs(int n, int m) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    queue.offer(new Node(i,j));
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            //사방탐색
            for (int d = 0; d < 4; d++) {
                int cr = node.r + Deltas[d][0];
                int cc = node.c + Deltas[d][1];
                if (cr >= 0 && cc >= 0 && cr < n && cc < m && !visited[cr][cc]) {
                    if(arr[cr][cc] == 0 && arr[node.r][node.c] > 0) {
                        if(--arr[node.r][node.c]==0)
                            cnt--;
                    }
//
                }
            }
        }
    }

    static boolean check() {
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (!visited[i][j] && arr[i][j] != 0) {
                    visited[i][j] = true;
                    cnt++;
                    dfs(i, j, visited);
                }
            }
        }
        return cnt >= 2 ? true : false;
    }

    static void dfs(int i, int j, boolean[][] visited) {
        for (int d = 0; d < 4; d++) {
            int cr = i + Deltas[d][0];
            int cc = j + Deltas[d][1];
            if (cr >= 0 && cc >= 0 && cr < arr.length && cc < arr[0].length && !visited[cr][cc] && arr[cr][cc] != 0) {
                visited[cr][cc] = true;
                dfs(cr, cc, visited);

            }
        }
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}


