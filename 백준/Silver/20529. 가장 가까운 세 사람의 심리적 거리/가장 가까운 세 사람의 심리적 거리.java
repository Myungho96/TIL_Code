import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Main {
    static int N, TC, result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
            result = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            String[] temp = br.readLine().split(" ");
            if (N > 32) {
                bw.write("0" + "\n");
            } else {
                for (int x = 0; x < N - 2; x++) {
                    for (int y = x + 1; y < N - 1; y++) {
                        for (int z = y + 1; z < N; z++) {
                            cal(temp[x].split(""), temp[y].split(""), temp[z].split(""));
                        }
                    }
                }
                bw.write(result + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void cal(String[] mbti1, String[] mbti2, String[] mbti3) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (!mbti1[i].equals(mbti2[i])) {
                sum++;
            }
            if (!mbti1[i].equals(mbti3[i])) {
                sum++;
            }
            if (!mbti2[i].equals(mbti3[i])) {
                sum++;
            }
        }
        result = Math.min(result, sum);
    }

}