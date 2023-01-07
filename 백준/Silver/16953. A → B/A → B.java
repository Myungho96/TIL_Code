import java.io.*;
import java.lang.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long N, M, result;
    static String[] temp;


    public static void main(String[] args) throws IOException {
        result=-1;
        temp = br.readLine().split(" ");
        N = Long.parseLong(temp[0]);
        M = Long.parseLong(temp[1]);
        bfs();
        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }

    private static void bfs() {
        Queue<long[]> queue = new ArrayDeque<>();
        queue.offer(new long[]{N,1});
        while(!queue.isEmpty()){
            long []num = queue.poll();
            if(num[0] == M){
                result=num[1];
                return;
            }
            else if(num[0]>M){
                continue;
            }else{
                queue.offer(new long[]{2*num[0],num[1]+1});
                queue.offer(new long[]{num[0]*10+1,num[1]+1});
            }
        }
    }


}