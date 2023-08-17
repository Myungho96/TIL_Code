import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 987654321;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] dp = new int[n][3];
        String[] temp;
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                dp[0][j] = 987654321;
            }
            dp[0][i] = arr[0][i];
            dfs(1, n, arr, dp, i);
        }


        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int num, int n, int[][] arr, int[][] dp, int start) {
        if (num == n) {
            for (int i = 0; i < 3; i++) {
                if (i != start)
                    Result = Math.min(dp[num - 1][i], Result);
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            dp[num][i] = Math.min(dp[num - 1][(i + 1) % 3], dp[num - 1][(i + 2) % 3]) + arr[num][i];
        }
        dfs(num + 1, n, arr, dp, start);
    }
}
