import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static String[] temp;
    static int[][] arr, dp;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= temp.length; j++) {
                arr[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }
        dp[1][1] = arr[1][1];
        dp();
        Arrays.sort(dp[N]);
        bw.write(dp[N][N] + "\n");

        bw.flush();
        bw.close();

    }

    private static void dp() {
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + arr[i][j];
            }
        }
    }

}