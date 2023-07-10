import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int Result = 0, Max = 0;
    public static int[][] Deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int[][] Deltas2 = {{-1, 1}, {-1, -1}, {-1, -1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                Max = Math.max(Max, arr[i][j]);
            }
        }
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(0, 0, i, j, visited, arr);
                visited[i][j] = false;
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

    public static void dfs(int cnt, int sum, int r, int c, boolean[][] visited, int[][] arr) {
        if (Result > sum + (4 - cnt) * Max)
            return;
        int N = visited.length;
        int M = visited[0].length;
        if (cnt == 4) {
            Result = Math.max(Result, sum);
            return;
        } else if (cnt == 3) {
            //ㅗ자 만들 수 있는지 체크
            if (r - 2 >= 0 && visited[r - 1][c] && visited[r - 2][c]) {
                for (int i = 0; i < 2; i++) {
                    int cr = r + Deltas2[i][0];
                    int cc = c + Deltas2[i][1];
                    if (cr >= 0 && cc >= 0 && cr < N && cc < M && !visited[cr][cc] && visited[cr][cc - Deltas2[i][1]]) {
                        visited[cr][cc] = true;
                        dfs(cnt + 1, sum + arr[cr][cc], cr, cc, visited, arr);
                        visited[cr][cc] = false;
                    }
                }

            } else if (c - 2 >= 0 && visited[r][c - 1] && visited[r][c - 2]) {
                for (int i = 2; i < 4; i++) {
                    int cr = r + Deltas2[i][0];
                    int cc = c + Deltas2[i][1];
                    if (cr >= 0 && cc >= 0 && cr < N && cc < M && !visited[cr][cc] && visited[cr - Deltas2[i][0]][cc]) {
                        visited[cr][cc] = true;
                        dfs(cnt + 1, sum + arr[cr][cc], cr, cc, visited, arr);
                        visited[cr][cc] = false;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int cr = r + Deltas[i][0];
            int cc = c + Deltas[i][1];
            if (cr >= 0 && cc >= 0 && cr < N && cc < M && !visited[cr][cc]) {
                visited[cr][cc] = true;
                dfs(cnt + 1, sum + arr[cr][cc], cr, cc, visited, arr);
                visited[cr][cc] = false;
            }
        }
    }
}

