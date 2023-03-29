import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M;
    static TreeMap<String,Integer> HMap;
    static TreeMap<String,Integer> ResultMap;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HMap = new TreeMap<>();
        ResultMap = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            HMap.put(br.readLine(),1);
        }
        for (int i = 0; i < M; i++) {
            try {
                String ex = br.readLine();
                if(HMap.get(ex)!=null)
                    ResultMap.put(ex,1);
            }catch (Exception e){
                continue;
            }
        }
        bw.write(ResultMap.size()+"\n");
        while (!ResultMap.isEmpty()){
            bw.write(ResultMap.pollFirstEntry().getKey()+"\n");
        }
        bw.flush();
        bw.close();

    }
}