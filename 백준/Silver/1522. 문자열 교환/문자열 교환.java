import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 987654321;

    public static void main(String[] args) throws IOException {
        char[] temp = br.readLine().toCharArray();
        int aCnt = 0;
        int bCnt = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 'a')
                aCnt++;
        }
        for (int i = 0; i < temp.length + aCnt - 1; i++) {
            if (i >= aCnt) {
                if (temp[i - aCnt] == 'b')
                    bCnt--;
                if (temp[i%temp.length] == 'b')
                    bCnt++;
                Result = Math.min(Result, bCnt);
            } else {
                if (temp[i] == 'b')
                    bCnt++;
                if (aCnt-1 == i)
                    Result = Math.min(Result, bCnt);
            }
        }
        if(Result==987654321)
            Result=0;
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }
}