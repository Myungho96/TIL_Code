import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr;
    public static int N, M, T, rot = 0;

    public static void main(String[] args) throws IOException {
        String[] tempString = br.readLine().split(" ");
        N = Integer.parseInt(tempString[0]);
        M = Integer.parseInt(tempString[1]);
        T = Integer.parseInt(tempString[2]);
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempString = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(tempString[j]);
            }
        }
        //T = T % (2 * N + 2 * M - 4);//실제로 돌아가는 값

        rot = Math.min(M, N) / 2;
        Rotation();

        //test
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    public static void Rotation() {
        int tempN = N + 1;
        int tempM = M + 1;
        for (int i = 0; i < rot; i++) {
            tempN -= 2;
            tempM -= 2;
            int cnt = T % (2 * (N - 2 * i) + 2 * (M - 2 * i - 2));
            for (int T = 0; T <cnt; T++) {
                int firstNum = arr[i][i];

                for (int j = 0; j < tempM; j++) {//윗변부터 진행하자.
                    arr[i][i + j] = arr[i][i + j + 1];
                }
                for (int j = 0; j < tempN; j++) {//오른쪽면 아래에서 위로
                    arr[i + j][M - 1 - i] = arr[i + j + 1][M - 1 - i];
                }
                for (int j = 0; j < tempM; j++) {//아래면 왼쪽부터 오른쪽으로
                    arr[N - 1 - i][M - j - i - 1] = arr[N - 1 - i][M - j - i - 2];
                }
                for (int j = 0; j < tempN; j++) {//왼쪽면 위에서 아래로
                    arr[N - 1 - j - i][i] = arr[N - 2 - j - i][i];
                }
                arr[i + 1][i] = firstNum;
            }


        }
    }


}
