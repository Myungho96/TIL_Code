import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String temp = br.readLine();
        char ch = temp.charAt(0);
        bw.write((int) ch + "\n");
        bw.flush();
        bw.close();
    }
}