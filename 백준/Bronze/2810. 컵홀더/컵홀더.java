import java.io.*;
import java.util.Arrays;


public class Main {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        String temp = br.readLine().replace("LL", "*");
        int cnt = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == '*')
                cnt += 1;
        }
        if (cnt > 1) {
            bw.write(N - cnt + 1 + "\n");
        } else {
            bw.write(N + "\n");
        }
        bw.flush();
        bw.close();
    }

}