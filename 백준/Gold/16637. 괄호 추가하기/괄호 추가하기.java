import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        int num = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        List<Integer> temp1 = new LinkedList<>();
        List<String> temp2 = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            if (arr[i] - '0' >= 0 && arr[i] - '0' <= 9) {
                temp1.add(arr[i] - '0');
            } else
                temp2.add(String.valueOf(arr[i]));
        }

        //괄호 만들어줘보기
        dfs(0, temp1, temp2, new boolean[temp2.size()]);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt, List<Integer> temp1, List<String> temp2, boolean[] visited) {
        if (cnt == visited.length) {
            //괄호부터 계산하기
            int idx=0;
            List<Integer> temp3 = new LinkedList<>();
            temp3.addAll(temp1);
            List<String> temp4 = new LinkedList<>();
            temp4.addAll(temp2);
            for (int i = 0; i < cnt; i++) {
                if (visited[i]) {
                    String str = temp4.get(i);
                    int a = temp3.get(idx);
                    int b = temp3.get(idx + 1);
                    temp3.remove(idx);
                    temp3.remove(idx);
                    switch (str) {
                        case "+":
                            temp3.add(idx, a + b);
                            break;
                        case "-":
                            temp3.add(idx, a - b);
                            break;
                        case "*":
                            temp3.add(idx, a * b);
                            break;
                    }
                }else
                    idx++;
            }

            //남은거 순차 계산

            int sum = temp3.get(0);
            temp3.remove(0);

            for (int i = 0; i < cnt; i++) {
                if (!visited[i]) {
                    String str = temp4.get(i);
                    int a = temp3.get(0);
                    temp3.remove(0);
                    switch (str) {
                        case "+":
                            sum+=a;
                            break;
                        case "-":
                            sum-=a;
                            break;
                        case "*":
                            sum*=a;
                            break;
                    }
                }
            }
            Result = Math.max(sum, Result);
            return;
        }
        if(cnt!=0 && visited[cnt-1]){
            dfs(cnt + 1, temp1, temp2, visited);
        }else{
            visited[cnt] = true;
            dfs(cnt + 1, temp1, temp2, visited);

            visited[cnt] = false;
            dfs(cnt + 1, temp1, temp2, visited);
        }

    }


}