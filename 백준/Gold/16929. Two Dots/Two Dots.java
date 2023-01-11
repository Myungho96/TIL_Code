import java.io.*;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static boolean result;
    static boolean[][] visited;
    static char[][] arr;
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp;
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = temp[j].charAt(0);
            }
        }

        cycle:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, arr[i][j], new int[N][M]);
                    if (result) {
                        bw.write("Yes\n");
                        break cycle;
                    }
                }
            }
        }
        if (!result) {
            bw.write("No\n");
        }
        bw.flush();
        bw.close();
    }

    private static int dfs(int r, int c, char alphabet, int[][] cntArr) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int cr = r + deltas[i][0];
            int cc = c + deltas[i][1];
            if (isin(cr, cc)) {
                if (visited[cr][cc] && cntArr[cr][cc] - cntArr[r][c] >= 3) {
                    result = true;
                    return 1;
                } else if (!visited[cr][cc] && arr[cr][cc] == alphabet) {
                    cntArr[cr][cc] = cntArr[r][c] + 1;
                    if(dfs(cr, cc, alphabet, cntArr) == 1) return 1;
                }
            }
        }
        return 0;
    }

    private static boolean isin(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}