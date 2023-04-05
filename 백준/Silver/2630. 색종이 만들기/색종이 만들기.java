import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,White,Blue;
    static int [][]Arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        Arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        White=0;
        Blue=0;
        solve(0,0,N);
        bw.write(White+"\n");
        bw.write(Blue+"\n");
        bw.flush();
        bw.close();

    }

    private static void solve(int r, int c, int n) {
        boolean flag = false;
        int color = Arr[r][c];
        for (int i = r; i < r+n; i++) {
            for (int j = c; j < c+n; j++) {
                if(Arr[i][j]!=color) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
        if(flag){//종이를 분할한다.
            solve(r,c,n/2);
            solve(r,c+n/2,n/2);
            solve(r+n/2,c,n/2);
            solve(r+n/2,c+n/2,n/2);

        }else{
            if(color==0)
                White++;
            else
                Blue++;
        }

    }
}