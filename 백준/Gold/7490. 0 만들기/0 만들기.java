import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String args[]) throws IOException {
      int n = Integer.parseInt(br.readLine());
      int []arr = new int[n];
      for(int i=0;i<n;i++){
          arr[i] = Integer.parseInt(br.readLine());
      }
      
      //브루트포스 문제같음. 3^10
      for(int i=0;i<n;i++){
          int []temp = new int[arr[i]];
          for(int j=1;j<=arr[i];j++){
              temp[j-1] = j;
          }
       
          permu(0,arr[i],temp, new int[arr[i]-1]);
          bw.write("\n");
      }
      bw.flush();
      bw.close();
      
    }
    
    static void permu(int cnt,int n, int []arr, int []check) throws IOException{
        if(cnt == n-1){
            //수식 계산해주기
            int sum = arr[0];
            int idx = 0;
            int idxTemp = 1;
            if(check[idx]==1){
                while(true){
                    if(idx<n-1 && check[idx]==1){
                        sum*=10;
                        sum+=arr[idxTemp++];
                        idx++;
                    }else
                        break;
                }
            }
            while(idx<n-1){
                int temp;
                switch(check[idx++]){
                    case 2:
                        temp = arr[idxTemp++];
                        while(true){
                            if(idx<n-1 && check[idx]==1){
                                temp*=10;
                                temp+=arr[idxTemp++];
                                idx++;
                            }else
                                break;
                        }
                        sum+=temp;
                        break;
                        
                    case 3:
                        temp = arr[idxTemp++];
                        while(true){
                            if(idx<n-1 && check[idx]==1){
                                temp*=10;
                                temp+=arr[idxTemp++];
                                idx++;
                            }else
                                break;
                        }
                        sum-=temp;
                        break;
                }    
            }
            
            
            
            //합이 0이 되는지 체크
            if(sum == 0){
                bw.write("1");
                for(int i=0;i<n-1;i++){
                    
                    if(check[i]==1){
                         bw.write(" ");
                    }else if(check[i]==2){
                         bw.write("+");
                    }else{
                        bw.write("-");
                    }
                    bw.write(String.valueOf(i+2));
                }
                bw.write("\n");
            }
            return;
        }
        
        for(int i=1;i<=3;i++){
            check[cnt] = i;
            permu(cnt+1,n,arr,check);
        }
    }
}