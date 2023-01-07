import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] arr;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

        }
        st = new StringTokenizer(br.readLine());
        dfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), arr[0], 0);
        bw.write(max + "\n");
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int a, int b, int c, int d, int sum, int index) {
        if (a == 0 && b == 0 && c == 0 && d == 0) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        if (a != 0) {
            dfs(a - 1, b, c, d, sum  + arr[index + 1], index + 1);
        }
        if (b != 0) {
            dfs(a, b - 1, c, d, sum - arr[index + 1], index + 1);
        }
        if (c != 0) {
            dfs(a, b, c - 1, d, sum * arr[index + 1], index + 1);
        }
        if (d != 0) {
            dfs(a, b, c, d - 1, sum / arr[index + 1], index + 1);
        }
    }


}