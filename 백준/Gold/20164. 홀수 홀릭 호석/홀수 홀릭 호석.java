import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, Min, Max;

    public static void main(String[] args) throws IOException {
        Min = 987654321;
        Max = 0;
        String temp = br.readLine();
        N = Integer.parseInt(temp);
        cal(temp,0);
        bw.write(Min + " " + Max);
        bw.flush();
        bw.close();


    }

    public static int cal(String num,int cnt) {
        int size = num.length();
        if (size == 1) {
            Min = Math.min(Min, cnt+Integer.parseInt(num) % 2);
            Max = Math.max(Max, cnt+Integer.parseInt(num) % 2);
        } else if (size == 2) {//홀수 체크한 후 두개로 나눠서 합을 구해 재귀
            int Cnt = 0;
            String[] temp = num.split("");
            for (int i = 0; i < 2; i++) {
                if (Integer.parseInt(temp[i]) % 2 == 1) {
                    Cnt++;
                }
            }
            cal(String.valueOf(Integer.parseInt(temp[0])+Integer.parseInt(temp[1])),cnt+Cnt);
        } else {
            String[] temp = num.split("");
            int Cnt = 0;
            for (int i = 0; i < temp.length; i++) {
                if (Integer.parseInt(temp[i]) % 2 == 1) {
                    Cnt++;
                }
            }
            //3등분 하기
            StringBuilder num1 = new StringBuilder();
            for (int i = 0; i < temp.length; i++) {
                num1.append(temp[i]);
                StringBuilder num2 = new StringBuilder();
                for (int j = i + 1; j < temp.length; j++) {
                    num2.append(temp[j]);
                    StringBuilder num3 = new StringBuilder();
                    for (int k = j + 1; k < temp.length; k++) {
                        num3.append(temp[k]);
                        if (k == temp.length - 1) {
                            cal(String.valueOf(Integer.parseInt(String.valueOf(num1))+Integer.parseInt(String.valueOf(num2))+Integer.parseInt(String.valueOf(num3))),cnt+Cnt);
                        }
                    }

                }

            }
        }
        return 0;
    }
}

