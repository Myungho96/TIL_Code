import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, L, R, Result = -1;
    public static int[][] Arr;
    public static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static boolean[][] visited;
    public static boolean Flag;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        L = Integer.parseInt(temp[1]);
        R = Integer.parseInt(temp[2]);
        Arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                Arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        Flag = true;
        while (Flag) {
            Flag = false;
            Result++;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j])
                        bfs(i, j);
                }
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

    public static void bfs(int r, int c) {
        Queue<Node> queue = new ArrayDeque<>();
        Queue<Node> result = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        int sum = Arr[r][c];
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = node.i + Deltas[i][0];
                int cc = node.j + Deltas[i][1];
                if (cr >= 0 && cc >= 0 && cr < N && cc < N && !visited[cr][cc] && Math.abs(Arr[node.i][node.j] - Arr[cr][cc]) >= L && Math.abs(Arr[node.i][node.j] - Arr[cr][cc]) <= R) {
                    Flag = true;
                    visited[cr][cc] = true;
                    queue.offer(new Node(cr, cc));
                    result.offer(new Node(cr,cc));
                    sum+=Arr[cr][cc];
                }
            }
        }
        if(result.size()>0){
            int avg = sum/(result.size()+1);
            Arr[r][c] = avg;
            while(!result.isEmpty()){
                Node node = result.poll();
                Arr[node.i][node.j] = avg;
            }
        }


    }

    public static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

}

