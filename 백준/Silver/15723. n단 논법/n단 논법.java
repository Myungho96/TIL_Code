import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Integer> []list;
    static int [][]arr;
    static int N,M;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        list = new List[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
        }
        char []temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().toCharArray();
            list[temp[0]-'a'].add(temp[temp.length-1]-'a');
        }
        M = Integer.parseInt(br.readLine());
        arr = new int[M][2];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().toCharArray();
            if(bfs(temp[0]-'a',temp[temp.length-1]-'a') == 1){
                bw.write("T\n");

            }else{
                bw.write("F\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static int bfs(int start, int end) {
        int result = 0;
        boolean []visited = new boolean[26];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < list[start].size(); i++) {
            queue.offer(list[start].get(i));
        }
        visited[start] = true;
        while (!queue.isEmpty()){
            int node = queue.poll();
            if(node==end){
                result=1;
                break;
            }
            for (int i = 0; i < list[node].size(); i++) {
                if(!visited[list[node].get(i)]){
                    visited[list[node].get(i)] = true;
                    queue.offer(list[node].get(i));
                }
            }
        }
        return result;
    }
}
