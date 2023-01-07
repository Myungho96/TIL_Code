import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, min = Integer.MAX_VALUE;
	public static int[][] arr;
	public static boolean[] visited;
	public static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		bw.write(min + "\n");
		bw.flush();
		bw.close();
	}

	private static void dfs(int cnt, int start) {
		if (cnt == N / 2) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i] == visited[j]) {
						if (visited[i]) {
							sum += arr[i][j];
						} else {
							sum -= arr[i][j];
						}
					}
				}
			}
			min = Integer.min(min, Math.abs(sum));
			return;
		}

		for (int i = start; i < N; i++) {
			visited[i] = true;
			dfs(cnt + 1, i + 1);
			visited[i] = false;
		}

	}

}
