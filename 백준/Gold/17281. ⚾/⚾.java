import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0, n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][10];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j < 10; j++) {
                arr[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        permu(2, new int[10], arr, new boolean[10]);
        bw.write(Result+"\n");
        bw.flush();
        bw.close();

    }

    // 선수 순서 정함
    static void permu(int num, int[] tempArr, int[][] arr, boolean[] visited) {
        if (num == 10) {
            tempArr[4] = 1;
            int score = play(tempArr,arr);
            Result = Math.max(Result, score);
            return;
        }
        
        for (int i = 1; i < 10; i++) {
            if(i==4)
                continue;
            if (!visited[i]) {
                visited[i] = true;
                tempArr[i] = num;
                permu(num + 1, tempArr, arr, visited);
                visited[i] = false;
            }
        }
    }

    static int play(int[] tempArr, int[][] arr) {
        int sum = 0;
        int idx = 1;
        for (int i = 0; i < n; i++) {
            int cnt = 0; // 현재 이닝에서 얻는 점수
            int out = 0; // 현재 이닝에서 아웃의 수
            boolean[] base = new boolean[4]; // true : 해당 베이스에 선수가 있음, false : 해당 베이스에 선수가 없음

            // 3 아웃이 되기 전까지 반복
            while (out < 3) {
                switch (arr[i][tempArr[idx]]) {
                    // 아웃
                    case 0:
                        out++;
                        break;
                    // 1루타
                    case 1:
                        if (base[3]) {
                            cnt++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            base[3] = true;
                            base[2] = false;
                        }
                        if (base[1]) {
                            base[2] = true;
                        }
                        base[1] = true;
                        break;
                    // 2루타
                    case 2:
                        if (base[3]) {
                            cnt++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            cnt++;
                        }
                        if (base[1]) {
                            base[3] = true;
                            base[1] = false;
                        }
                        base[2] = true;
                        break;
                    // 3루타
                    case 3:
                        if (base[3]) {
                            cnt++;
                        }
                        if (base[2]) {
                            cnt++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            cnt++;
                            base[1] = false;
                        }
                        base[3] = true;
                        break;
                    // 홈런
                    case 4:
                        if (base[3]) {
                            cnt++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            cnt++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            cnt++;
                            base[1] = false;
                        }
                        cnt++;
                        break;
                }
                idx++;
                if (idx == 10) {
                    idx = 1;
                }
            }
            sum += cnt;
        }
        return sum;
    }
}