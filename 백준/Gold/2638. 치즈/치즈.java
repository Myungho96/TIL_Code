import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int N, M, oneCnt, currentCnt, time;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oneCnt = 0;
        currentCnt = 0;
        time = 0;
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1)
                    oneCnt++;
            }
        }

        bfs();

        bw.write(time + "\n");
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        while (oneCnt > currentCnt) {
            queue.offer(new Node(0, 0));
            boolean[][] visited = new boolean[N][M];
            time++;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (oneCnt == currentCnt)
                    break;
                for (int i = 0; i < 4; i++) {
                    int cr = node.x + deltas[i][0];
                    int cc = node.y + deltas[i][1];
                    if (isIn(cr, cc)) {//범위 안에 있고
                        if (!visited[cr][cc] && arr[cr][cc] == 0) {//방문안했는데 0인경우, 큐에 넣는다.
                            visited[cr][cc] = true;
                            queue.offer(new Node(cr, cc));
                        } else if (!visited[cr][cc] && arr[cr][cc] == 1) {//방문 안했는데 1인경우, 방문처리 후 재방문 기다리기
                            visited[cr][cc] = true;
                        } else if (visited[cr][cc] && arr[cr][cc] == 1) {//방문했고 1인경우, 0으로 바꾸고 currentCnt++
                            arr[cr][cc] = 0;
                            currentCnt++;
                        }
                    }
                }
            }

        }
    }

    private static boolean isIn(int cr, int cc) {
        return cr >= 0 && cc >= 0 && cr < N && cc < M;
    }

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}