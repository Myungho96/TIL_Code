import java.io.*;

public class Main {
    static int N;
    static int[] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N == 1)
            bw.write("1\n");
        else {
            dp = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                dp[i] = i;
            }
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            bw.write(dp[N] + "\n");
        }
        bw.flush();
        bw.close();
    }


}