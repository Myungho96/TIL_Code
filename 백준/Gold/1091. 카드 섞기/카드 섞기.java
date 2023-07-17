import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = -1;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n];
        int[] s = new int[n];
        String[] temp = br.readLine().split(" ");
        String[] temp2 = br.readLine().split(" ");
        int []tempP = new int[n];
        for (int i = 0; i < n; i++) {
            //초기값 저장할 배열
            tempP[i] = Integer.parseInt(temp[i]);
            p[i] = Integer.parseInt(temp[i]);
            s[i] = Integer.parseInt(temp2[i]);
        }


        if (check(p)) {
            Result = 0;
        } else {
            //p를 받아 최종적으로 s가 되어야한다.
            int cnt = 0;
            while (true) {
                p = change(p, s);
                cnt++;
                if (check(p)) {
                    Result = cnt;
                    break;
                }
                if(cycleCheck(p,tempP)){
                    break;
                }

            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

    public static boolean check(int[] p) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] != i % 3)
                return false;
        }
        return true;
    }

    public static boolean cycleCheck(int[] p,int []tempP) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] != tempP[i])
                return false;
        }
        return true;
    }

    public static int[] change(int[] p, int[] s) {
        int[] arr = new int[p.length];
        int cnt = 0;
        for (int i : s) {
            arr[i] = p[cnt++];
        }
        return arr;
    }
}

