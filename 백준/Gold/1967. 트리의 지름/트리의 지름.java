import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, maxNum, maxDis;
    public static List<Node>[] list;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        if(N==1){
            bw.write(0 + "\n");
            bw.flush();
            bw.close();
            return;
        }
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);
        visited = new boolean[N + 1];
        visited[maxNum] = true;
        dfs(maxNum, 0);
        bw.write(maxDis + "\n");
        bw.flush();
        bw.close();

    }

    private static void dfs(int num, int dis) {
        if (dis > maxDis) {
            maxDis = dis;
            maxNum = num;
        }
        for (Node node : list[num]) {
            if (!visited[node.num]) {
                visited[node.num] = true;
                dfs(node.num, dis + node.cost);
            }
        }
    }

    private static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

}