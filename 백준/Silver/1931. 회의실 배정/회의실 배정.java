import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        pq = new PriorityQueue<>();
        int cnt = 0;
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Node start = new Node(-1, -1);
        for (int i = 0; i < N; i++) {
            Node current = pq.poll();
            if (start.y <= current.x) {
                start = current;
                cnt++;
            }
        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();

    }

    private static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Node o) {
            if(this.y==o.y){
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(this.y, o.y);
        }
    }
}