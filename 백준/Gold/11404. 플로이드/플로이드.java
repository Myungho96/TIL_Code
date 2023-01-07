import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static String[] temp;

    static int[][] arr;


    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                arr[i][j] = 987654321;
                if (i == j) {
                    arr[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            arr[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = Math.min(Integer.parseInt(temp[2]),arr[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]);
        }
        arr[1][1] = 0;
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(arr[i][j] != 987654321)
                    bw.write(arr[i][j] + " ");

                else
                    bw.write("0 ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();

    }


}