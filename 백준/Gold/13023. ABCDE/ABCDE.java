import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] visited;
    public static int Result = 0;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        visited = new boolean[N];
        List<Integer>[] lists = new List[N];
        for (int i = 0; i < N; i++)
            lists[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            lists[a].add(b);
            lists[b].add(a);
        }
        for(int i=0;i<N;i++){
            if(Result==1)
                break;
            visited[i] = true;
            dfs(0, i, lists);
            visited[i] = false;
        }

        bw.write(Result + "\n");
        bw.flush();
        bw.close();

    }

    public static void dfs(int cnt, int start, List<Integer>[] lists) {
        int N = visited.length;
        if (cnt == 4) {
//            int temp = 0;
//            for (int i = 0; i < N; i++)
//                if (visited[i])
//                    temp++;
//            if (temp >= 4)
                Result = 1;
            return;
        }
        for (int num : lists[start]) {
            if(!visited[num]){
                visited[num]=true;
                dfs(cnt+1,num,lists);
                visited[num]=false;
            }
        }
    }

}

