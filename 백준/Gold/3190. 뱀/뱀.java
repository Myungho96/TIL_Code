import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] Deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int Result = 0;
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        String[] temp;
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            arr[Integer.parseInt(temp[0]) - 1][Integer.parseInt(temp[1]) - 1] = 1;
        }
        int time = 0;
        int dir = 0;
        List<int[]> lists = new LinkedList<>();
        lists.add(new int[]{0, 0});
        int cr = 0;
        int cc = 0;
        boolean flag = false;
        int change = Integer.parseInt(br.readLine());
 loop:       for (int i = 0; i < change; i++) {
            temp = br.readLine().split(" ");
            int targetTime = Integer.parseInt(temp[0]);
            while (time < targetTime) {
                time++;
                cr+=Deltas[dir][0];
                cc+=Deltas[dir][1];
                if(check(cr,cc,n,lists)){
                    flag = true;
                    bw.write(time+"\n");
                    break loop;
                }
                lists.add(new int[]{cr,cc});
                if(arr[cr][cc] == 0){
                    lists.remove(0);
                }else{
                    arr[cr][cc] = 0;
                }

            }
            //방향 바꾸기
            if(temp[1].equals("L")){
                dir = (dir+3)%4;
            }else{
                dir = (dir+1)%4;
            }
        }
        if(!flag){
            while (true) {
                time++;
                cr+=Deltas[dir][0];
                cc+=Deltas[dir][1];
                if(check(cr,cc,n,lists)){
                    flag = true;
                    bw.write(time+"\n");
                    break;
                }
                lists.add(new int[]{cr,cc});
                if(arr[cr][cc] == 0){
                    lists.remove(0);
                }else{
                    arr[cr][cc] = 0;
                }

            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
    public static boolean check(int cr, int cc,int n, List<int[]> lists) {
        if (cr < 0 || cc < 0 || cr >= n || cc >= n) {
            return true;
        }

        for (int i = 0; i < lists.size(); i++) {
            int[] t = lists.get(i);
            if (cr == t[0] && cc == t[1])
                return true;
        }
        return false;
    }

}


