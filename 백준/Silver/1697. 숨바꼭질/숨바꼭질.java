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

    private static void bfs(int start, int end, int cnt) throws IOException {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start,cnt});
        while (!queue.isEmpty()) {
            int []num = queue.poll();
            visited[num[0]] = true;
            if (num[0] == end) {
                bw.write(num[1] + "\n");
                return;
            }

            if (start < end) {
                if(isIn(2*num[0]) && !visited[2*num[0]]){
                    queue.offer(new int[]{2*num[0],num[1]+1});
                }
                if(isIn(num[0]+1) && !visited[num[0]+1]){
                    queue.offer(new int[]{num[0]+1,num[1]+1});
                }
                if(isIn(num[0]-1) && !visited[num[0]-1]){
                    queue.offer(new int[]{num[0]-1,num[1]+1});
                }
            } else {
                if(isIn(num[0]-1) && !visited[num[0]-1]){
                    queue.offer(new int[]{num[0]-1,num[1]+1});
                }
            }
        }


    }

    private static boolean isIn(int x) {
        return x >= 0 && x <= 100000;
    }

}