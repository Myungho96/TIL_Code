import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp;
        boolean[] visited = new boolean[12];
        int visitedCnt = 0;
        char[][] arr = new char[5][9];
        for (int i = 0; i < 5; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = temp[j].charAt(0);
                if (arr[i][j] >= 'A' && arr[i][j] <= 'Z') {
                    visited[arr[i][j] - 'A'] = true;
                    visitedCnt++;
                }
            }
        }
        if (visitedCnt == 12) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(arr[i][j]);
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
            return;
        }

        //백트래킹이 이슈네; 순열로 줄세워야할거같은데 일단
        permu(0, 12 - visitedCnt, new char[12 - visitedCnt], arr, visited);


    }

    static void permu(int cnt, int end, char[] temp, char[][] arr, boolean[] visited) throws IOException {
        if (cnt == end) {
            //기존 표에서 빈칸 주어진 순서대로 채우기
            int idx = 0;
            char[][] tempArr = new char[5][9];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (arr[i][j] == 'x') {
                        tempArr[i][j] = temp[idx++];
                    } else {
                        tempArr[i][j] = arr[i][j];
                    }
                }
            }
            if (isMagicStar(tempArr)) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        bw.write(tempArr[i][j]);
                    }
                    bw.write("\n");
                }
                bw.flush();
                bw.close();
                System.exit(0);
            }
            return;
        }


        for (int i = 0; i < 12; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[cnt] = (char) (i + 'A');
                permu(cnt + 1, end, temp, arr, visited);
                visited[i] = false;
            }
        }
    }

    static boolean isMagicStar(char[][] tempArr) {
        int sumA = 0;
        int sumB = 0;
        int sumC = 0;
        int sumD = 0;
        int sumE = 0;
        int sumF = 0;
        for (int i = 0; i < 4; i++) {
            sumA += tempArr[i][4 - i] - 'A';
            sumB += tempArr[i][4 + i] - 'A';
            sumC += tempArr[4 - i][4 - i] - 'A';
            sumD += tempArr[4 - i][4 + i] - 'A';
            sumE += tempArr[1][1 + 2 * i] - 'A';
            sumF += tempArr[3][1 + 2 * i] - 'A';
        }

        return sumA == 22 && sumB == 22 && sumC == 22 && sumD == 22 && sumE == 22 && sumF == 22;

    }

}