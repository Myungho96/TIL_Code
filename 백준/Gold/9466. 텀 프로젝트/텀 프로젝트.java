import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result;
    static boolean[] visited, done;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            Result = n;
            int[] arr = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];
            String[] temp = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(temp[i - 1]);
                if (arr[i] == i) {
                    done[i] = true;
                    Result--;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!done[i]) {
                    dfs(i, arr);
                }
            }
            bw.write(Result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, int[] arr) {
        if (done[idx])//이미 완성
            return;
        if (visited[idx]) { // 방문을 했었다 == 사이클 구성원이다
            done[idx] = true;
            Result--;
        }
        visited[idx] = true;
        dfs(arr[idx], arr);
        done[idx] = true;
        visited[idx] = false;
    }

}
