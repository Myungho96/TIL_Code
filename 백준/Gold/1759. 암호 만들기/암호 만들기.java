import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int M,N;
    static String []temp,arr;
    public static void main(String[] args) throws IOException {
        temp = br.readLine().split(" ");
        M = Integer.parseInt(temp[0]);
        N = Integer.parseInt(temp[1]);
        arr = new String[N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = temp[i];
        }
        Arrays.sort(arr);
        comb(0,new String [M],0);
        bw.flush();
        bw.close();
    }

    private static void comb(int index, String[] choosed, int start) throws IOException {
        if(index == M){
            int flag = 0;
            int flag2 = 0;
            for (int i = 0; i < M; i++) {
                if(check(choosed[i])){
                    flag ++;
                }else {
                    flag2++;
                }
            }
            if(flag >=1 && flag2>=2){
                for (int i = 0; i < M; i++) {
                    bw.write(choosed[i]);
                }
                bw.write("\n");
                return;
            }
            return;

        }

        for (int i = start; i < N; i++) {
            choosed[index] = arr[i];
            comb(index+1,choosed,i+1);
        }
    }

    public static boolean check(String N) {//모음을 체크하기 위한 메소드
        return N.equals("a") || N.equals("e") || N.equals("i") || N.equals("o") || N.equals("u");//모음일 경우 true를 반환한다.
    }
}
