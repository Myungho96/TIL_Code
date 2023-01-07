import java.util.*;
import java.io.*;
import java.lang.*;

//21212	240
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
//        for (int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(arr[i]));
//        }

        Calculate(0, 0, N);
        bw.flush();
        bw.close();

    }

    public static void Calculate(int r, int c, int size) throws IOException {
        int check;
        int flag;
        if (size == 1) {//1개 남았으면 그냥 출력
            bw.write(Integer.toString(arr[r][c]));
            return;
        }
        if(size==N){//처음에 전체 탐색
            check = arr[r][c];
            flag = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (check != arr[i][j]) {//합칠 수 없으므로 재귀해준다.
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) break;
            }
            if (flag == 0) {
                bw.write(Integer.toString(check));
                return;
            }
        }
        //사이즈가 2 이상이면 분할정복 해보자.
        bw.write("(");
        //4분할 해서 범위별로 맞는지 체크해주자.
        //왼쪽 상단
        check = arr[r][c];
        flag = 0;
        for (int i = r; i < r + size / 2; i++) {
            for (int j = c; j < c + size / 2; j++) {
                if (check != arr[i][j]) {//합칠 수 없으므로 재귀해준다.
                    Calculate(r, c, size / 2);
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }
        if (flag == 0) {
            bw.write(Integer.toString(check));
        }
        //오른쪽 상단
        check = arr[r][c + size / 2];
        flag = 0;
        for (int i = r; i < r + size / 2; i++) {
            for (int j = c + size / 2; j < c + size; j++) {
                if (check != arr[i][j]) {//합칠 수 없으므로 재귀해준다.
                    Calculate(r, c + size / 2, size / 2);
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }
        if (flag == 0) {
            bw.write(Integer.toString(check));
        }
        //왼쪽 하단
        check = arr[r + size / 2][c];
        flag = 0;
        for (int i = r + size / 2; i < r + size; i++) {
            for (int j = c; j < c + size / 2; j++) {
                if (check != arr[i][j]) {//합칠 수 없으므로 재귀해준다.
                    Calculate(r + size / 2, c, size / 2);
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }
        if (flag == 0) {
            bw.write(Integer.toString(check));
        }
        //오른쪽 하단
        check = arr[r + size / 2][c + size / 2];
        flag = 0;
        for (int i = r + size / 2; i < r + size; i++) {
            for (int j = c + size / 2; j < c + size; j++) {
                if (check != arr[i][j]) {//합칠 수 없으므로 재귀해준다.
                    Calculate(r + size / 2, c + size / 2, size / 2);
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }
        if (flag == 0) {
            bw.write(Integer.toString(check));
        }


        bw.write(")");

    }


}