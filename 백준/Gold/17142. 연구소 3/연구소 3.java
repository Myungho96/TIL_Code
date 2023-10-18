import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}, arr;

    static int result = 987654321, zeroCnt = 0, n;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        arr = new int[n][n];
        List<int[]> virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
                if (arr[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }
        if(zeroCnt == 0){
            bw.write("0\n");
        }else{
            combi(0, 0, m, virus, new int[m], new boolean[virus.size()]);
            bw.write(result==987654321? "-1" :result+"\n");
        }


        bw.flush();
        bw.close();

    }

    static void combi(int cnt, int start, int end, List<int[]> virus, int[] temp, boolean[] visited) {
        if (cnt == end) {
            bfs(virus, temp);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[cnt] = i;
                combi(cnt + 1, i + 1, end, virus, temp, visited);
                visited[i] = false;
            }
        }
    }

    static void bfs(List<int[]> virus, int[] temp) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean [][]visited = new boolean[n][n];
        for (int i : temp) {
            visited[virus.get(i)[0]][virus.get(i)[1]] = true;
            queue.add(virus.get(i));
        }
        int cnt=0;
        int time = 0;
        while (!queue.isEmpty()) {

            if(cnt>=zeroCnt){
                result = Math.min(result,time);
                return;
            }
            time++;
            int size = queue.size();
            while (size-- > 0) {
                int []node = queue.poll();
                //사방탐색
                for(int d=0;d<4;d++){
                    int cr = node[0] + deltas[d][0];
                    int cc = node[1] + deltas[d][1];

                    if(cr>=0 && cc>=0 && cr<n && cc<n && !visited[cr][cc] && arr[cr][cc] != 1){
                        visited[cr][cc] = true;
                        queue.offer(new int[]{cr,cc});
                        int gc = arr[cr][cc] == 0 ? cnt++ : cnt;
                    }
                }
            }
        }
    }


}