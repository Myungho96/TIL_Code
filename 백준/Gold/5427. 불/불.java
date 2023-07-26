import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }

    public static void main(String[] args) throws IOException {
        LinkedList<Node> fire = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited;
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            fire.clear();
            queue.clear();
            String[] temp = br.readLine().split(" ");
            int w = Integer.parseInt(temp[0]);
            int h = Integer.parseInt(temp[1]);
            char[][] arr = new char[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                temp = br.readLine().split("");
                for (int j = 0; j < w; j++) {
                    arr[i][j] = temp[j].charAt(0);
                    if (arr[i][j] == '@') {
                        arr[i][j] = '.';
                        queue.offer(new Node(i, j));
//                        visited[i][j] = true;
                    } else if (arr[i][j] == '*') {
                        fire.offer(new Node(i, j));
                        visited[i][j] = true;
                    }
                }
            }
            int cnt = 0;
            boolean flag = false;
            loop:
            while (!queue.isEmpty()) {
                cnt++;
                int size = queue.size();
                while (size-- > 0) {
                    Node node = queue.poll();
                    if ((node.r == h - 1 ||node.r == 0 || node.c == w - 1|| node.c == 0) && arr[node.r][node.c]=='.') {
                        bw.write(cnt + "\n");
                        flag = true;
                        break loop;
                    }
                    //사방탐색
                    for (int d = 0; d < 4; d++) {
                        int cr = node.r + Deltas[d][0];
                        int cc = node.c + Deltas[d][1];
                        if (cr >= 0 && cc >= 0 && cr < h && cc < w && !visited[cr][cc] && arr[cr][cc] == '.') {
                            visited[cr][cc] = true;
                            queue.offer(new Node(cr, cc));
                        }
                    }
                }
                //불 확산
                size = fire.size();
                while (size-- > 0) {
                    Node node = fire.poll();
                    for (int d = 0; d < 4; d++) {
                        int cr = node.r + Deltas[d][0];
                        int cc = node.c + Deltas[d][1];
                        if (cr >= 0 && cc >= 0 && cr < h && cc < w &&  arr[cr][cc] == '.') {
                            visited[cr][cc] = true;
                            arr[cr][cc]='*';
                            fire.offer(new Node(cr, cc));
                        }

                    }
                }
            }
            if (!flag)
                bw.write("IMPOSSIBLE\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}