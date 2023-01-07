import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr;
    static boolean[] visited;
    static int N, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0, 0);
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    private static void dfs(int start, int index, int cnt, int sum) {
        if (cnt == N - 1 && arr[index][start] != 0) {
            min = Math.min(min, sum + arr[index][start]);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i] && arr[index][i] != 0) {
                visited[i] = true;
                dfs(start, i, cnt + 1, sum + arr[index][i]);
                visited[i] = false;

            }
        }

    }
}
