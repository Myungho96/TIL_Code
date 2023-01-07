import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, M, K, min = Integer.MAX_VALUE;
	public static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	public static int[][] horse = { { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 },
			{ -2, 1 } };
	public static boolean[][][] visited;
	public static int[][] arr;
	public static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M][K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		bfs(0, 0, 0, 0);
		if(min==Integer.MAX_VALUE)
			min=-1;
		bw.write(min+"\n");
		bw.flush();
		bw.close();

	}

	private static void bfs(int r, int c, int cnt, int move) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { r, c, 0, 0 });
		visited[r][c][0] = true;
		while (!queue.isEmpty()) {
			int[] monkey = queue.poll();
			if (monkey[0] == N - 1 && monkey[1] == M - 1) {
				min = Math.min(min, monkey[3]);
				return;
			}

			if (monkey[2] < K) {// 8방탐색 수행
				for (int h = 0; h < 8; h++) {
					int nr = monkey[0] + horse[h][0];
					int nc = monkey[1] + horse[h][1];
					if (isIn(nr, nc) && !visited[nr][nc][monkey[2]+1] && arr[nr][nc] != 1) {
						visited[nr][nc][monkey[2]+1] = true;
						queue.offer(new int[] { nr, nc, monkey[2] + 1, monkey[3] + 1 });
					}

				}
			}
			for (int d = 0; d < 4; d++) {
				int nr = monkey[0] + deltas[d][0];
				int nc = monkey[1] + deltas[d][1];
				if (isIn(nr, nc) && !visited[nr][nc][monkey[2]] && arr[nr][nc] != 1) {
					visited[nr][nc][monkey[2]] = true;
					queue.offer(new int[] { nr, nc, monkey[2], monkey[3] + 1 });
				}
			}
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}

}
