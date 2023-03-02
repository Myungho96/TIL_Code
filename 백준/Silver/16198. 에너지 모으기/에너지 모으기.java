import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N, Max;

    public static void main(String[] args) throws IOException {
        Max = 0;
        N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            list.add(Integer.valueOf(temp[i]));
        }
        solution(list, 0);
        bw.write(Max + "\n");

        bw.flush();
        bw.close();
    }

    private static void solution(List<Integer> list, int sum) {
        if (list.size() <= 2) {
            if (sum > Max)
                Max = sum;
            return;
        }
        for (int i = 1; i < list.size() - 1; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int x :
                    list) {
                tempList.add(x);
            }
            int mul = tempList.get(i - 1) * tempList.get(i + 1);
            tempList.remove(i);
            solution(tempList, sum + mul);
        }
    }
}