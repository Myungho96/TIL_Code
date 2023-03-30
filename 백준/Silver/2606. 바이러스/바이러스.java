import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M,Cnt;
    static int[][] Arr;
    static boolean[] visited;
    static List<Integer> []Lists;
    public static void main(String[] args) throws IOException {
        Cnt=0;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        Lists = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            Lists[i] = new ArrayList<>();
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Lists[a].add(b);
            Lists[b].add(a);
        }
        bfs(1);
        bw.write(Cnt+"\n");
        bw.flush();
        bw.close();

    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[1] = true;
        while (!queue.isEmpty()){
            int node = queue.poll();
            for (int i:Lists[node]) {
                if(!visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                    Cnt++;
                }
            }
        }

    }
}