import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr = new int[100][100];
    public static int N, M, T, total = 0;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            paper(N, M);

        }
        sum();
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }

    public static void paper(int N, int M) {
        for (int i = M; i < M + 10; i++) {
            for (int j = N; j < N + 10; j++) {
                arr[i][j] = 1;
            }
        }
    }

    public static void sum() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                total += arr[i][j];
            }
        }
    }

}
