

import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, D, max = Integer.MIN_VALUE;
    public static int[][] arr;
    public static int[][] copyArr;
    public static boolean[] visited;
    public static StringTokenizer st;
    public static List<int[]> list = new ArrayList<>();
    public static List<int[]> list2 = new ArrayList<>();
    public static int[][] deltas = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

    public static void main(String[] args) throws NumberFormatException, IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        copyArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
        comb(0, 0, new int[3]);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void comb(int start, int cnt, int[] numbers) {// 조합 잘 실행되는 것 확인.
        if (cnt == 3) {
//			System.out.println(Arrays.toString(numbers));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyArr[i][j] = arr[i][j];
                }
            }
            game(numbers);
            return;
        }
        for (int i = start; i < M; i++) {
            numbers[cnt] = i;
            comb(i + 1, cnt + 1, numbers);
        }

    }

    static void game(int[] archers) {
        int dead = 0;
        Unit[] targets = new Unit[3];
        for (int ar = N - 1; ar >= 0; ar--) {
            for (int i = 0; i < 3; i++) {
                targets[i] = getTarget(ar, archers[i]);
            }
            for (Unit target : targets) {
                if (target != null && copyArr[target.r][target.c] == 1) {
                    dead++;
                    copyArr[target.r][target.c] = 0;
                }
            }
        }
        max = Math.max(max, dead);
    }

    static Unit getTarget(int ar, int ac) {
        Unit unit = null;
        Queue<Unit> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new Unit(ar, ac));
        visited[ar][ac] = true;
        int range = 1;
        outer: while (!q.isEmpty() && range <= D) {

            int size = q.size();
            //System.out.println(size);
            while (size-- > 0) {
                Unit head = q.poll();
                if (copyArr[head.r][head.c] == 1) {
                    unit = head;
                    break outer;
                }
                for (int d = 0; d < deltas.length; d++) {
                    int nr = head.r + deltas[d][0];
                    int nc = head.c + deltas[d][1];
                    if (isIn(nr, nc) && !visited[nr][nc]) {
                        q.offer(new Unit(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            range++;
        }
        return unit;
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    static class Unit {
        int r, c;

        public Unit(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

    }

}