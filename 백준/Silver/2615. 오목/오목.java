import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] arr = new int[19][19];
    static int[][] deltas = new int[][]{{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (arr[i][j] != 0) {
                    if (sol(i, j)) {
                        bw.write(arr[i][j] + "\n");
                        bw.write((i + 1) + " " + (j + 1) + "\n");
                        bw.flush();
                        bw.close();
                        return;
                    }
                }
            }
        }
        bw.write(0 + "\n");
        bw.flush();
        bw.close();
        return;
    }

    public static boolean sol(int r, int c) {
        //우, 하 , 오른쪽 아래 대각선, 오른쪽 위 대각선 탐색
        int cnt;
        for (int d = 0; d < 4; d++) {
            cnt = 1;
            int cr = r;
            int cc = c;
            if(isIn(cr-deltas[d][0],cc-deltas[d][1]) && arr[cr-deltas[d][0]][cc-deltas[d][1]] == arr[cr][cc]){
                continue;
            }
            while (true) {
                cr += deltas[d][0];
                cc += deltas[d][1];
                if (cnt == 5) {
                    if (!isIn(cr,cc)){
                        return true;
                    }
                    else if (arr[r][c] != arr[cr][cc]) {
                        return true;
                    } else {
                        break;
                    }
                }
                if(isIn(cr,cc)){
                    if (arr[r][c] == arr[cr][cc]) {
                        cnt++;
                    } else {
                        break;
                    }
                }
                else
                    break;

            }
        }
        return false;
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < 19 && c < 19;
    }

}