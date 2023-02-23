import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N;
    public static double M, avg;
    public static double[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new double[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        avg = 0;
        M = arr[N - 1];
        for (int i = 0; i < N; i++) {
            arr[i] = (arr[i]/ M* 100 ) ;
            avg += arr[i];
        }
        bw.write(avg / N + "\n");
        bw.flush();
        bw.close();

    }


}