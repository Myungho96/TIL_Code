import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int l = Integer.parseInt(temp[2]);

        int[] arr = new int[n + 2];
        arr[0] = 0;
        arr[n + 1] = l;
        temp = br.readLine().split(" ");
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(temp[i - 1]);
        }
        Arrays.sort(arr);
        int start = 1;
        int end = l-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            for (int i = 1; i < n + 2; i++) {
                sum += (arr[i] - arr[i - 1] - 1) / mid;
            }
            if (sum > m)
                start = mid + 1;
            else
                end = mid - 1;
        }

        bw.write(start + "\n");
        bw.flush();
        bw.close();
    }

}