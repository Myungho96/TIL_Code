import java.io.*;
import java.util.*;
//https://tussle.tistory.com/1002
public class Main {
    static final int P = 1000000007;	//나머지 기준 값
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long N = 1, S = 0;
        //합 구하기
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            S = s * N + S * n;
            N *= n;
            S %= P;
            N %= P;

        }
        //기약 분수일 때
        if(S % N != 0)
            bw.write((search(N, P-2) * S) % P + "");
        else{//기약 분수가 아닐 때
            bw.write(S/N + "");
        }		  
        bw.flush();		//결과 출력
        bw.close();
    }
    static long search(long N, int index) {
        if(index == 1)
            return N;
        long temp = search(N, index/2);
        if(index % 2 == 1)
            return temp * temp % P * N % P;
        else
            return temp * temp % P;
    }
}