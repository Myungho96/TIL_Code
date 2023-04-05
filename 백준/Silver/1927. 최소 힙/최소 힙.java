import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<Integer> queue;
    public static void main(String[] args) throws IOException {
        queue = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            if(M==0){
                if(queue.size()==0){
                    bw.write("0\n");
                }else{
                    bw.write(queue.poll()+"\n");
                }
            }else{
                queue.add(M);
            }
        }

        bw.flush();
        bw.close();

    }
}