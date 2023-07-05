import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, K, ZeroCnt = 0, Stage = 0;
    public static Belt[] Belts;


    public static class Belt {
        int usedCnt;
        boolean existRobot;
        boolean isZero;

        public Belt(int usedCnt, boolean existRobot, boolean isZero) {
            this.usedCnt = usedCnt;
            this.existRobot = existRobot;
            this.isZero = isZero;
        }

        public String toString() {
            return usedCnt + " " + existRobot;
        }
    }


    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);
        Belts = new Belt[2 * N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < 2 * N; i++) {
            Belts[i] = new Belt(Integer.parseInt(temp[i]), false, false);
        }

        solve();
        bw.write(Stage + "\n");
        bw.flush();
        bw.close();
    }

    public static void solve() {
        while (ZeroCnt < K) {
            Stage++;
            //1. 벨트 회전 배열 안썼더니 시간초과 나는듯..
            Belt temp = Belts[0];
            for (int i = 0; i < 2 * N - 1; i++) {
                Belt temp2 = Belts[i + 1];
                Belts[i + 1] = temp;
                temp = temp2;
            }
            Belts[0] = temp;
            if (Belts[N - 1].existRobot)
                Belts[N - 1].existRobot = false;
            //로봇 한칸씩 이동
            for (int i = N - 2; i >= 0; i--) {
                if (i == N - 2 && Belts[N - 2].existRobot && Belts[N - 1].usedCnt > 0) {
                    Belts[N - 1].usedCnt--;
                    Belts[N - 2].existRobot = false;
                } else if (Belts[i].existRobot && !Belts[i + 1].existRobot && Belts[i + 1].usedCnt > 0) {
                    Belts[i + 1].usedCnt--;
                    Belts[i + 1].existRobot = true;
                    Belts[i].existRobot = false;
                }

                if (Belts[i + 1].usedCnt == 0 && !Belts[i + 1].isZero) {
                    ZeroCnt++;
                    Belts[i + 1].isZero = true;

                }
            }
            if (!Belts[0].existRobot && Belts[0].usedCnt > 0) {
                Belts[0].usedCnt--;
                Belts[0].existRobot = true;
                if (Belts[0].usedCnt == 0 && !Belts[0].isZero) {
                    ZeroCnt++;
                    Belts[0].isZero = true;
                }
            }


        }
    }


}

