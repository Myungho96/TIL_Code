import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        arr = new int[N];
        int result = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int start = 0;
        int end = arr[N - 1];

        while (start <= end) {
            int mid = (start + end) / 2;
            int node = arr[0];
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                if ((arr[i] - node) >= mid) {
                    cnt++;
                    node = arr[i];
                }
            }
            if (cnt >= C) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }


}