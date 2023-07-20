import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int result = -1, len;
    static int[] deltas = {1, 10, 100, 1000, 10000, 100000, 1000000};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        len = Integer.toString(n).length();
        if (len == 1)
            bw.write(result + "\n");
        else {
            bfs(n, k);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(int n, int k) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][k + 1];
        queue.offer(new Node(n, 0));
        int totalCnt = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.cnt == k) {
                result = Math.max(result, node.num);
            } else
                for (int i = 0; i < len - 1; i++)
                    for (int j = i + 1; j < len; j++) {
                        if (j == len - 1 && (node.num / deltas[i]) % 10 == 0) {//0이 맨 앞에 오는 경우
                            continue;
                        } else {
                            int num = swap(node.num, i, j);
                            if (!visited[num][node.cnt + 1]) {
                                visited[num][node.cnt + 1] = true;
                                queue.offer(new Node(num, node.cnt + 1));
                            }

                        }

                    }
        }
    }

    public static int swap(int num, int i, int j) {
        int a = (num / deltas[i]) % 10;
        int b = (num / deltas[j]) % 10;
        return num - a * deltas[i] - b * deltas[j] + a * deltas[j] + b * deltas[i];
    }

    static class Node {
        int num, cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}