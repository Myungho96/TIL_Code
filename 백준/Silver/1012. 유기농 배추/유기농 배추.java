import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, T, K, Cnt;
    static List<Node> Lists;
    static int[][] Arr;
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            Cnt=0;
            Lists = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Arr = new int[N][M];
            visited = new boolean[N][M];
            K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                Arr[a][b] = 1;
                Lists.add(new Node(a, b));
            }
            for (Node node : Lists) {
                if (!visited[node.r][node.c]) {
                    bfs(node);
                    Cnt++;
                }
            }
            bw.write(Cnt+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void bfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(node.r, node.c));
        visited[node.r][node.c] = true;
        Arr[node.r][node.c] = 0;
        while (!queue.isEmpty()) {
            Node newNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int cr = newNode.r + deltas[i][0];
                int cc = newNode.c + deltas[i][1];
                if(isIn(cr,cc) && !visited[cr][cc] && Arr[cr][cc]==1){
                    visited[cr][cc] = true;
                    Arr[cr][cc] = 0;
                    queue.offer(new Node(cr,cc));
                }
            }
        }

    }

    private static boolean isIn(int cr, int cc) {
        return cr>=0 && cc>=0 && cr<N && cc<M;
    }

    public static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}