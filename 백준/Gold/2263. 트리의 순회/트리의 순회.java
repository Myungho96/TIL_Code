import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N,idx;
    public static int []inOrder,postOrder,preOrder;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        idx=0;
        inOrder = new int[N];
        postOrder = new int[N];
        preOrder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
        getPreOrder(0, N - 1, 0, N - 1);
        for (int i = 0; i < N; i++) {
            bw.write(preOrder[i]+" ");
        }
        bw.flush();
        bw.close();

    }

    public static void getPreOrder(int is, int ie, int ps, int pe) {

        if (is <= ie && ps <= pe) {
            preOrder[idx++] = postOrder[pe];
            int pos = is;
            for (int i = is; i <= ie; i++) {
                if (inOrder[i] == postOrder[pe]) {
                    pos = i;
                    break;
                }
            }
            getPreOrder(is, pos - 1, ps, ps + pos - is - 1);
            getPreOrder(pos + 1, ie, ps + pos - is, pe - 1);
        }
    }

}