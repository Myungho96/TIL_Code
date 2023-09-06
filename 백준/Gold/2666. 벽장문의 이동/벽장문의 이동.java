import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;

    public static void main(String args[]) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");

        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        bw.write(solve(0, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), arr) + "\n");
        bw.flush();
        bw.close();
    }

    static int solve(int cnt, int a, int b, int[] arr) {

        if (cnt == arr.length) return 0;

        int tmp1 = Math.abs(a - arr[cnt]);
        int tmp2 = Math.abs(b - arr[cnt]);

        return Math.min(tmp1 + solve(cnt + 1, b, arr[cnt], arr),
                tmp2 + solve(cnt + 1, a, arr[cnt], arr));

    }


}