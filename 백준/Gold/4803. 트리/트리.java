import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static List<Integer>[] list;
    public static boolean[] visited;
    public static boolean[] checkCycle;
    public static int N, M, cnt = 0;
    public static int T = 0;
    public static boolean flag = false;

    public static void main(String[] args) throws IOException {
        while (true) {
            T++;
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            if (N == 0 && M == 0) {
                bw.flush();
                bw.close();
                return;
            }
            list = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            visited = new boolean[N + 1];//1부터 N까지 쓰자.
            cnt = 0;
            for (int i = 0; i < M; i++) {
                temp = br.readLine().split(" ");
                list[Integer.parseInt(temp[0])].add(Integer.parseInt(temp[1]));
                list[Integer.parseInt(temp[1])].add(Integer.parseInt(temp[0]));

            }
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    if(dfs(i,0))
                        cnt++;
                }
            }
            switch (cnt) {
                case 0:
                    bw.write("Case " + T + ": No trees." + "\n");
                    break;
                case 1:
                    bw.write("Case " + T + ": There is one tree." + "\n");
                    break;
                default:
                    bw.write("Case " + T + ": A forest of " + cnt + " trees." + "\n");
            }


        }


    }

    public static boolean dfs(int Node, int Before) {
        visited[Node] = true;
        for (int i = 0; i < list[Node].size(); i++) {
            if(list[Node].get(i) == Before)
                continue;//들렀던거를 들릴 필요는 없다.
            else if (visited[list[Node].get(i)]) {//방문하려고 하는 곳이 이미 방문 했다면 사이클이다.
                return false;
            } else if (!dfs(list[Node].get(i),Node)) {//하나가 false면 이렇게 해야 재귀탈출이 가능하다.
                return false;
            }
        }
        return true;
    }

}
