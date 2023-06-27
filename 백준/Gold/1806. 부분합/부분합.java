import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, Result = 987654321;
    public static int[] Arr;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        Arr = new int[N];
        temp = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            Arr[i] = Integer.parseInt(temp[i]);
        }
        int start = 0;
        int end = 0;
        int cnt = 1;
        int sum = Arr[start];
        while(start<=end){
            if(M>sum){
                if(end==N-1)
                    break;
                cnt++;
                end++;
                sum+=Arr[end];
            }else{//합이 M보다 큰 경우
                Result = Math.min(Result,cnt);
                cnt--;
                sum-=Arr[start];
                start++;
            }

        }
        if(Result==987654321)
            Result=0;
        bw.write(Result+"\n");
        bw.flush();
        bw.close();

    }
}