import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] arr = new boolean[1000001];
    static int[] result = new int[1000001];

    public static void main(String[] args) throws IOException {
        //아리스토테네스의 체로 뭘로 나눠지는지를 다 기재하고
        //두개씩 비교해서, 값이 같으면 더 큰수가 뭔지를 파악하면 될듯
        //1인경우는 무조건 승점만 하면 됨. 예외처리 하기

        //입력 받기
        int n = Integer.parseInt(br.readLine());
        int[] nodes = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nodes[i] = Integer.parseInt(temp[i]);
            arr[nodes[i]] = true;
        }

        for (int node : nodes) {
            int num;
            int idx = 2;
            while (node * idx <= 1000000) {
                num = node * idx;
                idx++;
                if (arr[num]) {
                    result[node]++;
                    result[num]--;
                }

            }

        }

        for (int i = 0; i < n; i++) {
            bw.write(result[nodes[i]] + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();

    }
}