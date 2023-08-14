import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int Result = 987654321;

    public static void main(String[] args) throws IOException {
//        int[][] arr = new int[10][10];
        boolean[][] toggle = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < 10; j++) {
                if (temp[j] - 'O' == 0)
                    toggle[i][j] = true;
            }
        }

        subSet(0, 0, toggle);
        if (Result == 987654321) {
            Result = -1;
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void subSet(int cnt, int switchNum, boolean[][] toggle) {
        if (cnt == 10) {
            boolean [][]temp = new boolean[10][10];
            for(int i=0;i<10;i++)
                for(int j=0;j<10;j++)
                    temp[i][j] = toggle[i][j];
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (temp[i - 1][j]) {
                        switchNum++;
                        temp[i][j] = !temp[i][j];
                        for (int d = 0; d < 4; d++) {
                            int cr = i + Deltas[d][0];
                            int cc = j + Deltas[d][1];
                            if (cr >= 0 && cc >= 0 && cr < 10 && cc < 10) {
                                temp[cr][cc] = !temp[cr][cc];
                            }
                        }
                    }
                }
            }
            boolean flag = true;
            for (int i = 0; i < 10; i++) {
                if(temp[9][i]){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Result = Math.min(Result,switchNum);
            }
            return;
        }
        boolean [][]temp = new boolean[10][10];
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                temp[i][j] = toggle[i][j];

        temp[0][cnt] = !temp[0][cnt];
        for (int i = 0; i < 4; i++) {
            int cr = Deltas[i][0];
            int cc = cnt + Deltas[i][1];
            if (cr >= 0 && cc >= 0 && cr < 10 && cc < 10) {
                temp[cr][cc] = !temp[cr][cc];
            }
        }
        subSet(cnt + 1, switchNum + 1, temp);

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                temp[i][j] = toggle[i][j];
        subSet(cnt + 1, switchNum, temp);
    }
}
