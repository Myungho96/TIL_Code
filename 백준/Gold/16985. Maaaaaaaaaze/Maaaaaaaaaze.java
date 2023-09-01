import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Queue<Integer>[] queue = new Queue[5];
    static int Result = 987654321;
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static void main(String[] args) throws IOException {
        //몇번째 블록, 행,열,돌렸을때의 값
        int[][][][] arr = new int[5][4][5][5];
        for (int t = 0; t < 5; t++) {
            for (int i = 0; i < 5; i++) {
                String[] temp = br.readLine().split(" ");
                for (int j = 0; j < 5; j++) {
                    arr[t][0][i][j] = Integer.parseInt(temp[j]);
                }
            }
        }
        //돌린것도 넣어주기
        for (int i = 1; i < 4; i++) {
            turn(i, arr);
        }

        //순서 정하기
        permu(0, new HashMap<>(), new HashMap<>(), new boolean[5], arr);
        bw.write((Result == 987654321 ? -1 : Result) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void permu(int cnt, HashMap<Integer, Integer> order, HashMap<Integer, Integer> floor, boolean[] visited, int[][][][] arr) {
        if (cnt == 5) {
            solveDir(0, new int[5], order, floor, arr);
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order.put(cnt, i);//1번째 2 이런식으로 저장
                floor.put(i, cnt);//2는 첫번째
                permu(cnt + 1, order, floor, visited, arr);
                visited[i] = false;
            }
        }
    }

    static class Node {
        int floor, r, c, cnt;

        public Node(int floor, int r, int c, int cnt) {
            this.floor = floor;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void bfs(int[] dir, HashMap<Integer, Integer> order, HashMap<Integer, Integer> floor, int[][][][] arr) {
        Queue<Node> que = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];

        que.offer(new Node(order.get(0), 0, 0, 0));
        visited[order.get(0)][0][0] = true;
        while (!que.isEmpty()) {
            Node node = que.poll();
            if (node.cnt > Result) return;
            if (node.floor == order.get(4) && node.r == 4 && node.c == 4) {
                Result = Math.min(node.cnt, Result);
                return;
            }
            //사방탐색
            for (int i = 0; i < 4; i++) {
                int cr = node.r + Deltas[i][0];
                int cc = node.c + Deltas[i][1];
                if (cr >= 0 && cc >= 0 && cr < 5 && cc < 5 && !visited[node.floor][cr][cc] && arr[node.floor][dir[node.floor]][cr][cc] == 1) {
                    visited[node.floor][cr][cc] = true;
                    que.offer(new Node(node.floor, cr, cc, node.cnt + 1));
                }

            }
            //위아래 갈 수 있는지 탐색 -> floor 활용
            int currentFloor = floor.get(node.floor);
            //아래부터 체크
            if (currentFloor + 1 < 5 && !visited[order.get(currentFloor + 1)][node.r][node.c] && arr[order.get(currentFloor + 1)][dir[order.get(currentFloor + 1)]][node.r][node.c] == 1) {
                visited[order.get(currentFloor + 1)][node.r][node.c] = true;
                que.offer(new Node(order.get(currentFloor + 1), node.r, node.c, node.cnt + 1));
            }
            if (currentFloor - 1 >= 0 && !visited[order.get(currentFloor - 1)][node.r][node.c] && arr[order.get(currentFloor - 1)][dir[order.get(currentFloor - 1)]][node.r][node.c] == 1) {
                visited[order.get(currentFloor - 1)][node.r][node.c] = true;
                que.offer(new Node(order.get(currentFloor - 1), node.r, node.c, node.cnt + 1));
            }
        }


    }


    private static void solveDir(int cnt, int[] dir, HashMap<Integer, Integer> order, HashMap<Integer, Integer> floor, int[][][][] arr) {
        if (cnt == 5) {
            if(arr[order.get(0)][dir[order.get(0)]][0][0] == 1)
                bfs(dir, order, floor, arr);
            return;
        }
        for (int i = 0; i < 4; i++) {
            dir[cnt] = i;
            solveDir(cnt + 1, dir, order, floor, arr);
        }

    }

    private static void turn(int dir, int[][][][] arr) throws IOException {
        for (int t = 0; t < 5; t++) {

            for (int i = 0; i < 5; i++) {
                //큐 초기화
                queue[i] = new ArrayDeque<>();
                for (int j = 0; j < 5; j++) {
                    //직전 dir 담기
                    queue[i].offer(arr[t][dir - 1][i][j]);
                }
            }
            int idx = 0;
            for (int j = 4; j >= 0; j--) {
                for (int i = 0; i < 5; i++) {
                    arr[t][dir][i][j] = queue[idx].poll();
                }
                idx++;
            }

        }

    }
}


