import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static List<Node> []list;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            int cnt = bfs(start,K);
            bw.write( cnt+"\n");
        }


        bw.flush();
        bw.close();
    }

    private static int bfs(int start, int K) {
        int cnt = 0;
        Queue<Node> queue = new ArrayDeque<>();
        boolean []visited = new boolean[N+1];
        queue.offer(new Node(start,987654321));
        visited[start] = true;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.cost >= K && node.cost!=987654321){
                cnt++;
            }
            for (Node nextNode:
                 list[node.num]) {
                if(!visited[nextNode.num]){
                    visited[nextNode.num] = true;
                    queue.offer(new Node(nextNode.num,Math.min(nextNode.cost, node.cost)));
                }
            }
        }
        return cnt;
    }

    public static class Node{
        int num,cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

}