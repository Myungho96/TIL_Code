import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result;
    static boolean[][] cowMap;
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][][][] roads;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        int r = Integer.parseInt(temp[2]);
        roads = new boolean[n + 1][n + 1][n + 1][n + 1];
        cowMap = new boolean[n + 1][n + 1];

        for (int i = 0; i < r; i++) {
            temp = br.readLine().split(" ");
            roads[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])][Integer.parseInt(temp[3])] = true;
            roads[Integer.parseInt(temp[2])][Integer.parseInt(temp[3])][Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = true;
        }

        Result = k * (k - 1);
        List<Node> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            lists.add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            cowMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = true;
        }
        for (Node node : lists) {
            bfs(node, n);
        }
        bw.write(Result/2 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(Node start, int n) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        queue.offer(start);
        visited[start.r][start.c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            //소가 있는데 본인이 아니면
            if (cowMap[node.r][node.c] && !node.equals(start)){
                Result--;
            }
            //사방탐색
            for(int i=0;i<4;i++){
                int cr = node.r+Deltas[i][0];
                int cc = node.c+Deltas[i][1];
                if(cr>0 && cc>0 && cr<=n && cc<=n && !roads[node.r][node.c][cr][cc] && !visited[cr][cc]){
                    visited[cr][cc] = true;
                    queue.offer(new Node(cr,cc));
                }
            }
        }

    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Node o) {
            return this.r == o.r && this.c == o.c;
        }
    }


}


