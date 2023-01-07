import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static long[][] arr = new long[66][10];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        //어떤수로 시작하는지를 카운트해서 arr에 반영할 것이다.
        //1. 한자리 수일때 -> 0~9까지 시작하는 수가 1개씩 있으므로 arr을 1로 채워준다.
        for (int i = 0; i < 10; i++) {
            arr[1][i] = 1;
        }
        //9로 시작하는 경우는 모든 수에 대해 1가지 경우밖에 없다.
        for (int i = 1; i < 65; i++) {
            arr[i][9] = 1;
        }
        //나머지 칸은 현재 구해야 하는 자릿수의 직전 자리수에서 같은 수로 시작하는 경우 + 현재 자리에서 구해야 하는 수 +1 의 누적의 합이다.
        for (int i = 2; i < 65; i++) {
            for (int j = 8; j >= 0; j--) {
                arr[i][j] = arr[i-1][j] + arr[i][j+1];
            }
        }
        //64의 결과값을 한번에 구하기 위해!
        for (int i = 0; i < 10; i++) {
            arr[65][0]+=arr[64][i];
        }
        //값 받아서 합계 출력
        for (int i = 0; i < N; i++) {
            M = Integer.parseInt(br.readLine());
            bw.write(arr[M+1][0]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
