import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = Integer.MAX_VALUE, s, e;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int j = 0; j < n; j++) {
            arr[j] = Integer.parseInt(temp[j]);
        }
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (Math.abs(sum) <= Result) {
                Result = Math.abs(sum);
                s = arr[start];
                e = arr[end];
            }
            if (sum > 0)
                end--;
            else
                start++;
        }

        bw.write(s + " " + e + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
