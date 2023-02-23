import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N, M, W, TC;
    public static int[] dist;
    public static List<Node>[] list;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            list = new List[N + 1];
            dist = new int[N + 1];
            Arrays.fill(dist, 987654321);
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list[a].add(new Node(b, c));
                list[b].add(new Node(a, c));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list[a].add(new Node(b, -c));
            }

            dist[1] = 0;
            //벨만포드 -> 하나만 보면 된다
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= N; j++) {
                    for (Node node : list[j]) {
                        if (dist[node.num] > dist[j] + node.cost) {
                            dist[node.num] = dist[j] + node.cost;
                        }
                    }
                }
            }
            boolean flag = false;
            //음수 사이클 테스트
            for (int j = 1; j <= N; j++) {
                for (Node node : list[j]) {
                    if (dist[node.num] > dist[j] + node.cost) {
                        bw.write("YES\n");
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    break;
            }
            if (!flag) {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();

    }

    private static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}