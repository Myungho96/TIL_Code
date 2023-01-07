import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static int[][] cctv1 = new int[][] { { 0, 0, 0, 1 }, { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 } };
	public static int[][] cctv2 = new int[][] { { 0, 0, 1, 1 }, { 1, 1, 0, 0 } };
	public static int[][] cctv3 = new int[][] { { 1, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 } };
	public static int[][] cctv4 = new int[][] { { 1, 0, 1, 1 }, { 1, 1, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 0 } };
	public static int[][] cctv5 = new int[][] { { 1, 1, 1, 1 } };
	public static int[][] deltas = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, M, min = Integer.MAX_VALUE;

	public static boolean[] visited;
	public static StringTokenizer st;
	public static List<int[]> cctv = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num < 6 && num > 0) {
					cctv.add(new int[] { i, j, num });
				}
				arr[i][j] = num;

			}
		}

		dfs(0, arr);
		bw.write(min + "\n");
		bw.flush();
		bw.close();
	}

	public static void dfs(int cnt, int[][] arr) {
		if (cnt == cctv.size()) {
			// 0을 카운트해야함
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0) {
						sum++;
					}
				}
			}
			min = Integer.min(min, sum);
			return;
		}
		if(cctv.size() == 0) {
			// 0을 카운트해야함
						int sum = 0;
						for (int i = 0; i < N; i++) {
							for (int j = 0; j < M; j++) {
								if (arr[i][j] == 0) {
									sum++;
								}
							}
						}
						min = Integer.min(min, sum);
						return;
		}
		int[][] copyArr = new int[N][M];//배열 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		if (cctv.get(cnt)[2] == 1) {
			for (int i = 0; i < cctv1.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (cctv1[i][j] == 1) {// 1이면 그 방향으로 #을 쓴다.
						int cr = cctv.get(cnt)[0];
						int cc = cctv.get(cnt)[1];
						while (true) {
							cr += deltas[j][0];
							cc += deltas[j][1];
							if (isIn(cr, cc) && copyArr[cr][cc] != 6) {
								copyArr[cr][cc] = cnt + 100;
							} else {
								break;
							}
						}
					}
				}
				dfs(cnt + 1, copyArr);
				// 여기까지 하면 한번 실행한것임. 배열 초기화해야한다.
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						copyArr[j][k] = arr[j][k];
					}
				}

			}
		}
		if (cctv.get(cnt)[2] == 2) {
			for (int i = 0; i < cctv2.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (cctv2[i][j] == 1) {// 1이면 그 방향으로 #을 쓴다.
						int cr = cctv.get(cnt)[0];
						int cc = cctv.get(cnt)[1];
						while (true) {
							cr += deltas[j][0];
							cc += deltas[j][1];
							if (isIn(cr, cc) && copyArr[cr][cc] != 6) {
								copyArr[cr][cc] = cnt + 100;
							} else {
								break;
							}
						}
					}
				}
				dfs(cnt + 1, copyArr);
				// 여기까지 하면 한번 실행한것임. 배열 초기화해야한다.
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						copyArr[j][k] = arr[j][k];
					}
				}

			}
		}
		// 4번 돌리면서 각각 다음 dfs를 호출하자.
		// #표시하고
		// dfs하고
		// 돌리고
		// 복구하고
		if (cctv.get(cnt)[2] == 3) {
			for (int i = 0; i < cctv3.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (cctv3[i][j] == 1) {// 1이면 그 방향으로 #을 쓴다.
						int cr = cctv.get(cnt)[0];
						int cc = cctv.get(cnt)[1];
						while (true) {
							cr += deltas[j][0];
							cc += deltas[j][1];
							if (isIn(cr, cc) && copyArr[cr][cc] != 6) {
								copyArr[cr][cc] = cnt + 100;
							} else {
								break;
							}
						}
					}
				}
				dfs(cnt + 1, copyArr);
				// 여기까지 하면 한번 실행한것임. 배열 초기화해야한다.
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						copyArr[j][k] = arr[j][k];
					}
				}

			}
		}
		if (cctv.get(cnt)[2] == 4) {
			for (int i = 0; i < cctv4.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (cctv4[i][j] == 1) {// 1이면 그 방향으로 #을 쓴다.
						int cr = cctv.get(cnt)[0];
						int cc = cctv.get(cnt)[1];
						while (true) {
							cr += deltas[j][0];
							cc += deltas[j][1];
							if (isIn(cr, cc) && copyArr[cr][cc] != 6) {
								copyArr[cr][cc] = cnt + 100;
							} else {
								break;
							}
						}
					}
				}
				dfs(cnt + 1, copyArr);
				// 여기까지 하면 한번 실행한것임. 배열 초기화해야한다.
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						copyArr[j][k] = arr[j][k];
					}
				}

			}
		}
		if (cctv.get(cnt)[2] == 5) {
			for (int i = 0; i < cctv5.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (cctv5[i][j] == 1) {// 1이면 그 방향으로 #을 쓴다.
						int cr = cctv.get(cnt)[0];
						int cc = cctv.get(cnt)[1];
						while (true) {
							cr += deltas[j][0];
							cc += deltas[j][1];
							if (isIn(cr, cc) && copyArr[cr][cc] != 6) {
								copyArr[cr][cc] = cnt + 100;
							} else {
								break;
							}
						}
					}
				}
				dfs(cnt + 1, copyArr);
				// 여기까지 하면 한번 실행한것임. 배열 초기화해야한다.
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						copyArr[j][k] = arr[j][k];
					}
				}

			}
		}

		
		
		
	}
	
	
	

	public static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
