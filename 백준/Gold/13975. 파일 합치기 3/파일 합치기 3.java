import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String args[]) throws IOException{
        PriorityQueue<Long> pq;
        int test = Integer.parseInt(br.readLine());
        for(int t=0;t<test;t++){
            long result = 0;
            pq = new PriorityQueue<>();
            int k = Integer.parseInt(br.readLine());
            String []temp = br.readLine().split(" ");
            for(String str : temp){
                pq.offer(Long.parseLong(str));
            }
            while(pq.size()>1){
                long sum = pq.poll() + pq.poll();
                result+=sum;
                pq.offer(sum);
            }
            bw.write(result+"\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    

}