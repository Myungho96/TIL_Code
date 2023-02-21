import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M, result;
    public static int[][] arr;
    public static int[][][] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M][3];
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], 987654321);
            }
        }

        for (int i = 0; i < M; i++) {
            dp[0][i][0] = arr[0][i];
            dp[0][i][1] = arr[0][i];
            dp[0][i][2] = arr[0][i];
        }

        cal(1);
        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }

    private static void cal(int cnt) {
        if (cnt == N) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < 3; j++) {
                    result = Math.min(result, dp[N - 1][i][j]);
                }
            }
            return;
        }
        for (int i = 0; i < M; i++) {
            if (i == 0) {
                dp[cnt][i][0] = Math.min(dp[cnt - 1][i + 1][1], dp[cnt - 1][i + 1][2]) + arr[cnt][i];
                dp[cnt][i][1] = dp[cnt-1][i][0] + arr[cnt][i];
            } else if (i == M - 1) {
                dp[cnt][i][1] = dp[cnt-1][i][2]+ arr[cnt][i];
                dp[cnt][i][2] = Math.min(dp[cnt - 1][i - 1][1], dp[cnt - 1][i - 1][0]) + arr[cnt][i];
            } else {
                dp[cnt][i][0] = Math.min(dp[cnt - 1][i + 1][1], dp[cnt - 1][i + 1][2]) + arr[cnt][i];
                dp[cnt][i][1] = Math.min(dp[cnt - 1][i][0], dp[cnt - 1][i][2]) + arr[cnt][i];
                dp[cnt][i][2] = Math.min(dp[cnt - 1][i - 1][0], dp[cnt - 1][i - 1][1]) + arr[cnt][i];
            }
        }
        cal(cnt + 1);

    }

}