import java.io.*;
import java.lang.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static long N, MOD = 1000000007;
    public static String[] temp;
    static long[][] A = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Long.parseLong(br.readLine());
        long[][] ans = fermat(A, N - 1);
        bw.write(ans[0][0] + "\n");
        bw.flush();
        bw.close();
    }

    private static long[][] fermat(long[][] A, long x) {
        if (x <= 1) return A;
        long[][] half = fermat(A, x / 2);
        if (x % 2 == 0)
            return mul(half, half);
        else
            return mul(mul(half, half), new long[][]{{1, 1}, {1, 0}});
    }

    private static long[][] mul(long[][] half1, long[][] half2) {
        long[][] result = new long[2][2];

        result[0][0] = ((half1[0][0] * half2[0][0]) + (half1[0][1] * half2[1][0])) % MOD;
        result[0][1] = ((half1[0][0] * half2[0][1]) + (half1[0][1] * half2[1][1])) % MOD;
        result[1][0] = ((half1[1][0] * half2[0][0]) + (half1[1][1] * half2[1][0])) % MOD;
        result[1][1] = ((half1[1][0] * half2[0][1]) + (half1[1][1] * half2[1][1])) % MOD;
        return result;
    }
}