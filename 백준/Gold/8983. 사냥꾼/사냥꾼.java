import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int L = Integer.parseInt(temp[2]);
        int Result = 0;
        int[] shooting = new int[N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            shooting[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(shooting);
        for (int i = 0; i < M; i++) {//동물은 담을 필요 없을듯
            temp = br.readLine().split(" ");
            //동물을 잡을 수 있는 사로가 있는지 체크한다.
            int r = Integer.parseInt(temp[0]);
            int c = Integer.parseInt(temp[1]);
            //c가 L보다 클 수가 있음. 그 경우에는 사대에서 잡을 수가 없음
            if (c > L)
                continue;
            //그게 아니면, 뭘로 이분탐색
            int findShooting = Arrays.binarySearch(shooting, c);
            if (findShooting >= 0) {//양수면 사냥 가능한 상태
                Result++;
            } else if (Math.abs(r - shooting[-(findShooting + 1)]) + c <= L) {//사냥 가능한지 계산해주기
                Result++;
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

}

