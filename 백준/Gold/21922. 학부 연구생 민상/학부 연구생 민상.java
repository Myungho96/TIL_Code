import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int Result;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        Result = 0;
        int[][] arr = new int[N][M];
        int[][] visited = new int[N][M];
        List<Node> lists = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 9) {
                    lists.add(new Node(i, j));
                }
            }
        }
        bfs(lists, arr, visited);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

    public static void bfs(List<Node> lists, int[][] arr, int[][] visited) {
        Queue<Node> queue = new ArrayDeque<>();
        int N = visited.length;
        int M = visited[0].length;
        for (Node node : lists) {
            queue.offer(new Node(node.r, node.c));
            visited[node.r][node.c] = 1;
            Result++;
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = node.r;
                int cc = node.c;
                int addR = Deltas[i][0];
                int addC = Deltas[i][1];
                loop:
                while (true) {
                    cr += addR;
                    cc += addC;
                    if (cr < 0 || cc < 0 || cr >= N || cc >= M || visited[cr][cc] > 4)
                        break;
                    if (visited[cr][cc] == 0) {
                        Result++;
                    }
                    visited[cr][cc]++;
                    if (arr[cr][cc] == 0)
                        continue;
                    switch (arr[cr][cc]) {
                        case 1:
                            //addR이 1이나 -1인 경우, 영향 없음.
                            if (addC == 1 || addC == -1)
                                break loop;
                            break;
                        case 2:
                            //addC가 1이나 -1인 경우, 영향 없음.
                            if (addR == 1 || addR == -1)
                                break loop;
                            break;
                        case 3:
                            //들어오는 방향에 따라 앞으로 나아갈 방향 바꿔줘야함
                            if (addR == 1 && addC == 0) {
                                addR = 0;
                                addC = -1;
                            } else if (addR == -1 && addC == 0) {
                                addR = 0;
                                addC = 1;
                            } else if (addR == 0 && addC == 1) {
                                addR = -1;
                                addC = 0;

                            } else if (addR == 0 && addC == -1) {
                                addR = 1;
                                addC = 0;
                            }
                            break;
                        case 4:
                            if (addR == 1 && addC == 0) {
                                addR = 0;
                                addC = 1;
                            } else if (addR == -1 && addC == 0) {
                                addR = 0;
                                addC = -1;
                            } else if (addR == 0 && addC == 1) {
                                addR = 1;
                                addC = 0;

                            } else if (addR == 0 && addC == -1) {
                                addR = -1;
                                addC = 0;
                            }
                            break;

                    }

                }
            }
        }
    }

    public static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}

