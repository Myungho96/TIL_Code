import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, R, Score;
    public static int[][] Arr;
    public static String[][] Result;
    public static Round[] Rounds;

    public static class Round {
        int ar, ac, dr, dc;
        String dir;

        public Round(int ar, int ac, String dir, int dr, int dc) {
            this.ar = ar;
            this.ac = ac;
            this.dir = dir;
            this.dr = dr;
            this.dc = dc;
        }

        @Override
        public String toString() {
            return ar + " " + ac + " " + dir + " " + dr + " " + dc;
        }
    }

    public static class Attack {
        int r, c;
        String dir;

        public Attack(int r, int c, String dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        R = Integer.parseInt(temp[2]);
        Rounds = new Round[R];
        Arr = new int[N][M];
        Result = new String[N][M];
        Score = 0;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                Arr[i][j] = Integer.parseInt(temp[j]);
                Result[i][j] = "S";
            }
        }
        for (int i = 0; i < R; i++) {
            temp = br.readLine().split(" ");
            int ar = Integer.parseInt(temp[0]) - 1;
            int ac = Integer.parseInt(temp[1]) - 1;
            String dir = temp[2];
            temp = br.readLine().split(" ");
            Rounds[i] = new Round(ar, ac, dir, Integer.parseInt(temp[0]) - 1, Integer.parseInt(temp[1]) - 1);
        }
        for (Round r : Rounds) {
            bfs(r);
        }
        bw.write(Score + "\n");
        for (int i = 0; i < N; i++) {
            for (String str : Result[i]) {
                bw.write(str + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }

    public static void bfs(Round start) {
        boolean[][] visited = new boolean[N][M];
        Queue<Attack> queue = new ArrayDeque<>();
        queue.offer(new Attack(start.ar, start.ac, start.dir));
        visited[start.ar][start.ac] = true;
        while (!queue.isEmpty()) {
            Attack attack = queue.poll();
            int cr = attack.r, cc = attack.c;
            int addR = 0, addC = 0;
            //공격하려는 도미노가 "F"인지를 체크하고, 아니면 방향을 체크하기
            if (Result[attack.r][attack.c].equals("F"))
                continue;
            Score++;
            Result[attack.r][attack.c] = "F";
            switch (attack.dir) {
                case "E":
//                    System.out.println("동");
                    addC = 1;
                    break;
                case "W":
//                    System.out.println("서");
                    addC = -1;
                    break;
                case "S":
//                    System.out.println("남");
                    addR = 1;
                    break;
                case "N":
//                    System.out.println("북");
                    addR = -1;
                    break;
            }

            for (int i = 0; i < Arr[attack.r][attack.c] - 1; i++) {
                cr += addR;
                cc += addC;
                //visited 체크해야하나...?
                if (cr >= 0 && cc >= 0 && cr < N && cc < M && !visited[cr][cc]) {
                    visited[cr][cc] = true;
                    queue.offer(new Attack(cr, cc, attack.dir));
                }
            }
            //방어하기


        }
        if (Result[start.dr][start.dc].equals("F")) {
            Result[start.dr][start.dc] = "S";
        }
    }


}

