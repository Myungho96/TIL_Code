import java.io.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int TC, x1, y1, r1, x2, y2, r2;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            String[] temp = br.readLine().split(" ");
            x1 = Integer.parseInt(temp[0]);
            y1 = Integer.parseInt(temp[1]);
            r1 = Integer.parseInt(temp[2]);
            x2 = Integer.parseInt(temp[3]);
            y2 = Integer.parseInt(temp[4]);
            r2 = Integer.parseInt(temp[5]);

            int distance = (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            if (x1 == x2 && y1 == y2 && r1 == r2)
                bw.write(-1 + "\n");
            else if (Math.pow(r2 - r1, 2) > distance || distance > Math.pow(r2 + r1, 2))
                bw.write(0 + "\n");
            else if (Math.pow(r2 - r1, 2) == distance || distance == Math.pow(r2 + r1, 2))
                bw.write(1 + "\n");
            else
                bw.write(2 + "\n");
        }
        bw.flush();
        bw.close();
    }
}