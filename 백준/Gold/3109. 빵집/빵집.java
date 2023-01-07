import java.util.*;
import java.io.*;
import java.lang.*;

//21212	240
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, cnt = 0;
    static int[][] deltas = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
    static String temp[], arr[][];
    static boolean[][] visited;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new String[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if(temp[j].equals("x")){
                    visited[i][j] = true;
                }
                arr[i][j] = temp[j];
            }
        }
        for (int i = 0; i < N; i++) {
            dfs(i, 0);
        }
        bw.write(cnt+"\n");
        bw.flush();
        bw.close();
    }

    public static boolean dfs(int r, int c) {
        visited[r][c] = true;
        //기저조건
        if (c == M-1) {
            cnt++;
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int cr = r + deltas[d][0];
            int cc = c + deltas[d][1];
            if(isIn(cr,cc) && !visited[cr][cc]){
                if(dfs(cr,cc))
                    return true;
            }
        }
        return false;
    }


    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}
