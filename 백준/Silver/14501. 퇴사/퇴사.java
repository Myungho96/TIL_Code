import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max;
    static int[][] arr;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int index, int sum) {
        if(index > N){
            return;
        }else if(index == N){
            max = Math.max(max,sum);
            return;
        }
        int flag = 0;
        for (int i = index; i < N; i++) {
            dfs(index+arr[i][0]+flag,sum+arr[i][1]);
            flag++;
        }
        max = Math.max(max,sum);
        return;
    }


}