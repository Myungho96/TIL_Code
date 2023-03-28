import java.io.*;
import java.util.*;

public class Main {
    static int [][]deltas = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M,H,Cnt,ZeroCnt,CurrentCnt;
    static int [][][]Arr;
    static List<Node> TomatoList;
    static class Node{
        int x,y,z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        TomatoList = new ArrayList<>();
        Cnt=0;
        ZeroCnt=0;
        CurrentCnt=0;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        Arr = new int[N][M][H];
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    Arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if(Arr[i][j][k]==1){
                        TomatoList.add(new Node(i,j,k));
                    } else if (Arr[i][j][k]==0) {
                        ZeroCnt++;
                    }
                }
            }
        }
        //저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
        if(ZeroCnt==0)
            bw.write("0\n");
        else {
            bfs();
            if(ZeroCnt==CurrentCnt)
                bw.write(Cnt+1+"\n");
            else
                bw.write("-1\n");

        }

        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean flag = false;
        boolean [][][]visited = new boolean[N][M][H];
        for (Node node:TomatoList) {
            queue.offer(new Node(node.x,node.y, node.z));
            visited[node.x][node.y][node.z] = true;
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                Node node = queue.poll();
                    
                for (int i = 0; i < 6; i++) {
                    int cx = node.x+deltas[i][0];
                    int cy = node.y+deltas[i][1];
                    int cz = node.z+deltas[i][2];
                    if(isIn(cx,cy,cz) && !visited[cx][cy][cz] && Arr[cx][cy][cz] == 0){
                        Arr[cx][cy][cz] = 1;
                        visited[cx][cy][cz] = true;
                        CurrentCnt++;
                        queue.offer(new Node(cx,cy,cz));
                    }
                }
                if(CurrentCnt==ZeroCnt){
                    flag=true;
                    break;
                }
            }
            if(flag)
                break;
            else
                Cnt++;
        }

    }

    private static boolean isIn(int cx, int cy, int cz) {
        return cx>=0 && cy>=0 && cz>=0 && cx<N && cy<M && cz<H;
    }
}