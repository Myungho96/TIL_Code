import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = -1;
    static int[][][] Deltas = {
            {
                    {-1, 0}, {-1, -1}, {-1, -1}
            },
            {
                    {-1, 0}, {-1, 1}, {-1, 1}
            },
            {
                    {1, 0}, {1, -1}, {1, -1}
            },
            {
                    {1, 0}, {1, 1}, {1, 1}
            },
            {
                    {0, -1}, {-1, -1}, {-1, -1}
            },
            {
                    {0, -1}, {1, -1}, {1, -1}
            },
            {
                    {0, 1}, {1, 1}, {1, 1}
            },
            {
                    {0, 1}, {-1, 1}, {-1, 1}
            }
    };

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int startR = Integer.parseInt(temp[0]);
        int startC = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        int endR = Integer.parseInt(temp[0]);
        int endC = Integer.parseInt(temp[1]);

        bfs(startR, startC, endR, endC);

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int startR, int startC, int endR, int endC) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        boolean[][] visited = new boolean[10][9];
        visited[startR][startC] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            while (size-- > 0) {
                int[] node = queue.poll();
                for (int i = 0; i < 8; i++) {

                    int cr = node[0];
                    int cc = node[1];
                    boolean flag = false;
                    for (int j = 0; j < 2; j++) {
                        cr += Deltas[i][j][0];
                        cc += Deltas[i][j][1];
                        if (cr == endR && cc == endC)
                            flag = true;
                    }
                    if (flag)
                        continue;
                    cr += Deltas[i][2][0];
                    cc += Deltas[i][2][1];
                    if (cr >= 0 && cc >= 0 && cr < 10 && cc < 9 && !visited[cr][cc]) {
                        if (cr == endR && cc == endC) {
                            Result = cnt;
                            return;
                        }
                        visited[cr][cc] = true;
                        queue.offer(new int[]{cr, cc});
                    }
                }
            }


        }
    }


}
