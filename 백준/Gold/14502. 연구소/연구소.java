import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] arr;
    static List<int[]> blank;
    static List<int[]> virus;
    static boolean[][] visited;
    static int[][] deltas = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        blank = new ArrayList<>();
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
                if (arr[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }
        //벽 3개를 임의로 세운 뒤 바이러스 퍼지는거 시뮬레이션
        for (int i = 0; i < blank.size(); i++) {
            arr[blank.get(i)[0]][blank.get(i)[1]] = 1;
            for (int j = i + 1; j < blank.size(); j++) {
                arr[blank.get(j)[0]][blank.get(j)[1]] = 1;
                for (int k = j + 1; k < blank.size(); k++) {
                    arr[blank.get(k)[0]][blank.get(k)[1]] = 1;
                    int[][] copyArr = copy();
                    bfs(copyArr);
                    max = Math.max(max, cntSafePlace(copyArr));
                    arr[blank.get(k)[0]][blank.get(k)[1]] = 0;
                }
                arr[blank.get(j)[0]][blank.get(j)[1]] = 0;
            }
            arr[blank.get(i)[0]][blank.get(i)[1]] = 0;
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static int[][] copy() {
        int[][] copyArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyArr[i][j] = arr[i][j];
            }
        }
        return copyArr;
    }

    private static int cntSafePlace(int[][] copyArr) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyArr[i][j] == 0)
                    sum++;
            }
        }

        return sum;
    }

    private static void bfs(int[][] copyArr) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][M];
        for (int i = 0; i < virus.size(); i++) {
            visited[virus.get(i)[0]][virus.get(i)[1]] = true;
            queue.offer(virus.get(i));
        }
        while (!queue.isEmpty()) {
            int[] V = queue.poll();
            copyArr[V[0]][V[1]] = 2;
            for (int d = 0; d < 4; d++) {
                int nr = V[0] + deltas[d][0];
                int nc = V[1] + deltas[d][1];
                if (isIn(nr, nc) && !visited[nr][nc] && copyArr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < M;
    }
}
