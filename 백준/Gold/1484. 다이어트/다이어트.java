import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int G;

    public static double[] Arr;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        G = Integer.parseInt(br.readLine());
        int start = (int) Math.ceil(Math.sqrt(G));

        int end = (G + 1) / 2 + 1;
        Arr = new double[end + 1];
//        System.out.println(start + " " + end);
        for (int i = 1; i <= end; i++) {
            Arr[i] = (double) i * i;
        }
//        System.out.println(Arrays.toString(Arr));
        //num부터 1씩증가하면서 end까지 현재 몸무게의 조건에 맞는게 있는지를 살피는데, 1 차이날 때 G이상 차이가 나면 그냥 바로 break
        for (int i = start; i <= end; i++) {
            for (int j = i - 1; j > 0; j--) {
                int temp = (int) (Arr[i] - Arr[j]);
                if (temp == G) {
                    flag=true;
                    bw.write((int) Math.sqrt(Arr[i]) + "\n");
                } else if (temp > G) {
                    break;
                }
            }
        }
        if(!flag)
            bw.write("-1\n");
        bw.flush();
        bw.close();
    }

}

