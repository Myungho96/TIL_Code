import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;
    static int[][] Deltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        List<Node> cloud = new LinkedList<>();
        //초기 구름 미리 담기
        for (int i = n - 2; i < n; i++) {
            cloud.add(new Node(i, 0));
            cloud.add(new Node(i, 1));
        }
        //구름에 이동에 따른 시뮬레이션 시작해주기
        for (int i = 0; i < m; i++) {
            boolean[][] visited = new boolean[n][n];
            temp = br.readLine().split(" ");
            int dir = Integer.parseInt(temp[0]) - 1;
            int moveCnt = Integer.parseInt(temp[1]);
            for (Node node : cloud) {
                node.r = (node.r + Deltas[dir][0] * moveCnt % n + n) % n;
                node.c = (node.c + Deltas[dir][1] * moveCnt % n + n) % n;
                arr[node.r][node.c]++;
                visited[node.r][node.c] = true;

            }
            for (Node node : cloud) {
                for (int j = 1; j < 8; j += 2) {
                    int cr = node.r + Deltas[j][0];
                    int cc = node.c + Deltas[j][1];
                    if (cr >= 0 && cc >= 0 && cr < n && cc < n && arr[cr][cc] != 0)
                        arr[node.r][node.c]++;
                }
            }
            //구름 초기화
            cloud.clear();
            //새로 구름 만들기
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (arr[j][k] >= 2 && !visited[j][k]) {
                        arr[j][k] -= 2;
                        cloud.add(new Node(j, k));
                    }
                }
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Result += arr[i][j];
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public String toString() {
            return r + " " + c;
        }
    }
}