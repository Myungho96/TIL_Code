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
    static int[] arr, dp;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        Arrays.fill(dp,1);
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        dp();
        Arrays.sort(dp);
        bw.write(dp[N-1]+"\n");

        bw.flush();
        bw.close();

    }

    private static void dp() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j])
                    dp[i] = Math.max(dp[i],dp[j]+1 );
            }
        }
    }

}