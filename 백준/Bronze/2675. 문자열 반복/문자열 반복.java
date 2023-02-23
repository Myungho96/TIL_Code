import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int TC,N;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            String []temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            String str = temp[1];
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < N; j++) {
                    bw.write(str.charAt(i));
                }
            }
            bw.write("\n");

        }
        bw.flush();
        bw.close();


    }


}