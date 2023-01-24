import java.io.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] dp;
    static boolean [][]list;
    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        dp = new int[N + 1];
       list = new boolean[N + 1][N + 1];
        for (int x = 0; x < M; x++) {
            temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]);
            int B = Integer.parseInt(temp[1]);
            list[A][B] = true;
        }
        solve();
        for (int i = 1; i <= N; i++) {
            bw.write(dp[i] + " ");
        }
        bw.flush();
        bw.close();
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (list[j][i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
    }
}