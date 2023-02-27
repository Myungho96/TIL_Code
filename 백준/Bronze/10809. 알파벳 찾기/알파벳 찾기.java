import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N;
    
    public static int []arr;
    public static List<Character> list;
    public static void main(String[] args) throws IOException {
        String []temp = br.readLine().split("");
        list = new ArrayList<>();
        arr = new int[26];

        for (int i = 0; i < temp.length; i++) {
            list.add(temp[i].charAt(0));
        }
        Arrays.fill(arr,-1);
        for (int i = 0; i < 26; i++) {
            char temp2 = (char) ('a'+i);
            if(list.indexOf(temp2)!=-1){
                arr[i] = list.indexOf(temp2);
            }
        }

        for (int i = 0; i < 26; i++) {
            bw.write(arr[i]+" ");
        }
        
        bw.flush();
        bw.close();
    }
}