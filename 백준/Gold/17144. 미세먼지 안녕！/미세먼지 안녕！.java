import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, M, T;
	public static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	public static int[][] arr;
	public static StringTokenizer st;
	public static List<int[]> dust;
	public static List<int[]> start;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		dust = new ArrayList<>();
		start = new ArrayList<>();
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0)
					dust.add(new int[] { i, j, arr[i][j] });
				if (arr[i][j] == -1)
					start.add(new int[] { i, j });
			}
		}
		bfs();
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		bw.flush();
		bw.close();
	}

	private static void bfs() throws IOException {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < dust.size(); i++) {
			queue.offer(dust.get(i));
		}
		int cnt = 0;
		while (true) {
			if (cnt == T) {
				int sum = 2;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						sum+=arr[i][j];
					}
				}
				bw.write(sum+"\n");
				return;
			}
			// 여기서 배열 탐색해서 큐에 dust 넣는 코드 필요할듯.
			if (cnt > 0) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (arr[i][j] > 0)
							queue.add(new int[] { i, j, arr[i][j] });
					}
				}
			}
			int size = queue.size();
			while (size-- > 0) {
				int[] microDust = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = microDust[0] + deltas[d][0];
					int nc = microDust[1] + deltas[d][1];
					if (isIn(nr, nc) && arr[nr][nc] != -1) {
						arr[microDust[0]][microDust[1]] -= microDust[2] / 5;
						arr[nr][nc] += microDust[2] / 5;
					}
				}
			} // 확산 완료
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			//System.out.println();
			// 공기청정기 가동하자.
			// 윗쪽부터 가동하자.
			int temp = 0;
			int current = 0;
			for (int i = 1; i < M; i++) {
				current = arr[start.get(0)[0]][i];
				arr[start.get(0)[0]][i] = temp;
				temp = current;

			}
			for (int i = start.get(0)[0] - 1; i >= 0; i--) {
				current = arr[i][M - 1];
				arr[i][M - 1] = temp;
				temp = current;
			}
			for (int i = M - 2; i >= 0; i--) {
				current = arr[0][i];
				arr[0][i] = temp;
				temp = current;
			}
			for (int i = 1; i < start.get(0)[0]; i++) {
				current = arr[i][0];
				arr[i][0] = temp;
				temp = current;
			}
			// 아래쪽도 가동시키자.
			temp = 0;
			current = 0;
			for (int i = 1; i < M; i++) {
				current = arr[start.get(1)[0]][i];
				arr[start.get(1)[0]][i] = temp;
				temp = current;

			}
			for (int i = start.get(1)[0] + 1; i < N; i++) {
				current = arr[i][M - 1];
				arr[i][M - 1] = temp;
				temp = current;
			}
			for (int i = M - 2; i >= 0; i--) {
				current = arr[N-1][i];
				arr[N-1][i] = temp;
				temp = current;
			}
			for (int i = N-2; i > start.get(1)[0]; i--) {
				current = arr[i][0];
				arr[i][0] = temp;
				temp = current;
			}

			// 공기청정기 가동 완료 했으니, cnt를 증가시키자.
			cnt++;

		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}

}
