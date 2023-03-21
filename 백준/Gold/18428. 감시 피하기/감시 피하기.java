import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static String Result;
    static int[][] Arr;
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static List<Node> Teachers;
    static List<Node> Blanks;

    public static void main(String[] args) throws IOException {
        Teachers = new ArrayList<>();
        Blanks = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Arr = new int[N + 1][N + 1];
        Result = "NO";
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                String temp = st.nextToken();
                if (temp.equals("S")) {
                    Arr[i][j] = 2;
                } else if (temp.equals("T")) {
                    Arr[i][j] = 3;
                    Teachers.add(new Node(i, j));
                } else {
                    Blanks.add(new Node(i, j));
                }
            }
        }
        //장애물 설치
        int BlankLen = Blanks.size();
        label:
        for (int i = 0; i < BlankLen; i++) {
            Arr[Blanks.get(i).r][Blanks.get(i).c] = 1;
            for (int j = i + 1; j < BlankLen; j++) {
                Arr[Blanks.get(j).r][Blanks.get(j).c] = 1;
                for (int k = j + 1; k < BlankLen; k++) {
                    Arr[Blanks.get(k).r][Blanks.get(k).c] = 1;
                    if (bfs() == 0) {
                        Result = "YES";
                        break label;
                    }
                    Arr[Blanks.get(k).r][Blanks.get(k).c] = 0;
                }
                Arr[Blanks.get(j).r][Blanks.get(j).c] = 0;
            }
            Arr[Blanks.get(i).r][Blanks.get(i).c] = 0;
        }

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        int cnt = 0;
        for (Node teacher : Teachers) {
            queue.offer(new Node(teacher.r, teacher.c));
//            visited[teacher.r][teacher.c] = true;
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {//한 방향으로 쭉 가보기
                Queue<Node> newQueue = new ArrayDeque<>();
                newQueue.offer(new Node(node.r, node.c));
                while (!newQueue.isEmpty()) {
                    boolean[][] visited = new boolean[N + 1][N + 1];
                    Node newNode = newQueue.poll();
                    int cr = newNode.r + deltas[i][0];
                    int cc = newNode.c + deltas[i][1];
                    if (!isIn(cr, cc))
                        break;
                    if (Arr[cr][cc] == 2) {
                        cnt++;
                        return cnt;
                    }
                    if (!visited[cr][cc] && Arr[cr][cc] != 1) {
                        visited[cr][cc] = true;
                        newQueue.offer(new Node(cr, cc));
                    }
                }


            }
        }
        return cnt;
    }

    private static boolean isIn(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= N;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


}