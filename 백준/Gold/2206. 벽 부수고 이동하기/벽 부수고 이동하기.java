
import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
	static int N, M;
	static long[][] arr;
	static boolean[][][] visited;
	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];
		for (int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(temp[j - 1]);
			}
		}

		ans = 0;
		BFS();
		if (ans == 0) {
			bw.write(-1 + "\n");
		} else {
			bw.write(ans + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(1, 1, 1, 0));
		visited[1][1][0] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.r == N && node.c == M) {
				ans = node.cnt;
				return;
			}
			for (int d = 0; d < 4; d++) {
				int lr = node.r + deltas[d][0];
				int lc = node.c + deltas[d][1];
				if(isIn(lr,lc)){
					if(node.breakCnt==0) {
						if(arr[lr][lc]==0 && !visited[lr][lc][0]) {
							visited[lr][lc][0] = true;
							queue.offer(new Node(lr,lc,node.cnt+1,0));
						}else if(arr[lr][lc]==1 && !visited[lr][lc][1]) {
							visited[lr][lc][1] = true;
							queue.offer(new Node(lr,lc,node.cnt+1,1));
						}
					}else {
						if(arr[lr][lc]==0 && !visited[lr][lc][1]) {
							visited[lr][lc][1] = true;
							queue.offer(new Node(lr,lc,node.cnt+1,1));
						}
					}
					
					
				}
				
			}
		}

	}

	public static boolean isIn(int r, int c) {
		return r > 0 && c > 0 && r <= N && c <= M;
	}

	public static class Node {
		int r, c, cnt, breakCnt;

		public Node(int r, int c, int cnt, int breakCnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.breakCnt = breakCnt;
		}

	}

}