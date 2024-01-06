import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    static int check(int num) {
        if (parents[num] == num)
            return num;
        return parents[num] = check(parents[num]);
    }

    static void union(int num1, int num2) {
        int parents1 = parents[num1];
        int parents2 = parents[num2];
        if (parents1 <= parents2) {
            parents[parents2] = parents1;
        } else {
            parents[parents1] = parents2;
        }
    }

    public static void main(String[] args) throws IOException {
        //사이클 체크 -> 유니온 파인드 기억하기
        //make, check, union 3개의 함수를 구현해야함

        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int result = 0;
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int num1 = Integer.parseInt(temp[0]);
            int num2 = Integer.parseInt(temp[1]);

            if (check(num1) == check(num2)) {
                result = i + 1;
                break;
            } else {
                union(num1, num2);
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }
}