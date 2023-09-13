import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String args[]) throws IOException{
      //투포인터 문제인듯
      int n = Integer.parseInt(br.readLine());
      int []arr = new int[n];
      String []temp = br.readLine().split(" ");
      for(int i=0;i<n;i++){
          arr[i] = Integer.parseInt(temp[i]);
      }
      
      int start = 0;
      int end = n-1;
      int min = Integer.MAX_VALUE;
      int minNum1=0, minNum2=0;
      
      while(start<end){
          if(min>=Math.abs(arr[start]+arr[end])){
              min = Math.abs(arr[start]+arr[end]);
              minNum1= start;
              minNum2 = end;
          }
          if(arr[start]+arr[end]>0)
                end--;
              else
                start++;
      }
  
      bw.write(arr[minNum1] + " "+arr[minNum2]+"\n");
      bw.flush();
      bw.close();
    }
}