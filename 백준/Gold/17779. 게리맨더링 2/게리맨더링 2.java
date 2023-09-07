import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 987654321;
    static int totalAll = 0;
    public static void main(String args[]) throws IOException{
      int n = Integer.parseInt(br.readLine());
      int [][]arr = new int[n][n];
      for(int i=0;i<n;i++){
          String []temp = br.readLine().split(" ");
          for(int j=0;j<n;j++){
              arr[i][j] = Integer.parseInt(temp[j]);
              totalAll+=arr[i][j];
          }
      }
      
      for (int x = 0; x < n; x++) {
        for (int y = 0; y < n; y++) {
            for (int d1 = 1; d1 < n; d1++) {
                for (int d2 = 1; d2 < n; d2++) {
                    if (x + d1 + d2 >= n) continue;
                    if (y - d1 < 0 || y + d2 >= n) continue;
                        solve(n, x, y, d1, d2, arr);
                    }
                }
            }
        }
        bw.write(Result+"\n");
        bw.flush();
        bw.close();
    }
    
    public static void solve(int n, int x, int y, int d1, int d2, int [][]arr){
        boolean[][] border = new boolean[n][n];

        // 경계선 세팅
        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }
        
        
        int []total = new int[5];
        //1번 선거구
        for(int i=0; i<x+d1;i++)
            for(int j=0; j<=y; j++){
                if (border[i][j]) break;
                total[0]+=arr[i][j];
            }
                
        //2번 선거구
        for(int i=0; i<=x+d2;i++)
            for (int j = n - 1; j > y; j--){
                if (border[i][j]) break;
                total[1]+=arr[i][j];
            }
                
        //3번 선거구
        for(int i=x+d1; i<n;i++)
            for(int j=0; j<y-d1+d2; j++){
                if (border[i][j]) break;
                total[2]+=arr[i][j];
            }
                
        //4번 선거구
        for(int i=x+d2+1; i<n;i++)
            for (int j = n - 1; j >= y - d1 + d2; j--){
                if (border[i][j]) break;
                total[3]+=arr[i][j];
            }
                
        //5번 선거구
        total[4] = totalAll - (total[0]+total[1]+total[2]+total[3]);
        //인구가 가장 많은 선거구와 아닌 선거구의 차이를 Result와 비교
        Arrays.sort(total);
        Result = Math.min(Result,total[4]-total[0]);
    }
}