import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int TC, N;
    static String[] temp;
    static int[][] arr, dp;


    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            for (int i = 0; i < 2; i++) {
                temp = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(temp[j]);
                }
            }
            if (N == 1) {
                int max = Math.max(arr[0][0], arr[1][0]);
                bw.write(max + "\n");
            } else {
                dp = new int[2][N];
                dp[0][0] = arr[0][0];
                dp[1][0] = arr[1][0];
                dp();
                int max = Math.max(dp[0][N-1], dp[1][N-1]);
                bw.write(max + "\n");
            }

        }
        bw.flush();
        bw.close();

    }

    private static void dp() {
        for (int i = 1; i < N; i++) {
            dp[0][i] = Math.max(dp[0][i-1],dp[1][i-1]+arr[0][i]);
            dp[1][i] = Math.max(dp[1][i-1],dp[0][i-1]+arr[1][i]);
        }
    }

}