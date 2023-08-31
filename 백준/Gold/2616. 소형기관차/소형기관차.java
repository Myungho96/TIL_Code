import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][n + 1];
        int[] arr = new int[n + 1];
        String[] temp = br.readLine().split(" ");
        int limit = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(temp[i - 1]);
        }
        //브루트포스는 안된다......
        //
        for (int i = 1; i <= 3; i++) {
            for (int j = i * limit; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - limit] + arr[j] - arr[j-limit]);
            }
        }
        bw.write(dp[3][n] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}


