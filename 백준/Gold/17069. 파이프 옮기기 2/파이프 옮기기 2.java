

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
	static int N;
	static long[][] arr;
	static long[][][] map;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new long[N + 1][N + 1];
		map = new long[N + 1][N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 2; i <= N; i++) {
			if (arr[1][i] == 1) {
				break;
			} else {
				map[1][i][0] = 1;
			}

		}

		ans = 0;
		DFS(2, 3);
		bw.write(map[N][N][0] + map[N][N][1] + map[N][N][2] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int r, int c) {

		for (int i = r; i <= N; i++) {
			for (int j = c; j <= N; j++) {
				if(arr[i][j] == 1)
					continue;
				map[i][j][0] = map[i][j - 1][0] + map[i][j - 1][2];
				map[i][j][1] = map[i - 1][j][1] + map[i - 1][j][2];
				if (arr[i][j - 1] == 0 && arr[i - 1][j] == 0) {
					map[i][j][2] = map[i - 1][j - 1][0] + map[i - 1][j - 1][1] + map[i - 1][j - 1][2];
				}

			}
		}

	}

	public static boolean isIn(int r, int c) {
		return r > 0 && c > 0 && r <= N && c <= N;
	}

}