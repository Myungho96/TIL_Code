import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String temp = "";
            temp += addBlank(N - i - 1);
            temp += addStar(i + 1);
            bw.write(temp+"\n");
        }


        bw.flush();
        bw.close();
    }

    private static String addStar(int i) {
        String temp = "";
        for (int j = 0; j < i; j++) {
            temp += "*";
        }
        return temp;
    }

    private static String addBlank(int i) {
        String temp = "";
        for (int j = 0; j < i; j++) {
            temp += " ";
        }
        return temp;
    }
}