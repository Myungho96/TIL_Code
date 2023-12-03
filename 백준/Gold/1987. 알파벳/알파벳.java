import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, max = 1;
    static int[][] deltas = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringTokenizer st;
    static boolean[] visited;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[26];
        String temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = temp.charAt(j) - 'A';
            }
        }
        dfs(0, 0, 0);


        bw.write(max + "\n");
        bw.flush();
        bw.close();

    }

    public static void dfs(int x, int y, int cnt) {
        if (visited[arr[x][y]]) {
            max = Integer.max(max, cnt);
            return;
        }
        visited[arr[x][y]] = true;
        for (int d = 0; d < 4; d++) {
            int lr = x + deltas[d][0];
            int lc = y + deltas[d][1];

            if(isIn(lr,lc)){
                dfs(lr,lc,cnt+1);
            }
        }
        visited[arr[x][y]] = false;

    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }


}