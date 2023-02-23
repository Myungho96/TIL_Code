import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int TC, N;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        StringBuffer sb = new StringBuffer(temp[0]);
        int num1 = Integer.parseInt(sb.reverse().toString());
        sb = new StringBuffer(temp[1]);
        int num2 = Integer.parseInt(sb.reverse().toString());
        int result = Math.max(num1, num2);
        bw.write(result + "\n");
        bw.flush();
        bw.close();


    }


}