import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int[][] arr;
    public static boolean[] visited;
    public static int N, M, K, X;
    public static List<Integer> list = new ArrayList<>();
    public static List<List<Integer>> datas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            datas.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            datas.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }
        bfs();
        if (list.size() == 0) {
            bw.write(-1 + "\n");
        } else {
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void bfs() {//도달이 가능한지 아닌지 여부이므로(최단거리와 관계없다.) dfs로 짜는게 나을 것 같다.
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{X, 0});
        visited[X] = true;
        while (!queue.isEmpty()) {
            Integer[] node = queue.poll();
            if (node[1] == K)
                list.add(node[0]);
            for (int i = 0; i < datas.get(node[0]).size(); i++) {
                int next = datas.get(node[0]).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new Integer[]{next, node[1] + 1});
                }


            }
        }
    }


}