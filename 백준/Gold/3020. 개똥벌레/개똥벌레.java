import java.lang.*;
import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int H = Integer.parseInt(temp[1]);
        int[] arr1 = new int[N / 2];
        int[] arr2 = new int[N / 2];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                arr1[i / 2] = Integer.parseInt(br.readLine());
            else
                arr2[i / 2] = H - Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int result = 987654321;
        int cnt = 0;
        for(int i=1;i<=H;i++){

//            int num1 =  Arrays.binarySearch(arr1,i);
            int num1 =  binarySearch(arr1,i);
            if(num1<0){
                num1 = -(num1+1);
            }
//            int num2 = Arrays.binarySearch(arr2,i-1);
            int num2 = binarySearch(arr2,i);
            if(num2<0){
                num2=-(num2+1);
            }
            int tempResult = (N/2-num1)+num2;
            if(result==tempResult){
                cnt++;
            }else if(result>tempResult){
                result = tempResult;
                cnt=1;
            }
        }
        bw.write(result+" "+cnt+"\n");
        bw.flush();
        bw.close();

    }

    public static int binarySearch(int [] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (target <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}

