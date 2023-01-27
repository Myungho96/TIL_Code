import java.io.*;
import java.util.Arrays;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, result;
    static int[][] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[M][3];
        parent = new int[N];
        result = 0;
        String[] temp;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            graph[i][0] = Integer.parseInt(temp[0]);
            graph[i][1] = Integer.parseInt(temp[1]);
            graph[i][2] = Integer.parseInt(temp[2]);
        }
        // 낮은 비용부터 크루스칼 가능하므로 소팅해준다.
        Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        make();


        for (int i = 0; i < M; i++) {
            // 사이클이 존재하지 않는 경우에만
            if (find(graph[i][0] - 1) != find(graph[i][1] - 1)) {
                union(graph[i][0] - 1, graph[i][1] - 1);
                result += graph[i][2];
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static void make() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;
        else
            return find(parent[x]);
    }


}