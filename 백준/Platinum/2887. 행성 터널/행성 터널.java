import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        List<Node>[] lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
            String[] temp = br.readLine().split(" ");
            arr[i][0] = i;
            for (int j = 0; j < 3; j++) {
                arr[i][j+1] = Integer.parseInt(temp[j]);
            }
        }

        for (int t = 1; t < 4; t++) {
            int T = t;
            Arrays.sort(arr, (o1, o2) -> o1[T] - o2[T]);
            for (int i = 0; i < n - 1; i++) {
                int cnt = Math.abs(arr[i][T] - arr[i + 1][T]);

                lists[arr[i][0]].add(new Node(arr[i + 1][0], cnt));
                lists[arr[i + 1][0]].add(new Node(arr[i][0], cnt));
            }
        }



        bfs(lists, n);

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(List<Node>[] lists, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        pq.offer(new Node(0, 0));
        int cnt = 0;
        int sum = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.num]) {
                continue;
            }
            visited[node.num] = true;
            cnt++;
            sum += node.cnt;
            if (cnt == n) {
                Result = sum;
                return;
            }
            for (Node list : lists[node.num]) {
                if (!visited[list.num])
                    pq.offer(new Node(list.num, list.cnt));
            }
        }
    }

    static class Node implements Comparable<Node> {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }


}
