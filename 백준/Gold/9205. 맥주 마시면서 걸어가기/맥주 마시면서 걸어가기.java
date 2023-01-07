import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, T;
    static String[] temp;
    static boolean[] visited;
    static Node start;
    static Node end;
    static List<Node> stores;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            stores = new ArrayList<>();
            temp = br.readLine().split(" ");
            start = new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            for (int i = 0; i <= N; i++) {
                if (i == N) {
                    temp = br.readLine().split(" ");
                    end = new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                } else {
                    temp = br.readLine().split(" ");
                    stores.add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                }
            }
            boolean result = bfs();
            if (result) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start.r, start.c));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (isArrive(node, end))
                return true;
            for (int i = 0; i < N; i++) {
                Node store = stores.get(i);
                if (isArrive(node, store) && !visited[i]) {
                    visited[i] = true;
                    queue.offer(new Node(store.r, store.c));
                }
            }
        }
        return false;
    }

    private static boolean isArrive(Node node, Node end) {
        return (Math.abs(node.r - end.r) + Math.abs(node.c - end.c)) <= 1000;
    }

    public static class Node {
        int r, c;

        public Node(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
}