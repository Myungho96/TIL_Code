import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static StringTokenizer st;
    public static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        bfs(N, M, 0);
        bw.flush();
        bw.close();
    }
    static class Node implements Comparable<Node>{
        int start,cnt;

        public Node(int start, int cnt) {
            this.start = start;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt,o.cnt);
        }
    }
    private static void bfs(int start, int end, int cnt) throws IOException {
        PriorityQueue<Node> queue = new PriorityQueue<>();
//        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new Node(start,cnt));
        while (!queue.isEmpty()) {
            Node num = queue.poll();
            visited[num.start] = true;
            if (num.start == end) {
                bw.write(num.cnt + "\n");
                return;
            }

            if (start < end) {
                if(isIn(2*num.start) && !visited[2*num.start]){
                    queue.offer(new Node(2*num.start,num.cnt));
                }
                if(isIn(num.start+1) && !visited[num.start+1]){
                    queue.offer(new Node(num.start+1,num.cnt+1));
                }
                if(isIn(num.start-1) && !visited[num.start-1]){
                    queue.offer(new Node(num.start-1,num.cnt+1));
                }
            } else {
                if(isIn(num.start-1) && !visited[num.start-1]){
                    queue.offer(new Node(num.start-1,num.cnt+1));
                }
            }
        }


    }

    private static boolean isIn(int x) {
        return x >= 0 && x <= 100000;
    }

}