import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result = 0;

    public static void main(String[] args) throws IOException {
        int num = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[num + 1];
        visited[0] = true;
        visited[1] = true;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1,0);
        for (int i = 2; i <= num; i++) {
            if (!visited[i]) {
                if (i == 2)
                    treeMap.put(i, i);
                else
                    treeMap.put(i, treeMap.lastEntry().getValue() + i);
                int cnt = 2;
                while (i * cnt <= num) {
                    visited[i * (cnt++)] = true;
                }
            }
        }
//        System.out.println(treeMap);
        int[] arr = new int[treeMap.size()];
        int idx = 0;
        for (int temp : treeMap.keySet()) {
            arr[idx++] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(treeMap.get(arr[j]) - treeMap.get(arr[i]) == num){
//                    System.out.println(arr[j]+" "+arr[i]);
                    Result++;
                }else if(treeMap.get(arr[j]) - treeMap.get(arr[i]) > num){
                    break;
                }
            }
        }

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }


}