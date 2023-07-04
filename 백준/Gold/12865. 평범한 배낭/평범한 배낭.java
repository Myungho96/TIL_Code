import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, K;
    public static int[][] Dp;
    public static Node[] Products;

    public static class Node {
        int W, V;

        public Node(int W, int V) {
            this.W = W;
            this.V = V;
        }

        @Override
        public String toString() {
            return W + " " + V;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);
        Dp = new int[N][K + 1];
        Products = new Node[N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            Products[i] = new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        cal();
        bw.write(Dp[N - 1][K] + "\n");
        bw.flush();
        bw.close();

    }

    public static void cal() {
        //0번째는 K 넘으면 그냥 넣어주기
        for (int i = 1; i <= K; i++)
            if (i >= Products[0].W) {
                Dp[0][i] = Products[0].V;
            }

        for (int i = 1; i < N; i++)
            for (int j = 1; j <= K; j++) {
                Dp[i][j] = Dp[i - 1][j];
                int margin = j - Products[i].W;
                if (margin >= 0) {
                    Dp[i][j] = Math.max(Dp[i][j], Dp[i - 1][margin] + Products[i].V);
                }
            }
    }
}

