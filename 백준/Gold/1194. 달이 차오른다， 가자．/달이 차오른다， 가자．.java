

import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M;
	static String[] temp;
	static String[][] arr;
	static boolean[][][] visited;
	static int ans = -1;
	static int[] start;
	static List<int[]> end;

	public static class User {
		int row, col, cnt, flag;

		public User(int row, int col, int cnt, int flag) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.flag = flag;
		}
	}

	public static void main(String[] args) throws IOException {
		temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		arr = new String[N][M];
		end = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp[j];
				if (temp[j].equals("0")) {
					start = new int[] { i, j };
				} else if (temp[j].equals("1")) {
					end.add(new int[] { i, j });
				}
			}
		}

		bfs();
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}

	private static void bfs() {
		Queue<User> queue = new ArrayDeque<>();
		visited = new boolean[N][M][512];
		queue.offer(new User(start[0], start[1], 0, 0));
		visited[start[0]][start[1]][0] = true;
		while (!queue.isEmpty()) {
			User user = queue.poll();
			for (int i = 0; i < end.size(); i++) {
				if (user.row == end.get(i)[0] && user.col == end.get(i)[1]) {
					ans = user.cnt;
					return;
				}
			}
			
			for (int d = 0; d < deltas.length; d++) {
				int nr = user.row + deltas[d][0];
				int nc = user.col + deltas[d][1];
				if (!isIn(nr, nc) || visited[nr][nc][user.flag])
					continue;
				else if (arr[nr][nc].equals("#")) {
					continue;
				} else if (arr[nr][nc].compareTo("A") >= 0 && arr[nr][nc].compareTo("F") <= 0) {
					if ( (user.flag & ( 1 <<(int) arr[nr][nc].charAt(0) - (int) "A".charAt(0)) ) != 0) {
						visited[nr][nc][user.flag] = true; // 방문처리하고
						queue.add(new User(nr, nc, user.cnt + 1, user.flag)); // 큐에 추가
					}
				} else if (arr[nr][nc].compareTo("a") >= 0 && arr[nr][nc].compareTo("f") <= 0) {
					visited[nr][nc][user.flag | 1 << (int) arr[nr][nc].charAt(0) - (int) "a".charAt(0)] = true;
					queue.add(new User(nr, nc, user.cnt + 1, user.flag | 1 << (int) arr[nr][nc].charAt(0) - (int) "a".charAt(0)));
				} else {
					visited[nr][nc][user.flag] = true; // 방문처리하고
					queue.add(new User(nr, nc, user.cnt + 1, user.flag)); // 큐에 추가
				}

			}
		}

	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

}