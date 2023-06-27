import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, R, C, K, Num = 0;
    public static int[][] Arr;
    public static int[][] Deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public static int[] Dice = {0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        R = Integer.parseInt(temp[2]);
        C = Integer.parseInt(temp[3]);
        K = Integer.parseInt(temp[4]);

        Arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                Arr[i][j] = Integer.parseInt(temp[j]);
            }
        }

        temp = br.readLine().split(" ");
        for (int i = 0; i < temp.length; i++) {
            int dir = Integer.parseInt(temp[i]) - 1;
            int cr = R + Deltas[dir][0];
            int cc = C + Deltas[dir][1];
            if (cr >= 0 && cc >= 0 && cr < N && cc < M) {
                R = cr;
                C = cc;
//                System.out.println("R 과 C - " + R + " " + C);
                changeDicePos(dir);
                if (Arr[cr][cc] == 0) {
                    Arr[cr][cc] = Dice[5];
                } else {
                    Dice[5] = Arr[cr][cc];
                    Arr[cr][cc] = 0;
                }
                bw.write(Dice[0] + "\n");
            }


        }


        bw.flush();
        bw.close();
    }

    public static void changeDicePos(int dir) {
        int temp;
        switch (dir) {
            case 0:
                temp = Dice[0];
                Dice[0] = Dice[3];
                Dice[3] = Dice[5];
                Dice[5] = Dice[2];
                Dice[2] = temp;
                break;
            case 1:
                temp = Dice[0];
                Dice[0] = Dice[2];
                Dice[2] = Dice[5];
                Dice[5] = Dice[3];
                Dice[3] = temp;
                break;
            case 2:
                temp = Dice[0];
                Dice[0] = Dice[4];
                Dice[4] = Dice[5];
                Dice[5] = Dice[1];
                Dice[1] = temp;
                break;
            case 3:
                temp = Dice[0];
                Dice[0] = Dice[1];
                Dice[1] = Dice[5];
                Dice[5] = Dice[4];
                Dice[4] = temp;
                break;
        }
    }
}