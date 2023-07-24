import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;

    public static void main(String[] args) throws IOException {
        //dp...?구현아닌거같은데
        //투포인터로 계산 후 이진탐색으로 있는지 체크?
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(arr);
        if (n == 1 || n == 2)
            bw.write("0\n");
        else {
            int start, end;
            for (int i = 0; i < n; i++) {
                start = 0;
                end = n - 1;
                while (start < end) {
                    if (arr[start] + arr[end] < arr[i])
                        start++;
                    else if(arr[start] + arr[end] > arr[i])
                        end--;
                    else{
                        if(start!=i && end!=i) {
                            Result++;
                            break;
                        }else if(start==i)
                            start++;
                        else
                            end--;
                    }
                }
            }

            bw.write(Result + "\n");
        }
        bw.flush();
        bw.close();
    }
}