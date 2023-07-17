import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        //짝수
        int n = Integer.parseInt(br.readLine());
        int[][] arr = dp(n);
        bw.write(sum(arr, n) + "\n");
        bw.flush();
        bw.close();

    }

    static int[][] dp(int n) {
        int[][] arr = new int[n][10];
        for (int i = 0; i < n; i++)
            arr[i][0] = 1;
        for (int j = 0; j < 10; j++)
            arr[0][j] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 10; j++) {
                arr[i][j] = (arr[i - 1][j] + arr[i][j - 1]) % 10007;
            }
        }
        return arr;

    }

    static int sum(int[][] arr, int n) {
        int answer = 0;
        for (int i = 0; i < 10; i++)
            answer += arr[n - 1][i] % 10007;
        return answer % 10007;
    }
}

