import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int H, W, Result = 0;
    public static int[] Arr;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        H = Integer.parseInt(temp[0]);
        W = Integer.parseInt(temp[1]);
        Arr = new int[W];
        temp = br.readLine().split(" ");
        for (int i = 0; i < W; i++) {
            Arr[i] = Integer.parseInt(temp[i]);
        }
        cal();
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    public static void cal() {

        for (int i = 0; i < W - 1; i++) {
            for (int j = 1; j <= Arr[i]; j++) {
                int num = i+1;
                int cnt=0;
                while(true){
                    if (j - Arr[num] > 0 && num != W - 1) {
                        num++;
                        cnt++;
                    }else if(j - Arr[num] > 0 && num == W - 1){
                        break;
                    }else{
                        Result+=cnt;
                        break;
                    }
                }
            }
        }
    }

}

