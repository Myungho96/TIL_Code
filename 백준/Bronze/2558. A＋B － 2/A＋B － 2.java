import java.awt.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        bw.write(Integer.parseInt(br.readLine())+Integer.parseInt(br.readLine())+"\n");
        bw.flush();
        bw.close();
    }
}