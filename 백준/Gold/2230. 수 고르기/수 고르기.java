import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int start = 0;
        int end = 0;

        while (start <= end) {//같은 수를 고를수도 있다고 함
            if(end == n)
                break;
            int absCal = Math.abs(arr[end] - arr[start]);
            if (absCal>=m){
                Result = Math.min(Result,absCal);
                start++;
                if(absCal==m){
                    break;
                }
            }else
                end++;
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}
