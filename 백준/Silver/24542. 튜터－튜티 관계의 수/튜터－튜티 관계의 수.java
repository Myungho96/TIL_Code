import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] visited;
    public static int N, M;
    public static List<Integer>[] list;
    public static long result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        result = 1;
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        visited = new boolean[N + 1];
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result = result * bfs(i) % 1000000007;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }


    private static long bfs(int start) throws IOException {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        long cnt = 1;
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int temp : list[node]) {
                if(!visited[temp]){
                    visited[temp]=true;
                    queue.offer(temp);
                    cnt++;
                }
            }
        }


        return cnt;
    }

}