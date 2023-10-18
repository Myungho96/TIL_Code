import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        temp = br.readLine().split(" ");
        int r = Integer.parseInt(temp[0]);
        int c = Integer.parseInt(temp[1]);
        int dir = Integer.parseInt(temp[2]);

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        while (true) {
            if (!visited[r][c] && arr[r][c] == 0) {
                visited[r][c] = true;
                result++;
            }
            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int cr = r + deltas[d][0];
                int cc = c + deltas[d][1];
                if (cr >= 0 && cc >= 0 && cr < n && cc < m && !visited[cr][cc] && arr[cr][cc] == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                dir = (3+dir)%4;
                int cr = r + deltas[dir][0];
                int cc = c + deltas[dir][1];
                if (cr >= 0 && cc >= 0 && cr < n && cc < m && !visited[cr][cc] && arr[cr][cc] == 0) {
                    r = cr;
                    c = cc;
                }

            } else {
                //후진가능?
                int back = (dir + 2) % 4;
                int cr = r + deltas[back][0];
                int cc = c + deltas[back][1];
                if (cr >= 0 && cc >= 0 && cr < n && cc < m && arr[cr][cc] == 0) {
                    r = cr;
                    c = cc;
                }else
                    break;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }


}