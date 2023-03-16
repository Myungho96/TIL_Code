import java.io.*;
import java.util.*;

public class Main {
    public static int N, E, V1, V2, V1Start, V2Start, V1End, V2End, V1V2, V1V2Flag;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Node>[] Lists;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        Lists = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            Lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Lists[a].add(new Node(b, c));
            Lists[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        //이 문제는 V1, V2를 꼭 지나야한다.
        //그러므로 다익스트라를 3번 해줘야할 것 같다.
        //1. 1번정점에서 V1,V2로 가는 최단 경로 구하기
        //2. V1에서 V2로 가는 최단 경로 구하기(갈수 있는지?)
        //3. N에서 V1,V2으로 가는 최단 경로 구하기(양방향이라 반대로 가도 최소 똑같음)
        V1V2Flag = 0;
        //1번 부터 진행하자.
        int result = 0;
        Dij(V1,3);
        if (V1V2Flag == 1)
            bw.write(-1 + "\n");
        else {
            Dij(1,1);
            Dij(N,2);
            int sum1 = 987654321;
            int sum2 = 987654321;
            if (V1Start != 987654321 && V2End != 987654321)
                sum1 = V1Start + V2End;
            if (V2Start != 987654321 && V1End != 987654321)
                sum2 = V2Start + V1End;
            if (sum1 == 987654321 && sum2 == 987654321)
                bw.write(-1 + "\n");
            else if (sum1 != 987654321 && sum2 == 987654321) {
                bw.write(sum2 + V1V2 + "\n");
            } else if (sum1 == 987654321 && sum2 != 987654321) {
                bw.write(sum1 + V1V2 + "\n");
            } else {
                bw.write(Math.min(sum1, sum2) + V1V2 + "\n");
            }
        }
        bw.flush();
        bw.close();

    }

    private static void Dij(int start, int type) {
        Queue<Node> queue = new ArrayDeque<>();
        int[] D = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(D, 987654321);
        D[start] = 0;
//        visited[start] = true;
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (D[node.num] < node.cost)
                continue;
            for (Node temp : Lists[node.num]) {
                if (D[temp.num] > D[node.num] + temp.cost) {
                    D[temp.num] = D[node.num] + temp.cost;
                    queue.offer(new Node(temp.num, D[temp.num]));
                }
            }
        }
        if (type == 1) {
            V1Start = D[V1];
            V2Start = D[V2];
        } else if (type == 2) {
            V1End = D[V1];
            V2End = D[V2];
        } else {//V1에서 V2까지
            if (D[V2] == 987654321)
                V1V2Flag = 1;
            else {
                V1V2 = D[V2];
            }
        }


    }

    public static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}