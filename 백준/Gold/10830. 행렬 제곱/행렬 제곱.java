import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static long B;
    public static int[][] Arr;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        Arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = cal(Arr, B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }


    public static int[][] cal(int[][] A, long exp) {

        // 지수가 1일 때
        if (exp == 1L) {
            return A;
        }
        int[][] ret = cal(A, exp / 2);
        ret = multiply(ret, ret);

        // 홀수인 경우
        if (exp % 2 == 1L) {
            ret = multiply(ret, Arr);
        }

        return ret;
    }


    // o1과 o2 행렬을 곱해주는 메소드
    public static int[][] multiply(int[][] o1, int[][] o2) {

        int[][] ret = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= 1000;
                }
            }
        }
        return ret;
    }
}