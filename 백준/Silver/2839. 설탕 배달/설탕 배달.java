import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, cnt = 0;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        while (true) {
            if (N < 3 && N > 0) {
                cnt = -1;
                break;
            }
            if (N == 0) {
                break;
            }
            if (N % 5 == 0) {
                N -= 5;
                cnt++;
            } else if (N % 3 == 0) {
                N -= 3;
                cnt++;
            } else if (N>5) {
                N -= 5;
                cnt++;
            }else {
                N -= 3;
                cnt++;
            }
        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();

    }


}