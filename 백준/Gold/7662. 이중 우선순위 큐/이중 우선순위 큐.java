import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;

public class Main {
    static int [][]deltas = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int K,T;
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            K = Integer.parseInt(br.readLine());
            TreeMap<Integer,Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if("I".equals(cal)){//숫자 넣는 경우
                    try{
                        treeMap.put(num,treeMap.get(num)+1);
                    }catch (Exception e){
                        treeMap.put(num,1);
                    }
                }else{
                    if(treeMap.isEmpty())
                        continue;
                    if(num==-1){//최솟값 제거
                        Map.Entry<Integer, Integer> entity = treeMap.pollFirstEntry();
                        if(entity.getValue()>1){
                            treeMap.put(entity.getKey(), entity.getValue()-1);
                        }
                    }else{
                        Map.Entry<Integer, Integer> entity = treeMap.pollLastEntry();
                        if(entity.getValue()>1){
                            treeMap.put(entity.getKey(), entity.getValue()-1);
                        }
                    }
                }

            }
            if(treeMap.isEmpty())
                bw.write("EMPTY\n");
            else{
//                Map.Entry<Integer, Integer> entity = treeMap.pollFirstEntry();
//                if(entity.getValue()>1){
//                    treeMap.put(entity.getKey(), entity.getValue()-1);
//                }
                int min = treeMap.firstKey();
//                entity = treeMap.pollLastEntry();
//                if(entity.getValue()>1){
//                    treeMap.put(entity.getKey(), entity.getValue()-1);
//                }
                int max = treeMap.lastKey();
                bw.write(max+" "+min+"\n");
            }
        }
        bw.flush();
        bw.close();

    }
}