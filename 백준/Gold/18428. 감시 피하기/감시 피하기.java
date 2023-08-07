import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        boolean[][] check = new boolean[n][n];
        String result = "NO";
        for (int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (temp[j * 2] == 'S')
                    arr[i][j] = 1;
                if (temp[j * 2] == 'T')
                    arr[i][j] = 2;
            }
        }
        //장애물을 check배열을 이용해 체크하고 bfs돌기
        loop:
        for (int i = 0; i < n * n; i++) {
            if (arr[i / n][i % n] == 0) {
                check[i / n][i % n] = true;
                for (int j = i + 1; j < n * n; j++) {
                    if (arr[j / n][j % n] == 0) {
                        check[j / n][j % n] = true;
                        for (int k = j + 1; k < n * n; k++) {
                            if (arr[k / n][k % n] == 0) {
                                check[k / n][k % n] = true;
                                if (bfs(arr, check)) {
                                    result = "YES";
                                    break loop;
                                }
                                check[k / n][k % n] = false;
                            }
                        }
                        check[j / n][j % n] = false;

                    }
                }
                check[i / n][i % n] = false;

            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean bfs(int[][] arr, boolean[][] check) {
        Queue<int[]> queue = new LinkedList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (arr[i][j] == 1)
                    queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int cr = node[0] + deltas[d][0];
                int cc = node[1] + deltas[d][1];

                while (true) {
                    if (!(cr >= 0 && cc >= 0 && cr < n && cc < n))
                        break;
                    if (!check[cr][cc] && arr[cr][cc] == 2)
                        return false;
                    else if (check[cr][cc])
                        break;
                    cr += deltas[d][0];
                    cc += deltas[d][1];
                }
            }
        }
        return true;
    }
}