import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] Deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int Result = 987654321;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        boolean[][] visited;
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        //대륙별로 분리해주기
        int num = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    visited = new boolean[N][N];
                    mark(i, j, num++, visited, arr);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //사방에 0이 있는지 체크해서, 있으면 bfs
                if (arr[i][j] != 0) {
                    visited = new boolean[N][N];
                    bfs(i, j, visited, arr);
                }
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();


    }

    public static void mark(int r, int c, int num, boolean[][] visited, int[][] arr) {
        int N = visited.length;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c, 0));
        visited[r][c] = true;
        arr[r][c] = num;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = node.r + Deltas[i][0];
                int cc = node.c + Deltas[i][1];
                if (cr >= 0 && cc >= 0 && cr < N && cc < N && !visited[cr][cc] && arr[cr][cc] == 1) {
                    visited[cr][cc] = true;
                    arr[cr][cc] = num;
                    queue.offer(new Node(cr, cc, 0));

                }

            }
        }
    }

    public static void bfs(int r, int c, boolean[][] visited, int[][] arr) {
        int N = visited.length;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c, 0));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = node.r + Deltas[i][0];
                int cc = node.c + Deltas[i][1];
                if (cr >= 0 && cc >= 0 && cr < N && cc < N && !visited[cr][cc]) {
                    if (arr[cr][cc] == 0) {
                        visited[cr][cc] = true;
                        queue.offer(new Node(cr, cc, node.cnt + 1));
                    } else if (arr[cr][cc] != arr[r][c]) {
                        Result = Math.min(Result, node.cnt);
                        return;
                    }

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

