import java.io.*;
import java.util.*;

/*
9 1 2 5
9층짜리 엘리베이터, 디스플레이에는 1자리 수만 보인다. 최대 2개를 반전시킬 수 있으며 현재 5층에 있음
현재 5층에 있다는 의미 -> 5층에서 2개를 바꿔서 가능한 경우의 수를 구하여라

조건을 보면, 6자리 수까지 가능하며 P가 최대 42이므로 전체 수를 반전시킬 수 있다.


1. 2차원 배열을 이용해서 특정 수가 다른 수가 되기 위해서는 몇번의 반전이 필요한지 기록한다
2. 0~9까지 배열을 하나 만든 후, 층이 들어오면 분해해서 1의 자리 숫자부터 보면서 가능한지 여부 확인 후 가능하다면
cnt를 누적시켜주고 경우의 수(total)을 증가시켜준다.
3. 중간에 가능 여부 확인 때 가능하지 않다면 -1로 바꿔준다.
*/
public class Main {
    static int N, K, P, X;
    static int sum;
    static int[][] reverseNum;
    static String[] display = {"1110111",
            "0010010",
            "1011101",
            "1011011",
            "0111010",
            "1101011",
            "1101111",
            "1010010",
            "1111111",
            "1111011"};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        reverseNum = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                compareNum(i, j);
            }
        }
        sum = 0;
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);
        P = Integer.parseInt(temp[2]);
        X = Integer.parseInt(temp[3]);
        if (K * 7 <= P) {//그 자릿수는 뭐든지 만들 수 있는 상황
            bw.write(N - 1 + "\n");
        } else {//dfs 해야할듯? 조합해야하지 않을까 싶네
            //먼저 N을 자릿수에 맞게 만들어 줘야 한다.
            String floor = String.valueOf(X);
            while (floor.length() < K) {
                floor = "0" + floor;
            }
            //최대 층도 변환해보자
            String maxfloor = String.valueOf(N);
            while (maxfloor.length() < K) {
                maxfloor = "0" + maxfloor;
            }
//            System.out.println(maxfloor);
//            System.out.println(Integer.parseInt(maxfloor));
            dfs(floor, maxfloor, 0, "");
            bw.write(sum - 1 + "\n");
        }
        bw.flush();
        bw.close();


    }

    private static void dfs(String floor, String maxfloor, int cnt, String temp) {
        if (cnt == K) {//순열 완료, 대소관계 비교해야함
            int total = Integer.parseInt(temp);
            if (total != 0 && total <= Integer.parseInt(maxfloor)) {
                cal(floor, temp);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            dfs(floor, maxfloor, cnt + 1, temp + String.valueOf(i));

        }
    }

    private static void cal(String floor, String temp) {
        int acc = 0;
        for (int i = 0; i < floor.length(); i++) {
            acc += reverseNum[floor.charAt(i) - '0'][temp.charAt(i) - '0'];
        }
        if (acc <= P)
            sum++;
    }

    private static void compareNum(int i, int j) {
        int sum = 0;
        char[] arr1 = display[i].toCharArray();
        char[] arr2 = display[j].toCharArray();
        for (int k = 0; k < 7; k++) {
            if (arr1[k] != arr2[k])
                sum++;
        }
        reverseNum[i][j] = sum;
        reverseNum[j][i] = sum;
    }

}