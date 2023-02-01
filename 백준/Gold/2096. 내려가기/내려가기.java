import java.io.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, max, min;
    public static int[][] dp1;
    public static int[][] dp2;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        //max, min을 각각 저장할 배열
        dp1 = new int[N][3];
        dp2 = new int[N][3];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = Integer.parseInt(temp[1]);
            arr[i][2] = Integer.parseInt(temp[2]);
            if (i == 0) {
                //dp 초기값
                dp1[i][0] = arr[i][0];
                dp1[i][1] = arr[i][1];
                dp1[i][2] = arr[i][2];
                dp2[i][0] = arr[i][0];
                dp2[i][1] = arr[i][1];
                dp2[i][2] = arr[i][2];
            }
        }
        //N까지 bottom-up 탐색
        dfs(1);
        bw.write(max + " " + min);
        bw.flush();
        bw.close();
    }

    private static void dfs(int i) {
        if (i == N) {
            min = Math.min(dp1[N - 1][0], dp1[N - 1][1]);
            min = Math.min(min, dp1[N - 1][2]);

            max = Math.max(dp2[N - 1][0], dp2[N - 1][1]);
            max = Math.max(max, dp2[N - 1][2]);
            return;
        }
        dp1[i][0] = Math.min(dp1[i - 1][0], dp1[i - 1][1]) + arr[i][0];
        dp1[i][1] = Math.min(Math.min(dp1[i - 1][0], dp1[i - 1][1]), dp1[i - 1][2]) + arr[i][1];
        dp1[i][2] = Math.min(dp1[i - 1][1], dp1[i - 1][2]) + arr[i][2];

        dp2[i][0] = Math.max(dp2[i - 1][0], dp2[i - 1][1]) + arr[i][0];
        dp2[i][1] = Math.max(Math.max(dp2[i - 1][0], dp2[i - 1][1]), dp2[i - 1][2]) + arr[i][1];
        dp2[i][2] = Math.max(dp2[i - 1][1], dp2[i - 1][2]) + arr[i][2];
        dfs(i + 1);
    }
}