import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String args[]) throws IOException{
        String []temp = br.readLine().split(" ");
        int m = Integer.parseInt(temp[0]);
        int n = Integer.parseInt(temp[1]);
        int [][]arr = new int[m][m];
        for(int i=0;i<m;i++)
            for(int j=0;j<m;j++){
                arr[i][j] = 1;
            }

        for(int t=0;t<n;t++){
            int [][]increase = new int[m][m];
            temp = br.readLine().split(" ");
            int idx = 0;
            Map<Integer,Integer> map = new HashMap<>();
            map.put(0,Integer.parseInt(temp[0]));
            map.put(1,Integer.parseInt(temp[1]));
            map.put(2,Integer.parseInt(temp[2]));
            int cnt = 0;
            if(map.get(cnt) == 0)
                cnt++;
            for(int i=m-1; i>=0;i--){
                if(map.get(cnt) == 0)
                    cnt++;
                arr[i][0] += cnt;
                increase[i][0] = cnt;
                map.put(cnt,map.get(cnt)-1);
            }
            for(int i=1;i<m;i++){
                if(map.get(cnt) == 0)
                    cnt++;
                arr[0][i] += cnt;
                increase[0][i] = cnt;
                map.put(cnt,map.get(cnt)-1);
            }
            for(int i=1;i<m;i++){
                for(int j=1;j<m;j++){
                    increase[i][j] = Math.max(increase[i-1][j-1],Math.max(increase[i-1][j],increase[i][j-1]));
                    arr[i][j] += increase[i][j];
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                bw.write(arr[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }
}