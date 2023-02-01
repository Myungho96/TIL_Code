import java.io.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int[] num = new int[122];
    public static int[] sum = new int[122];
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N == 1){
            bw.write(1 + "\n");
            bw.flush();
            br.close();
            return;
        }
        num[1] = 1;
        sum[1] = 1;
        for (int i = 2; i < 122; i++) {
            num[i] = num[i - 1] + i;
            sum[i] = num[i] + sum[i - 1];
        }
        dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < 122; j++) {
                if (sum[j] > i) break;
                dp[i] = Math.min(dp[i], dp[i - sum[j]] + 1);
            }
        }
        bw.write(dp[N]+"\n");
        bw.flush();
        br.close();
    }
}