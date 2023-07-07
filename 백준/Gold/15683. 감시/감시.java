import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, Result = 987654321;
    public static int[][][] Deltas = {{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}, {{0, 1, 0, -1}, {1, 0, -1, 0}}, {{-1, 0, 0, 1}, {0, 1, 1, 0}, {1, 0, 0, -1}, {0, -1, -1, 0}}, {{0, -1, -1, 0, 0, 1}, {-1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, -1}, {1, 0, 0, -1, -1, 0}}, {{1, 0, 0, -1, -1, 0, 0, 1}}};


    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        int zeroCnt = 0;
        int[][] arr = new int[N][M];
        List<int[]> cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] > 0 && arr[i][j] != 6) {
                    cctvs.add(new int[]{i, j});
                } else if (arr[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        dfs(0, zeroCnt, arr, cctvs, new boolean[N][M]);
        bw.write(Result+"\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int cnt, int zeroCnt, int[][] arr, List<int[]> cctvs, boolean visited[][]) {
        if (cnt == cctvs.size()) {
            /*기저조건. zeroCnt의 최소 크기를 저장한다.*/
            Result = Math.min(zeroCnt, Result);
            return;
        }
        //cctv 별로 visited를 변경해서 dfs 변경
        int[] cctv = cctvs.get(cnt);
        for (int[] dir : Deltas[arr[cctv[0]][cctv[1]] - 1]) {
            boolean[][] tempVisited = new boolean[N][M];
            int tempZeroCnt = zeroCnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tempVisited[i][j] = visited[i][j];
                }
            }
            for (int i = 0; i < dir.length; i += 2) {
                //감시카메라 기준으로 특정 방향 모두 방문시키기
                int cr = cctv[0];
                int cc = cctv[1];
                while (true) {
                    cr += dir[i];
                    cc += dir[i + 1];
                    if(cr>=0 && cc>=0 && cr<N && cc <M){
                        if(arr[cr][cc]==0 && !tempVisited[cr][cc]){
                            tempVisited[cr][cc] = true;
                            tempZeroCnt--;
                        }else if(arr[cr][cc]==6)
                            break;
                    }else{
                        break;
                    }

                }

            }
            dfs(cnt + 1, tempZeroCnt, arr, cctvs, tempVisited);
        }
    }


}

