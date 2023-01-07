import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr, stack, copyMap;
    public static int[] start, end;
    public static boolean []visited;
    public static int N, M, T, rot = 0, min = Integer.MAX_VALUE;
    ;

    public static void main(String[] args) throws IOException {
        String[] tempString = br.readLine().split(" ");
        N = Integer.parseInt(tempString[0]);
        M = Integer.parseInt(tempString[1]);
        T = Integer.parseInt(tempString[2]);
        int[] result = new int[T];
        visited = new boolean[T];
        stack = new int[T][3];
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempString = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(tempString[j]);
            }
        }
        for (int i = 0; i < T; i++) {
            tempString = br.readLine().split(" ");
            int r = Integer.parseInt(tempString[0]);
            int c = Integer.parseInt(tempString[1]);
            int size = Integer.parseInt(tempString[2]);
            stack[i][0] = r;
            stack[i][1] = c;
            stack[i][2] = size;
        }
        permutation(0,result);
        bw.write(min + "\n");

        bw.flush();
        bw.close();
    }

    public static void permutation(int cnt, int[] result) {
        if (cnt == T) {
            copyMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j] = arr[i][j];
                }
            }
            for (int i = 0; i < result.length; i++) {
                Rotation(result[i]-1);
            }
            isMin(copyMap);
            return;

        }

        for (int i = 0; i < T; i++) {
            if(!visited[i]){
                visited[i] = true;
                result[cnt] = i+1;
                permutation(cnt+1,result);
                visited[i] = false;
            }
        }
    }

    public static void Rotation(int k) {
        start = new int[]{stack[k][0] - stack[k][2] - 1, stack[k][1] - stack[k][2] - 1};
        end = new int[]{stack[k][0] + stack[k][2] - 1, stack[k][1] + stack[k][2] - 1};
        int R = end[0] - start[0];
        int C = end[1] - start[1];
        int tempN = R + 1;
        int tempM = C + 1;
        rot = Math.min(C, R) / 2;
        for (int i = 0; i < rot; i++) {//테스트를 위해 rot대신 1 넣음.
            tempN -= 2;
            tempM -= 2;

            int lastNum = copyMap[start[0] + i][start[1] + i];
            int saveNum;
            for (int j = 0; j <= tempM; j++) {//윗변부터 진행하자.
                saveNum = copyMap[i + start[0]][i + j + start[1] + 1];
                copyMap[i + start[0]][i + j + start[1] + 1] = lastNum;
                lastNum = saveNum;
            }
            for (int j = 0; j <= tempN; j++) {//오른쪽면 위에서 아래로

                saveNum = copyMap[i + j + 1 + start[0]][end[1] - i];
                copyMap[i + j + 1 + start[0]][end[1] - i] = lastNum;
                lastNum = saveNum;
            }
            for (int j = 0; j <= tempM; j++) {//아래면 왼쪽부터 오른쪽으로
                saveNum = copyMap[end[0] - i][end[1] - j - i - 1];
                copyMap[end[0] - i][end[1] - j - i - 1] = lastNum;
                lastNum = saveNum;
            }
            for (int j = 0; j <= tempN; j++) {//왼쪽면 위에서 아래로
                saveNum = copyMap[end[0] - i - j - 1][start[1] + i];
                copyMap[end[0] - i - j - 1][start[1] + i] = lastNum;
                lastNum = saveNum;

            }
        }
    }


    public static void isMin(int [][] copyMap) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += copyMap[i][j];
            }
            min = Math.min(min, sum);
        }
    }


}