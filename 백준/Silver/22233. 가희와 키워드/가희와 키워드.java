import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {
    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        int cnt = N;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(",");
            for (int j = 0; j < temp.length; j++) {
                if (set.contains(temp[j])) {
                    cnt--;
                    set.remove(temp[j]);
                }
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }

}