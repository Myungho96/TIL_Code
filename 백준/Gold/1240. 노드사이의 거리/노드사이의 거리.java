import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M;
    static List<Node> []list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
       String []temp = br.readLine().split(" ");
       N = Integer.parseInt(temp[0]);
       M = Integer.parseInt(temp[1]);
       list = new List[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            temp = br.readLine().split(" ");
            list[Integer.parseInt(temp[0])].add(new Node(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
            list[Integer.parseInt(temp[1])].add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[2])));
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            bw.write(bfs(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]))+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int bfs(int start, int end) {
        visited = new boolean[N+1];
        Queue<Edge> queue = new ArrayDeque<>();
        queue.offer(new Edge(start,0));
        visited[start] = true;
        while (!queue.isEmpty()){
            Edge edge = queue.poll();
            if(edge.num == end){
                return edge.cnt;
            }
            for (Node node:
                 list[edge.num]) {
                if(!visited[node.num]){
                    visited[node.num] = true;
                    queue.offer(new Edge(node.num, edge.cnt+ node.dis));
                }
            }
        }
        return 0;
    }


    private static class Node {
        int num, dis;

        public Node(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }
    }
    private static class Edge {
        int num, cnt;

        public Edge(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}