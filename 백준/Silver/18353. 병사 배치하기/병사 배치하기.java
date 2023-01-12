import java.io.*;
import java.util.Arrays;


public class Main {
    static int N;
    static int[] arr, cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp;
        N = Integer.parseInt(br.readLine());
        cnt = new int[N];
        arr = new int[N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        int max=0;
        for (int i = 0; i < N; i++) {
            cnt[i]=1;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && cnt[i]<cnt[j]+1) {
                    cnt[i]=cnt[j]+1;
                }
            }
            max=Math.max(max,cnt[i]);
        }
        bw.write(N - max+"\n");
        bw.flush();
        bw.close();


    }

}