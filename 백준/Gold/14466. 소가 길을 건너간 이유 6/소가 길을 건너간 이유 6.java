import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K, R;
    static int[][] Arr;
    static boolean[][][][] road;
    static int [][]deltas = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Arr = new int[N + 1][N + 1];
        road = new boolean[N + 1][N + 1][N + 1][N + 1];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            road[a][b][c][d] = true;
            road[c][d][a][b] = true;
        }
        List<Node> cows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cows.add(new Node(a, b));
            Arr[a][b] = 1;
        }
        int cnt = K*(K-1);
        for (Node node:cows) {
            cnt-=bfs(node);
        }
        bw.write(cnt/2+"\n");
        bw.flush();
        bw.close();
    }

    private static int bfs(Node node) {
        int cnt = 0;
        Queue<Node> queue = new ArrayDeque<>();
        boolean [][]visited = new boolean[N+1][N+1];
        queue.offer(new Node(node.x, node.y));
        visited[node.x][node.y] = true;
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(Arr[temp.x][temp.y] == 1 && !(temp.x==node.x && temp.y == node.y))
                cnt++;
            for (int i = 0; i < 4; i++) {
                int cr = temp.x + deltas[i][0];
                int cc = temp.y + deltas[i][1];
                if(isIn(cr,cc) && !visited[cr][cc])
                    if(!road[temp.x][temp.y][cr][cc] && !road[cr][cc][temp.x][temp.y]){
                        visited[cr][cc] = true;
                        queue.offer(new Node(cr,cc));
                    }
            }
        }

        return cnt;
    }
    private static boolean isIn(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }
    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}