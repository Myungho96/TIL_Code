import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;
    static int[][] Deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        //1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
        Queue<Node>[][] map = new Queue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new LinkedList<>();
            }
        }
        List<Node> lists = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int r = Integer.parseInt(temp[0]) - 1;
            int c = Integer.parseInt(temp[1]) - 1;
            int weight = Integer.parseInt(temp[2]);
            int s = Integer.parseInt(temp[3]);
            int d = Integer.parseInt(temp[4]);
            lists.add(new Node(r, c, weight, s, d));
//            map[r][c].add(new Node(r, c, weight, s, d));
        }
        bfs(lists, map, k);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(List<Node> lists, Queue<Node>[][] map, int k) {
        int n = map.length;
        Queue<Node> queue = new LinkedList<>();
        for (Node node : lists) {
            queue.offer(new Node(node.r, node.c, node.m, node.s, node.d));
        }
        int cnt = -1;
        while (!queue.isEmpty()) {
            cnt++;
            if (cnt == k) {
                //질량의 합 구하기
                while (!queue.isEmpty()) {
                    Result += queue.poll().m;
                }
                return;
            }
            int size = queue.size();
            //파이어볼 이동
            while (size-- > 0) {
                Node node = queue.poll();
                int cr = (node.r + Deltas[node.d][0] * node.s + 1000*n) % n;
                int cc = (node.c + Deltas[node.d][1] * node.s + 1000*n) % n;
                map[cr][cc].offer(new Node(cr, cc, node.m, node.s, node.d));
            }
            //2개 이상 있는 칸이 있는지 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].size() >= 2) {
                        int len = map[i][j].size();
                        int totalM = 0;
                        int totalS = 0;
                        int even = 0;
                        for (int x = 0; x < len; x++) {
                            Node temp = map[i][j].poll();
                            totalM += temp.m;
                            totalS += temp.s;
                            if (temp.d % 2 == 0)
                                even++;

                        }
                        totalM /= 5;
                        if (totalM == 0)
                            continue;
                        totalS /= len;
                        if (even == len || even == 0) {
                            for (int x = 0; x < 8; x += 2) {
                                queue.offer(new Node(i, j, totalM, totalS, x));
                            }
                        } else {
                            for (int x = 1; x < 8; x += 2) {
                                queue.offer(new Node(i, j, totalM, totalS, x));
                            }
                        }
                    } else if (map[i][j].size() == 1) {
                        queue.offer(map[i][j].poll());
                    }
                }
            }
        }
    }

    static class Node {
        int r, c, m, s, d;

        public Node(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public boolean equals(Object ob) {
            Node o = (Node) ob;
            return this.r == o.r && this.c == o.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }//0,2 0,5

}
