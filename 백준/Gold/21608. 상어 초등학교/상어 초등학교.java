import java.util.*;
import java.io.*;


public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [][]Deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String args[]) throws IOException
    {
        int n = Integer.parseInt(br.readLine());
        int [][]arr = new int[n+1][n+1];
        int [][]status = new int[n*n+1][4];
        List<int[]> lists = new ArrayList<>();
        for(int i=0;i<n*n;i++){
            String []temp = br.readLine().split(" ");
            lists.add(new int[]{Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3]),Integer.parseInt(temp[4])});
            status[Integer.parseInt(temp[0])][0] = Integer.parseInt(temp[1]);
            status[Integer.parseInt(temp[0])][1] = Integer.parseInt(temp[2]);
            status[Integer.parseInt(temp[0])][2] = Integer.parseInt(temp[3]);
            status[Integer.parseInt(temp[0])][3] = Integer.parseInt(temp[4]);
        }
        //1. 비어있는 칸들을 조사하면서 주변에 좋아하는 학생 수,빈칸 수 세기
        //2. 학생 수가 같다면 -> 빈칸수 더 많은곳
        //3. 2번도 같으면 행의 번호가 더 작은거, 그것도 같으면 열의의 번호가 작은것
        
        for(int []shark : lists){
            PriorityQueue<Node> pq = new PriorityQueue<>();
            //맵을 보면서 빈칸 있는지 체크!!!빈칸이면 계산해서 큐에 넣어주자
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(arr[i][j] != 0)
                        continue;
                    int fav = 0;
                    int blank = 0;
                    for(int d=0;d<4;d++){
                        int cr = i+Deltas[d][0];
                        int cc = j + Deltas[d][1];
                        if(cr>0 && cc>0 && cr<=n && cc<=n){
                            if(arr[cr][cc] == shark[1] || arr[cr][cc] == shark[2] ||arr[cr][cc] == shark[3] ||arr[cr][cc] == shark[4])
                                fav++;
                            else if(arr[cr][cc]==0)
                                blank++;
                        }
                    }
                    pq.offer(new Node(i,j,fav,blank));
                }
            }
            Node result = pq.poll();
            arr[result.r][result.c] = shark[0];
        }

        //만족도 구하기
        int sum = 0;
        for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(arr[i][j] == 0)
                        continue;
                    int fav = 0;
                    for(int d=0;d<4;d++){
                        int cr = i+Deltas[d][0];
                        int cc = j + Deltas[d][1];
                        if(cr>0 && cc>0 && cr<=n && cc<=n){
                            if(arr[cr][cc] == status[arr[i][j]][0] || arr[cr][cc] == status[arr[i][j]][1] ||arr[cr][cc] == status[arr[i][j]][2] ||arr[cr][cc] == status[arr[i][j]][3])
                                fav++;
                    
                        }
                    }
                    if(fav>=2)
                        sum+=Math.pow(10,fav-1);
                    else
                        sum+=fav;
                }
            }
        bw.write(sum+"\n");
        bw.flush();
        bw.close();

    }

    static class Node implements Comparable<Node>{
        int r,c,fav,blank;
        Node(int r, int c, int fav, int blank){
            this.r = r;
            this.c = c;
            this.fav = fav;
            this.blank = blank;
        }
        public int compareTo(Node o){
            if(this.fav == o.fav){
                if(this.blank == o.blank){
                    return this.r == o.r? Integer.compare(this.c,o.c):Integer.compare(this.r,o.r);
                }else
                    return Integer.compare(this.blank,o.blank)*-1;
            }else
                return Integer.compare(this.fav,o.fav)*-1;
        }
    }
}