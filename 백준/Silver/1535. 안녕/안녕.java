import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static String[] temp;

    static int[][] dp;
    static int[] L, J;
    static long result;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        L = new int[N];
        J = new int[N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(temp[i]);
        }

        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            J[i] = Integer.parseInt(temp[i]);
        }


        dp = new int[N + 1][100];
        dp();
        bw.write(dp[N][99] + "\n");
        bw.flush();
        bw.close();

    }

    private static void dp() {
        for (int i = 1; i <= N; i++) {
            int hp = L[i - 1];
            int happy = J[i - 1];
            for (int j = 1; j < 100; j++) {
                if (hp <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - hp] + happy);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }
    }


}