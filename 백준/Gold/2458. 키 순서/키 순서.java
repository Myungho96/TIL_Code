import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, ans;
    static String[] temp;
    static boolean[] visited;
    static int[][] heights;
    static int higherStudent, lowerStudent;

    public static void main(String[] args) throws IOException {
        ans = 0;
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        heights = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            heights[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 1;
        }
        for (int i = 0; i < N; i++) {
            higherBFS(i + 1);
            lowerBFS(i + 1);
            if (higherStudent + lowerStudent == N - 1)
                ans++;
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    private static void lowerBFS(int start) {
        visited = new boolean[N + 1];
        lowerStudent = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (heights[i][node] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    lowerStudent++;
                }
            }
        }
    }

    private static void higherBFS(int start) {
        visited = new boolean[N + 1];
        higherStudent = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (heights[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    higherStudent++;
                }
            }
        }

    }


    public static class Height {
        int low, high;

        public Height(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
}