import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int arr[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        int start = 0;
        int end = N - 1;
        arr = new int[N];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(arr);
        int Min = Integer.MAX_VALUE;
        int minNum1 = -1;
        int minNum2 = -1;
        while (start < end) {
            int cal = arr[end] + arr[start];
            if (Math.abs(cal) < Min) {
                Min = Math.abs(cal);
                minNum1 = arr[start];
                minNum2 = arr[end];
            }
            if (cal > 0)
                end--;
            else
                start++;
        }
        bw.write(minNum1 + " " + minNum2);
        bw.flush();
        bw.close();

    }
}
