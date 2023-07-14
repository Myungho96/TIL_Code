import java.lang.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, K;

    public static class Node {
        int j, o, i;

        public Node(int j, int o, int i) {
            this.j = j;
            this.o = o;
            this.i = i;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "j=" + j +
                    ", o=" + o +
                    ", i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(br.readLine());
        Node[][] arr = new Node[N + 1][M + 1];
        for(int i=0;i<=N;i++){
            arr[i][0] = new Node(0,0,0);
        }
        for(int i=0;i<=M;i++){
            arr[0][i] = new Node(0,0,0);
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
                if (i == 0) {
                    arr[i][j] = new Node(a, b, c);
                } else {
                    arr[i][j] = new Node(a + arr[i - 1][j].j, b + arr[i - 1][j].o, c + arr[i - 1][j].i);
                }
            }
        }
        for (int i = 0; i < K; i++) {
            temp = br.readLine().split(" ");
            int br = Integer.parseInt(temp[0]);
            int bc = Integer.parseInt(temp[1]);
            int ar = Integer.parseInt(temp[2]);
            int ac = Integer.parseInt(temp[3]);
            int a = arr[ar][ac].j - arr[br - 1][ac].j - arr[ar][bc - 1].j + arr[br - 1][bc - 1].j;
            int b = arr[ar][ac].o - arr[br - 1][ac].o - arr[ar][bc - 1].o + arr[br - 1][bc - 1].o;
            int c = arr[ar][ac].i - arr[br - 1][ac].i - arr[ar][bc - 1].i + arr[br - 1][bc - 1].i;
            bw.write(a+" "+b+" "+c+"\n");
        }
        bw.flush();
        bw.close();
    }
}

