import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long result;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int idx = 0;
        while (true) {
            idx++;
            result = -987654321;
            String[] temp = br.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            if (n == 0)
                break;
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[][] visited;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new boolean[n][n];
                    solve(i, j, n, arr);
                }
            }


            bw.write(idx + ". " + result + "\n");

        }
        bw.flush();
        bw.close();


    }

    static void solve(int i, int j, int n, int[][] arr) {
        long sum = -987654321;
        if (j + 3 < n) {
            sum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3];
        }
        if (i + 3 < n) {
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j]);
        }
        if (i + 1 < n && j + 1 < n) {
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i][j + 1]);
        }
        if (i + 1 < n && j + 2 < n) {
            sum = Math.max(sum, arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2]);
            sum = Math.max(sum, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2]);
            sum = Math.max(sum, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1]);
        }
        if (i + 2 < n && j - 1 >= 0) {
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j - 1]);
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j - 1]);
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j - 1] + arr[i + 1][j - 1]);
        }

        if (i + 2 < n && j + 1 < n) {
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i][j + 1]);
            sum = Math.max(sum, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j + 1]);
        }

        if (i - 1 >= 0 && j + 2 < n) {
            sum = Math.max(sum, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i - 1][j]);
            sum = Math.max(sum, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i - 1][j + 1]);
        }
        result = Math.max(result, sum);

    }

}