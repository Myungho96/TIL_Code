import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][][][] arr;
    static String[] temp;
    static int[][] deltas = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    static int min;
    public static void main(String[] args) throws Exception {
        min = Integer.MAX_VALUE;
        arr = new int[5][5][5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp = br.readLine().split(" ");
                for (int k = 0; k < 5; k++) {
                    arr[i][j][k][0] = Integer.parseInt(temp[k]);
                }
            }
        }
        rocation();
        DFS(0, new int[5]);
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    static void rocation() {
        for (int i = 0; i < 5; i++) {
            for (int T = 1; T < 4; T++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        arr[i][j][k][T] = arr[i][k][4 - j][T - 1];
                    }
                }
            }
        }
    }

    static void DFS(int cnt, int[] rocationNumber) {
        if (cnt == 5) {
            //회전 경우의 수가 나온 상태, 판들을 순열로 정렬해서 map을 만들어 주고 그 map에서 bfs를 수행하면 된다.
            perm(0, rocationNumber, new boolean[5], new int[5]);
            return;
        }
        for (int i = 0; i < 4; i++) {
            rocationNumber[cnt] = i;
            DFS(cnt + 1, rocationNumber);
        }
    }

    static void perm(int cnt, int[] rocationNumber, boolean[] visited, int[] stackNumber) {
        if (cnt == 5) {
            //완성된 순열이 온 상황, 회전수 리스트도 존재하므로 map을 만들어주어야 한다.
            int[][][] map = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        map[i][j][k] = arr[stackNumber[i]][j][k][rocationNumber[stackNumber[i]]];
                    }
                }
            }
            if (map[0][0][0] == 1)
                bfs(map);
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                stackNumber[cnt] = i;
                perm(cnt + 1, rocationNumber, visited, stackNumber);
                visited[i] = false;
            }
        }
    }

    static void bfs(int[][][] map) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.cnt > min) return;//더 진행해도 최소가 될 수 없다.
            if (node.i == 4 && node.j == 4 && node.k == 4 && map[4][4][4] == 1) {//도착했다면 그동안의 cnt를 min에 저장한다.
                min = Math.min(min, node.cnt);
                return;
            }
            for (int d = 0, size = deltas.length; d < size; d++) {//사방 + 위아래 탐색!
                int ci = node.i + deltas[d][0];
                int cj = node.j + deltas[d][1];
                int ck = node.k + deltas[d][2];
                int count = node.cnt;

                if (isIn(ci, cj, ck, map) && !visited[ci][cj][ck]) {
                    visited[ci][cj][ck] = true;
                    queue.offer(new Node(ci, cj, ck, count + 1));
                }
            }
        }
    }

    static boolean isIn(int i, int j, int k, int[][][] map) {
        return i >= 0 && j >= 0 && k >= 0 && i < 5 && j < 5 && k < 5 && map[i][j][k] != 0;
    }

    static class Node {
        int i, j, k, cnt;

        public Node(int i, int j, int k, int cnt) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.cnt = cnt;
        }
    }
}
