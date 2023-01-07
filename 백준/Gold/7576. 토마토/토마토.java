import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int N, M, ans = -1, cnt=0;
	public static int[][] arr;
	public static boolean[][] visited;
	public static Queue<int[]> rotten = new LinkedList<int[]>();
	public static int[][] deltas = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					rotten.add(new int[] { i, j });
				}
				if (arr[i][j] == -1) {
					cnt++;
				}
			}
		}
		bfs(0, 0);
		if(cnt == M*N)
			bw.write(ans+"\n");
		else
			bw.write("-1"+"\n");
		bw.flush();
		bw.close();
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = rotten;
		cnt +=rotten.size();
		for (int[] rot : queue) {
			visited[rot[0]][rot[1]] = true;
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] tomato = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = tomato[0] + deltas[d][0];
					int nc = tomato[1] + deltas[d][1];
					if (isIn(nr, nc) && !visited[nr][nc]) {//빈 상자 아니고 방문하지 않았으면 안익은 토마토이다.
						visited[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
						cnt++;

					}
				}
			}
			ans++;
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M && arr[nr][nc] != -1;
	}

}
