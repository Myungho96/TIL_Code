import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int K, T;
    static Node[] Arr;

    public static void main(String[] args) throws IOException {
        Arr = new Node[41];
        Arr[0] = new Node(1, 0);
        Arr[1] = new Node(0, 1);
        fibo(2);
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(br.readLine());
            bw.write(Arr[num].zero + " " + Arr[num].one + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static void fibo(int i) {
        if (i == 41)
            return;
        Arr[i] = new Node(Arr[i - 1].zero + Arr[i - 2].zero, Arr[i - 1].one + Arr[i - 2].one);
        fibo(i+1);
    }

    private static class Node {
        int zero, one;

        public Node(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }
    }
}