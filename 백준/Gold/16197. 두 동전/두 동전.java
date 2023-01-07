import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static String input = "1 2\n" +
            "oo";
    static String input2 = "5 3\n" +
            "###\n" +
            ".o.\n" +
            "#.#\n" +
            ".o.\n" +
            "###";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, result = -1;
    static String[][] arr;
    static int[][] deltas = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringTokenizer st;
    static boolean[][][][] visited;


    public static void main(String[] args) throws IOException {
        //br = new BufferedReader(new StringReader(input2));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new String[N][M];
        visited = new boolean[N][M][N][M];
        String[] temp;
        int[] startCoin = new int[5];
        int flag = 0;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if (temp[j].equals("o")) {
                    startCoin[2 * flag] = i;
                    startCoin[2 * flag + 1] = j;
                    flag++;
                }
                arr[i][j] = temp[j];
            }
        }
        bfs(startCoin);
        System.out.println(result);
    }

    static void bfs(int[] startCoin) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(startCoin);
        visited[startCoin[0]][startCoin[1]][startCoin[2]][startCoin[3]] = true;
        while (!queue.isEmpty()) {//이 조건이 확실치 않다.
            int[] node = queue.poll();
            //System.out.println(node[0] + " " + node[1] + " " + node[2] + " " + node[3] + " " + node[4]);
            for (int d = 0; d < deltas.length; d++) {
                int r1 = node[0] + deltas[d][0];
                int c1 = node[1] + deltas[d][1];
                int r2 = node[2] + deltas[d][0];
                int c2 = node[3] + deltas[d][1];//두 동전을 동시에 상하좌우 탐색을 해야한다.
                int cnt = node[4];

                if (cnt >= 10) {//10번보다 많이 눌러야 한다면 -1 리턴
                    result = -1;
                    return;
                }
                if (isIn(r1, c1) && arr[r1][c1].equals("#")) {//벽이면 이동하지 않는다.
                    r1 = node[0];
                    c1 = node[1];
                }
                if (isIn(r2, c2) && arr[r2][c2].equals("#")) {//벽이면 이동하지 않는다.
                    r2 = node[2];
                    c2 = node[3];
                }
                if ((isIn(r1, c1) && isIn(r2, c2)) && !visited[r1][c1][r2][c2]) {//이동 가능한경우
                    visited[r1][c1][r2][c2] = true;
                    queue.offer(new int[]{r1, c1, r2, c2, cnt + 1});
                } else if ((isIn(r1, c1) && !isIn(r2, c2)) || (!isIn(r1, c1) && isIn(r2, c2))) {//기저조건을 만족하는 경우
                    result = cnt + 1;
                    return;
                }
            }

        }

    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}