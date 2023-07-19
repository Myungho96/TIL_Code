import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int x = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder(br.readLine());
        String []original = answer.toString().split("");
        String[] temp = answer.toString().split("");
        int cnt = 0;
        String []str = new String[temp.length];
        while (cnt++ < x) {
            int idx=0;
            for (int i = 0; i < temp.length; i += 2) {
                str[idx++] = temp[i];
            }
            for (int i = temp.length - 1 - (temp.length % 2); i > 0; i -= 2) {
                str[idx++] = temp[i];
            }
            if(check(original,str)){
                x = cnt+x%cnt;
            }
            for(int i=0;i<temp.length;i++){
                    temp[i]=str[i];
            }
        }
        answer = new StringBuilder("");
        for(int i=0;i<temp.length;i++){
            answer.append(temp[i]);
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();

    }
    static boolean check(String []temp,String []str){
        for(int i=0;i<temp.length;i++){
            if(!temp[i].equals(str[i]))
                return false;
        }
        return true;
    }
}