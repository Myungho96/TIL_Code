import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N, cnt;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        cnt = 0;
        nQueen(0);
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();


    }

    public static void nQueen(int depth) {
        // 행을 다 체우면 카운트를 1 증가시키고 리턴시킨다.
        if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (isQueen(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static boolean isQueen(int x) {

        for (int i = 0; i < x; i++) {
            if (arr[x] == arr[i]) {
                return false;
            } else if (Math.abs(x - i) == Math.abs(arr[x] - arr[i])) {
                return false;
            }
        }

        return true;
    }


}