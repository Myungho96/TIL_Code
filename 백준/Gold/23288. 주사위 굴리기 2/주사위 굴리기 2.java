import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int N, M, K, point;

    public static int[][] arr, dice;
    public static Node current;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dice = new int[][]{{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};
        int dir = 0;
        current = new Node(1, 1);
        for (int i = 0; i < K; i++) {
            //1. 이동하면서 방향에 따라 주사위 배열 조정
            dir = changeDiceLocation(dir);
            //BFS를 통해 점수 계산
            bfs();
            //크기에 따라 dir 조정
            dir = changeDir(dir);

        }
        bw.write(point + "\n");
        bw.flush();
        bw.close();
    }

    private static int changeDir(int dir) {
        if (dice[3][1] > arr[current.x][current.y]) {
            dir = (dir + 1) % 4;
        } else if (dice[3][1] < arr[current.x][current.y]) {
            if (dir == 0)
                dir = 3;
            else
                dir -= 1;
        }
        return dir;
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(current.x, current.y));
        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[current.x][current.y] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int cr = node.x + deltas[i][0];
                int cc = node.y + deltas[i][1];
                if (isIn(cr, cc) && !visited[cr][cc] && arr[current.x][current.y] == arr[cr][cc]) {
                    visited[cr][cc] = true;
                    queue.offer(new Node(cr, cc));
                }
            }
        }
        point += cnt * arr[current.x][current.y];
    }

    private static int changeDiceLocation(int dir) {
        //그 방향으로 계속 갈 수 있는지 체크한다. 만약 더이상 그 방향으로 가지 못한다면, 이동 방향을 반대로 바꾼다.
        if (!isIn(current.x + deltas[dir][0], current.y + deltas[dir][1])) {
            switch (dir) {
                case 0:
                    dir = 2;
                    break;
                case 1:
                    dir = 3;
                    break;
                case 2:
                    dir = 0;
                    break;
                case 3:
                    dir = 1;
                    break;
            }
        }

        //현재 주사위 위치 변경
        current.x += deltas[dir][0];
        current.y += deltas[dir][1];

        //주사위 숫자 방향에 따라 바꾸기
        int pretemp, temp;
        switch (dir) {
            case 0:
                pretemp = dice[1][0];
                for (int i = 1; i < 3; i++) {
                    temp = dice[1][i];
                    dice[1][i] = pretemp;
                    pretemp = temp;
                }
                temp = dice[3][1];
                dice[3][1] = pretemp;
                dice[1][0] = temp;
                break;
            case 1:
                pretemp = dice[0][1];
                for (int i = 1; i < 4; i++) {
                    temp = dice[i][1];
                    dice[i][1] = pretemp;
                    pretemp = temp;
                }
                dice[0][1] = pretemp;
                break;
            case 2:
                pretemp = dice[1][2];
                for (int i = 1; i >= 0; i--) {
                    temp = dice[1][i];
                    dice[1][i] = pretemp;
                    pretemp = temp;
                }
                temp = dice[3][1];
                dice[3][1] = pretemp;
                dice[1][2] = temp;
                break;
            case 3:
                pretemp = dice[3][1];
                for (int i = 2; i >= 0; i--) {
                    temp = dice[i][1];
                    dice[i][1] = pretemp;
                    pretemp = temp;
                }
                dice[3][1] = pretemp;
                break;

        }
        return dir;
    }

    private static boolean isIn(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= M;
    }

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}