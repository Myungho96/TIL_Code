import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 987654321;
    public static void main(String args[]) throws IOException{
      String str = br.readLine();
      int n = Integer.parseInt(str);
      Result = Math.abs(n-100);
      int len = str.length();
      int m = Integer.parseInt(br.readLine());
      boolean []arr= new boolean[10];
      if(m>0){
          //고장난 버튼이 있는 경우
          String []temp = br.readLine().split(" ");
          for(int i=0;i<m;i++){
              arr[Integer.parseInt(temp[i])] = true;
          }
      }
      if(n==100)
         bw.write("0\n");
      else{
          dfs(0,len,str,"",arr);
          bw.write(Result+"\n");
      }
      
      
      bw.flush();
      bw.close();
      
      
    }
    static void dfs(int cnt, int len, String str,String make, boolean []arr){
        //백트래킹 하기
        
        if(cnt == len+1){
            //버튼 누르는 갯수 계산
            // System.out.println(make);
            make = String.valueOf(Integer.parseInt(make));
            Result = Math.min(Result, make.length()+Math.abs(Integer.parseInt(make)-Integer.parseInt(str)));
            return;
        }
        if(!make.equals("")){
            make = String.valueOf(Integer.parseInt(make));
            Result = Math.min(Result, make.length()+Math.abs(Integer.parseInt(make)-Integer.parseInt(str)));
        }

        int min = 100;
        int minIdx = -1;
        for(int i=0;i<=9;i++){
            //abs 시용해서 최솟값 구하기
            if(!arr[i]){
               dfs(cnt+1,len, str, make+i,arr);
            }
        }
        // dfs(cnt+1,len, str, make+minIdx,arr);
    }
}