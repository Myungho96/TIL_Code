import java.io.*;
import java.util.Arrays;


public class Main {
    static int N;
    static long total;
    static long[][] arr;
    static long max = Long.MIN_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        total = 0;
        arr = new long[N][N];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(temp[j]);
                total += arr[i][j];
                max = Math.max(max, arr[i][j]);

            }
        }
        long left = 0;
        long right = max;
        while (left+1 < right) {
            long mid = (left + right) / 2;
            long cntPC = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mid <= arr[i][j])
                        cntPC += mid;
                    else
                        cntPC += arr[i][j];
                }
            }
            //퍼센트 계산
            if (((double) cntPC / (double) total) >= 0.5) {
                right = mid;
            } else
                left = mid;
        }
        bw.write(right + "\n");
        bw.flush();
        bw.close();
    }

}