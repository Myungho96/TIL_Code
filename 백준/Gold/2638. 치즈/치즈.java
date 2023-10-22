import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int result = 0, oneCnt;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        oneCnt = 0;

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 1)
                    oneCnt++;
            }
        }

        bfs(n, m, arr);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static void bfs(int n, int m, int[][] arr) {
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 0;
        while (oneCnt > cnt) {
            queue.offer(new int[]{0, 0});
            boolean[][] visited = new boolean[n][m];
            result++;
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                if (oneCnt == cnt)
                    break;
                for (int d = 0; d < 4; d++) {
                    int cr = node[0] + deltas[d][0];
                    int cc = node[1] + deltas[d][1];
                    if (cr >= 0 && cc >= 0 && cr < n && cc < m)
                        if (!visited[cr][cc] && arr[cr][cc] == 0) {
                            visited[cr][cc] = true;
                            queue.offer(new int[]{cr, cc});
                        } else if (!visited[cr][cc] && arr[cr][cc] == 1) {
                            //첫방문, 재방문 기다리기
                            visited[cr][cc] = true;
                        } else if (visited[cr][cc] && arr[cr][cc] == 1) {
                            //0으로 바꾸고 cnt 증가
                            arr[cr][cc] = 0;
                            cnt++;
                        }
                }
            }
        }

    }
}