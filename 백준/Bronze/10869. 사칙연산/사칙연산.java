import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int num1 = Integer.parseInt(temp[0]);
        int num2 = Integer.parseInt(temp[1]);

        bw.write(num1 + num2 + "\n");
        bw.write(num1 - num2 + "\n");
        bw.write(num1 * num2 + "\n");
        bw.write(num1 / num2 + "\n");
        bw.write(num1 % num2 + "\n");
        bw.flush();
        bw.close();

    }
}
