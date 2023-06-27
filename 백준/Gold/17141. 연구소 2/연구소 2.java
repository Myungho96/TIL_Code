import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, Time, ZeroCnt;
    public static int[][] Arr;
    public static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static List<Node> Virus;

    public static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        Time = 987654321;
        ZeroCnt = 0;
        Virus = new ArrayList<>();
        Arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                Arr[i][j] = Integer.parseInt(temp[j]);
                if (Arr[i][j] == 2){
                    Virus.add(new Node(i, j));
                    ZeroCnt++;
                }

                else if (Arr[i][j] == 0)
                    ZeroCnt++;
            }
        }
        //M만큼 해야하네~~~~~~~~~~~~~~~~ dfs도 하나 필요하네
        dfs(0, 0, new boolean[Virus.size()]);

        if (Time == 987654321)
            Time = -1;
        bw.write(Time + "\n");
        bw.flush();
        bw.close();


    }

    public static void dfs(int cnt, int current, boolean visited[]) {
        if (cnt == M) {
            bfs(visited);
            return;
        }
        for (int i = current; i < Virus.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(cnt + 1, i, visited);
                visited[i] = false;
            }
        }
    }

    public static void bfs(boolean visited[]) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited2 = new boolean[N][N];
        int zeroCnt = ZeroCnt;
        for (int i = 0; i < Virus.size(); i++) {
            if (visited[i]) {
                Node virus = Virus.get(i);
                queue.offer(virus);
                visited2[virus.i][virus.j] = true;
                zeroCnt--;
            }
        }
        int cnt = 0;

        while (!queue.isEmpty()) {
            if (zeroCnt <= 0) {
                Time = Math.min(Time, cnt);
                return;
            }
            int size = queue.size();
            cnt++;
            while (size-- > 0) {

                Node node = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int cr = node.i + Deltas[i][0];
                    int cc = node.j + Deltas[i][1];
                    if(cr>=0 && cc>=0 && cr<N && cc<N && !visited2[cr][cc] && Arr[cr][cc]!=1){
                        visited2[cr][cc] = true;
                        zeroCnt--;
                        queue.offer(new Node(cr,cc));
                    }
                }

            }

        }

    }
}

