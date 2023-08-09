import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        Result = (int) Math.pow(2,n * m);
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        dfs(0, visited);
        visited[0][0] = false;
        dfs(0, visited);

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int cnt, boolean[][] visited) {
        int n = visited.length;
        int m = visited[0].length;
        int r = (cnt + 1) / m;
        int c = (cnt + 1) % m;
        if (r == n) {//2*2 있는지 체크
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < m-1; j++) {
                    if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
                        Result--;
                        return;
                    }
                }
            }
            return;
        }
        visited[r][c] = true;
        dfs(cnt + 1, visited);
        visited[r][c] = false;
        dfs(cnt + 1, visited);


    }

}