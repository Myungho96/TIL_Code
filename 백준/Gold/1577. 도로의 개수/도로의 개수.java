import java.lang.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        BigDecimal[][] arr = new BigDecimal[N + 1][M + 1];
        boolean[][][] stop = new boolean[N + 1][M + 1][2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {

                arr[i][j] = new BigDecimal("0");
            }
        }
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            temp = br.readLine().split(" ");
            if ((Integer.parseInt(temp[0]) > Integer.parseInt(temp[2])) || (Integer.parseInt(temp[1]) > Integer.parseInt(temp[3]))) {
                if (Integer.parseInt(temp[0]) - Integer.parseInt(temp[2]) > 0) {
                    stop[Integer.parseInt(temp[2])][Integer.parseInt(temp[3])][0]=true;
                } else {
                    stop[Integer.parseInt(temp[2])][Integer.parseInt(temp[3])][1]=true;
                }
            } else {
                if (Integer.parseInt(temp[2]) - Integer.parseInt(temp[0]) > 0) {
                    stop[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][0]=true;
                } else {
                    stop[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][1]=true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (stop[i - 1][0][0])
                break;
            arr[i][0] = new BigDecimal("1");
        }
        for (int j = 1; j <= M; j++) {
            if (stop[0][j - 1][1])
                break;
            arr[0][j] = new BigDecimal("1");
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                //가로 도로 부실공사 여부
                if (!stop[i - 1][j][0]) {
                    arr[i][j] = arr[i][j].add(arr[i - 1][j]);
                }

                //세로 도로 부실공사 여부
                if (!stop[i][j - 1][1]) {
                    arr[i][j] = arr[i][j].add(arr[i][j - 1]);
                }
            }//for end
        }//for end
        bw.write(arr[N][M] + "\n");
        bw.flush();
        bw.close();

    }
}

