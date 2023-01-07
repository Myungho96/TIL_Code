import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isInA1(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j]);
                }
                if (isInA2(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3]);
                }
                if (isInB(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1]);
                }
                if (isInC1(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j + 1]);
                }
                if (isInC2(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j]);
                }
                if (isInC3(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1]);
                }
                if (isInC4(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i - 1][j + 2]);
                }
                if (isInC5(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j - 1]);
                }
                if (isInC6(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2] + arr[i + 1][j]);
                }
                if (isInC7(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 2][j]);
                }
                if (isInC8(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2]);
                }
                if (isInD1(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1]);
                }
                if (isInD2(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i - 1][j + 1] + arr[i - 1][j + 2]);
                }
                if (isInD3(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i + 2][j - 1]);
                }
                if (isInD4(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2]);
                }
                if (isInE1(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j + 1]);
                }
                if (isInE2(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1]);
                }
                if (isInE3(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j - 1]);
                }
                if (isInE4(i, j)) {
                    max = Math.max(max, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i - 1][j + 1]);
                }

            }
        }
    }

    private static boolean isInE1(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 2, nc) && isIn(nr + 1, nc + 1);
    }

    private static boolean isInE2(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2) && isIn(nr + 1, nc + 1);
    }

    private static boolean isInE3(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 2, nc) && isIn(nr + 1, nc - 1);
    }

    private static boolean isInE4(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2) && isIn(nr - 1, nc + 1);
    }

    private static boolean isInD1(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 1, nc + 1) && isIn(nr + 2, nc + 1);
    }

    private static boolean isInD2(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr - 1, nc + 1) && isIn(nr - 1, nc + 2);
    }

    private static boolean isInD3(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 1, nc - 1) && isIn(nr + 2, nc - 1);
    }

    private static boolean isInD4(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr + 1, nc + 1) && isIn(nr + 1, nc + 2);
    }

    private static boolean isInC1(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 2, nc) && isIn(nr + 2, nc + 1);
    }

    private static boolean isInC2(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2);
    }

    private static boolean isInC3(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr + 1, nc + 1) && isIn(nr + 2, nc + 1);
    }

    private static boolean isInC4(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2) && isIn(nr - 1, nc + 2);
    }

    private static boolean isInC5(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 2, nc) && isIn(nr + 2, nc - 1);
    }

    private static boolean isInC6(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 1, nc + 1) && isIn(nr + 1, nc + 2);
    }

    private static boolean isInC7(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr + 1, nc) && isIn(nr + 2, nc);
    }

    private static boolean isInC8(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2) && isIn(nr + 1, nc + 2);
    }

    private static boolean isInB(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr, nc + 1) && isIn(nr + 1, nc + 1);
    }

    private static boolean isInA1(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr + 1, nc) && isIn(nr + 2, nc) && isIn(nr + 3, nc);
    }

    private static boolean isInA2(int nr, int nc) {
        return isIn(nr, nc) && isIn(nr, nc + 1) && isIn(nr, nc + 2) && isIn(nr, nc + 3);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < M;
    }
}
