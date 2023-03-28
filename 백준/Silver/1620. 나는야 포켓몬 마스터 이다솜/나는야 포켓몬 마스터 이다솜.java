import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M;
    static Map<String,Integer> Map;
    static Map<Integer,String> Map2;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map = new HashMap<>();
        Map2 = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            Map.put(temp,i+1);
            Map2.put(i+1,temp);

        }
        for (int i = 0; i < M; i++) {
            String temp = br.readLine();
            if(48<=(int)temp.charAt(0) && (int)temp.charAt(0)<=57){
                int num = Integer.parseInt(temp);
                bw.write(Map2.get(num)+"\n");
            }else{
                bw.write(Map.get(temp)+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
}