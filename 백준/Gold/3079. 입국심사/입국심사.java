import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int arr[];

    public static void main(String[] args) throws Exception {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        long left = 0;
        long right = 1L * max * M;
        long mid = 0;
        long cnt = 0;
        long minResult = 0;
        while (left <= right) {
            mid = 1L * (left + right) / 2;
            cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mid / arr[i];
            }
            if (cnt < M) {
                left = mid + 1;
            } else {
                minResult = mid;
                right = mid - 1;
            }

        }
        bw.write(minResult + "\n");
        bw.flush();
        bw.close();


    }
}
