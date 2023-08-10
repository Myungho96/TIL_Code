import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result = 0;

    public static void main(String[] args) throws IOException {
        int num = Integer.parseInt(br.readLine());
        int[][][] arr = new int[num][6][7];
        for (int n = 0; n < num; n++) {
            String[] temp = br.readLine().split(" ");
            for (int i = 0; i < 6; i++) {
                arr[n][0][i] = Integer.parseInt(temp[i]);
            } //회전 시켜서 각각 저장하기
            arr[n][0][6] = Math.max(Math.max(arr[n][0][2], arr[n][0][1]), Math.max(arr[n][0][3], arr[n][0][4]));
            for (int i = 1; i < 6; i++) {
                if (i == 4) {
                    arr[n][i][2] = arr[n][i - 1][0];
                    arr[n][i][4] = arr[n][i - 1][5];
                    arr[n][i][0] = arr[n][i - 1][4];
                    arr[n][i][1] = arr[n][i - 1][1];
                    arr[n][i][3] = arr[n][i - 1][3];
                    arr[n][i][5] = arr[n][i - 1][2];
                    arr[n][i][6] = Math.max(Math.max(arr[n][i][2], arr[n][i][1]), Math.max(arr[n][i][3], arr[n][i][4]));
                } else if (i == 5) {
                    arr[n][i][2] = arr[n][i - 2][5];
                    arr[n][i][4] = arr[n][i - 2][0];
                    arr[n][i][0] = arr[n][i - 2][2];
                    arr[n][i][1] = arr[n][i - 2][1];
                    arr[n][i][3] = arr[n][i - 2][3];
                    arr[n][i][5] = arr[n][i - 2][4];
                    arr[n][i][6] = Math.max(Math.max(arr[n][i][2], arr[n][i][1]), Math.max(arr[n][i][3], arr[n][i][4]));
                } else {
                    arr[n][i][2] = arr[n][i - 1][2];
                    arr[n][i][4] = arr[n][i - 1][4];
                    arr[n][i][0] = arr[n][i - 1][1];
                    arr[n][i][1] = arr[n][i - 1][5];
                    arr[n][i][3] = arr[n][i - 1][0];
                    arr[n][i][5] = arr[n][i - 1][3];
                    arr[n][i][6] = Math.max(Math.max(arr[n][i][2], arr[n][i][1]), Math.max(arr[n][i][3], arr[n][i][4]));
                }
            }
        }
        //위아래 빼면 결국 최댓값은 6번에 저장된 값이 된다.
        dfs(0, 0, 0, arr);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt, int sum, int lastUp, int[][][] arr) {
        int num = arr.length;
        if (Result >= (num - cnt) * 6 + sum)
            return;
        if (cnt == num) {
            Result = Math.max(sum, Result);
            return;
        }
        for (int i = 0; i < 6; i++) {
            if (cnt == 0) {
                dfs(cnt + 1, sum + arr[cnt][i][6], arr[cnt][i][0], arr);
            } else {
                //현재 주사위 아랫면과 직전 주사위 윗면이 같은지 비교
                if (arr[cnt][i][5] == lastUp) {
                    dfs(cnt + 1, sum + arr[cnt][i][6], arr[cnt][i][0], arr);
                }
            }
        }


    }


}