import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, R, C, cnt = 0;
    static StringTokenizer st;
    //static int[][] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);
        int startR = 0;
        int startC = 0;
        while (true) {
            if (size == 2) {
                cnt += 2 * (R - startR) + (C - startC);
//                arr[startR][startC] = cnt++;
//                arr[startR][startC + 1] = cnt++;
//                arr[startR + 1][startC] = cnt++;
//                arr[startR + 1][startC + 1] = cnt++;
                break;
            }//이 밑은 size가 최소 4이다.
            if (R < startR + size / 2 && C < startC + size / 2) {
                size /= 2;
                continue;
            } else if (R < startR + size / 2 && C >= startC + size / 2) {
                cnt += size * size / 4;
                size /= 2;
                startC += size;
                continue;
            } else if (R >= startR + size / 2 && C < startC + size / 2) {
                cnt += size * size / 2;
                size /= 2;
                startR += size;
                continue;
            } else if (R >= startR + size / 2 && C >= startC + size / 2) {
                cnt += size * size / 4 * 3;
                size /= 2;
                startR += size;
                startC += size;
                continue;
            }
        }

        bw.write(cnt + "\n");
        bw.flush();
        bw.close();

    }


}