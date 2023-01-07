import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K, D, Coupon, total, start, end, max;
    static int[] type;
    static int[] dishes;
    static String[] temp;

    public static void main(String[] args) throws IOException {
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        D = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);
        Coupon = Integer.parseInt(temp[3]);
        total = 0;
        max=0;
        dishes = new int[N];
        type = new int[D + 1];
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
        start = 0;
        end = K;
        for (int i = 0; i < K; i++) {
            if (type[dishes[i]] == 0){
                total++;
            }

            type[dishes[i]]++;
        }
        if (type[Coupon] == 0)
            total++;
        type[Coupon]++;
        while (true) {
            if (start == N - 1) {
                bw.write(max + "\n");
                bw.flush();
                bw.close();
                return;
            }
            if (type[dishes[start]] - 1 == 0)
                total--;
            type[dishes[start]]--;
            if (type[dishes[end]] == 0)
                total++;
            type[dishes[end]]++;
            start++;
            end++;
            max = Math.max(max,total);
            if (end == N) {
                end = 0;
            }
        }
    }
}