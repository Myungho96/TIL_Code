import java.lang.*;
import java.io.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, K;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(br.readLine());
        int[][][] arr = new int[N + 1][M + 1][3];
        for(int i=0;i<=N;i++){
            for(int j=0;j<3;j++)
                arr[i][0][j] = 0;
        }
        for(int i=0;i<=M;i++){
            for(int j=0;j<3;j++)
                arr[0][1][j] = 0;
        }
        for (int i = 1; i <= N; i++) {
            temp = br.readLine().split("");
            int a = 0;
            int b = 0;
            int c = 0;
            for (int j = 1; j <= M; j++) {
                switch (temp[j-1]) {
                    case "J":
                        a++;
                        break;
                    case "O":
                        b++;
                        break;
                    case "I":
                        c++;
                        break;
                }
                arr[i][j][0] = a + arr[i - 1][j][0];
                arr[i][j][1] = b + arr[i - 1][j][1];
                arr[i][j][2] = c + arr[i - 1][j][2];
            }
        }
        for (int i = 0; i < K; i++) {
            temp = br.readLine().split(" ");
            int br = Integer.parseInt(temp[0]);
            int bc = Integer.parseInt(temp[1]);
            int ar = Integer.parseInt(temp[2]);
            int ac = Integer.parseInt(temp[3]);
            int a = arr[ar][ac][0] - arr[br - 1][ac][0] - arr[ar][bc - 1][0] + arr[br - 1][bc - 1][0];
            int b = arr[ar][ac][1] - arr[br - 1][ac][1] - arr[ar][bc - 1][1] + arr[br - 1][bc - 1][1];
            int c = arr[ar][ac][2] - arr[br - 1][ac][2] - arr[ar][bc - 1][2] + arr[br - 1][bc - 1][2];
            bw.write(a+" "+b+" "+c+"\n");
        }
        bw.flush();
        bw.close();
    }
}

