import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, R;
    static int TC;
    static String[] temp;
    static int[] arr;
    static int[] dp;
    static long result;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            result = 0;
            N = Integer.parseInt(br.readLine());
            temp = br.readLine().split(" ");
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(temp[i]);
            }
            R = Integer.parseInt(br.readLine());
            dp = new int[R + 1];
            dp();
            bw.write(dp[R] + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static void dp() {
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= R; j++) {
                dp[j] = dp[j - arr[i]] + dp[j];
            }
        }
    }
}