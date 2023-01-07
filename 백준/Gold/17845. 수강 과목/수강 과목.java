import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static String[] temp;
    static int[][] arr;
    static int[][] dp;
    static long result;

    public static void main(String[] args) throws IOException {
        temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[M][2];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = Integer.parseInt(temp[1]);
        }
        dp = new int[M+1][N+1];
        dp();
        bw.write(dp[M][N]+"\n");
        bw.flush();
        bw.close();

    }

    private static void dp() {
        for (int i = 1; i <= M; i++) {
            int focus = arr[i-1][0];
            int weight = arr[i-1][1];
            for (int j = 1; j <= N; j++) {
                if(weight<=j){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight]+focus);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }

        }
    }


}