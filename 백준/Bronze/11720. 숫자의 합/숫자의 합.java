import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N, result;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split("");
        for (int i = 0; i < N; i++) {
            result += Integer.parseInt(temp[i]);
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();


    }


}