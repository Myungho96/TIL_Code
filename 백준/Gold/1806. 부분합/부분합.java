import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static long S;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        S = Long.parseLong(temp[1]);
        arr = new int[N];
        result = Integer.MAX_VALUE;
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        int start = 0, end = 0;
        long sum = 0;
        while (true) {
            if (end == N && sum < S) {
                break;
            } else if (end == N && sum >= S) {
                for (int i = start; i < end; i++) {
                    if (sum >= S) {
                        result = Math.min(result, end - start);
                        sum -= arr[i];
                        start = i + 1;
                    } else {
                        break;
                    }
                }
                break;
            } else {
                if (sum < S) {
                    sum += arr[end];
                    end++;
                } else {//S이하로 만들어주기
                    for (int i = start; i < end; i++) {
                        if (sum >= S) {
                            result = Math.min(result, end - start);
                            sum -= arr[i];
                            start = i + 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }


}