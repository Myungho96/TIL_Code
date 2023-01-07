import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, min = Integer.MAX_VALUE;
	public static int[][] arr;
	public static int[][] D;
	public static boolean[][] visited;
	public static int[][] deltas = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		int cnt = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				bw.flush();
				bw.close();
				return;
			}
			arr = new int[N][N];
			visited = new boolean[N][N];
			String[] temp;
			for (int i = 0; i < N; i++) {
				temp = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(temp[j]);
				}
			}
			min = Integer.MAX_VALUE;
			D = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			D[0][0] = arr[0][0];
			pq.offer(new int[] { 0, 0, 0 });
			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				if (visited[current[0]][current[1]])
					continue;
				visited[current[0]][current[1]] = true;
				if (current[0] == N - 1 && current[1] == N - 1) {
					min = Math.min(min, current[2]);
					break;
				}
				for (int d = 0; d < 4; d++) {
					int nr = current[0] + deltas[d][0];
					int nc = current[1] + deltas[d][1];
					if (isIn(nr, nc) && !visited[nr][nc] && D[nr][nc] > D[current[0]][current[1]] + arr[nr][nc]) {
						D[nr][nc] = D[current[0]][current[1]] + arr[nr][nc];
						pq.offer(new int[] { nr, nc, D[nr][nc] });
					}
				}
			}
			bw.write("Problem " + cnt + ": " + min + "\n");
			cnt++;
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
