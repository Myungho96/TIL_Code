import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i + 1][j + 1] = Integer.parseInt(temp[j]);
            }
        }
        temp = br.readLine().split(" ");
        int start = Integer.parseInt(temp[0]);
        for (int i = 1; i < M; i++) {
            if (!bfs(start, Integer.parseInt(temp[i]))) {//못가는 경우
                bw.write("NO\n");
                bw.flush();
                bw.close();
                return;
            } else {//다음 목적지에 도착 성공한 경우
                start = Integer.parseInt(temp[i]);
            }
        }
        bw.write("YES\n");
        bw.flush();
        bw.close();
    }

    private static boolean bfs(int start, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == end) {
                return true;
            }
            for (int i = 0; i < N; i++) {
                if (!visited[i + 1] && arr[node][i + 1] == 1) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }
            }
        }
        return false;
    }
}