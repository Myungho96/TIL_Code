import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long result;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        result = 1;
        arr = new int[10];
        for (int i = 0; i < 3; i++) {
            int temp = Integer.parseInt(br.readLine());
            result *= temp;
        }
        String str = String.valueOf(result);
        for (int i = 0; i < str.length(); i++) {
            arr[Integer.parseInt(String.valueOf(str.charAt(i)))]++;
        }
        for (int i = 0; i < 10; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();


    }


}