import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++)
            arr[i] = i;

        int[] money = new int[n + 1];
        temp = br.readLine().split(" ");
        for (int i = 1; i <= n; i++)
            money[i] = Integer.parseInt(temp[i - 1]);

        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            if (find(Integer.parseInt(temp[0]), arr) != find(Integer.parseInt(temp[1]), arr)) {
                union(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), arr, money);
            }
        }
        int answer = 0;
        boolean []visited = new boolean[n+1];
        for(int num : arr){
            if(!visited[find(num,arr)]){
                answer+=money[find(num,arr)];
                visited[find(num,arr)] = true;
                visited[num] = true;
            }else{
                visited[num] = true;
            }
        }
        if(k < answer)
            bw.write("Oh no\n");
        else
            bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }

    static int find(int n, int[] arr) {
        if (arr[n] == n)
            return n;
        return find(arr[n], arr);
    }

    static void union(int n, int m, int[] arr, int[] money) {
        if (money[find(n, arr)] < money[find(m, arr)]) {
            arr[find(m, arr)] = find(n, arr);
        } else {
            arr[find(n, arr)] = find(m, arr);
        }
    }


}