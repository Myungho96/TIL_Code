import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int TC, N, A, B, max;
    static boolean[] isPrime;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        isPrime = new boolean[1000001];
        isPrime[1] = true; //1은 소수가 아니다.
        for (int i = 2; i * i <= 1000000; i++) {
            if (!isPrime[i]) {// 소수면, 그 수를 제외하고 그 수의 배수부터 소수 아님 처리
                for (int j = i * i; j <= 1000000; j += i)
                    isPrime[j] = true;
            }
        }

        TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            A = Integer.parseInt(temp[1]);
            B = Integer.parseInt(temp[2]);
            max = Math.max(N, B);
            visited = new boolean[max + 1];

            int result = bfs();
            bw.write(result + "\n");
        }
        bw.flush();
        br.close();

//        // 소수 출력
//        for (int i = 1; i <= 1000000; i++) {
//            if (!isPrime[i])
//                System.out.print(i + " ");
//        }
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(N, 0));
        visited[N] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.num >= A && node.num <= B && !isPrime[node.num]) {
                return node.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int transNum = trans(i, node.num);
                if (isIn(transNum)) {
                    visited[transNum] = true;
                    queue.offer(new Node(transNum, node.cnt + 1));
                }
            }

        }
        return -1;
    }

    private static boolean isIn(int transNum) {
        return transNum >= 0 && transNum <= max && !visited[transNum];
    }

    private static int trans(int i, int nodeNum) {
        int transNum = -1;
        switch (i) {
            case 0:
                transNum = nodeNum / 2;
                break;
            case 1:
                transNum = nodeNum / 3;
                break;
            case 2:
                transNum = nodeNum + 1;
                break;
            case 3:
                transNum = nodeNum - 1;
                break;
        }
        return transNum;
    }

    private static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}