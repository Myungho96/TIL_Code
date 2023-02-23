import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int maxNum = 0,maxPos=-1;
        for (int i = 0; i < 9; i++) {
            int temp = Integer.parseInt(br.readLine());
            if(temp>maxNum){
                maxNum=temp;
                maxPos=i;
            }
        }
        bw.write(maxNum+"\n");
        bw.write(maxPos+1+"\n");
        bw.flush();
        bw.close();


    }


}