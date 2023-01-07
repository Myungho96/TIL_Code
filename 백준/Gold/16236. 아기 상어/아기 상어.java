import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int[][] arr;
	public static int[][] deltas = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	public static boolean[][] visited;
	public static int N, totalCnt;
	public static StringTokenizer st;

	public static class Shark implements Comparable<Shark> {
		int r, c, cnt, level, move;

		public Shark(int r, int c, int cnt, int level, int move) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.level = level;
			this.move = move;
		}

		public void isLevelUp() {
			if (this.cnt == this.level) {
				this.cnt =0;
				this.level++;
			}
		}
		
		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", cnt=" + cnt + ", level=" + level + ", move=" + move + "]";
		}

		@Override
		public int compareTo(Shark o) {
			if (this.r == o.r) {
				return Integer.compare(this.c, o.c);
			}
			return Integer.compare(this.r, o.r);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		Shark baby = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					baby = new Shark(i, j, 0, 2, 0);
				}
			}
		}

		bfs(baby);
		System.out.println(totalCnt);

	}

	private static void bfs(Shark baby) {
		totalCnt = 0;
		Queue<Shark> queue = new LinkedList<>();
		List<Shark> list = new LinkedList<>();
		queue.offer(baby);
		visited[baby.r][baby.c] = true;
		arr[baby.r][baby.c] = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Shark shark = queue.poll();// 큐에서 상어를 꺼낸다
				// 먹을걸 찾으러가자.
					for (int d = 0; d < 4; d++) {
						int nr = shark.r + deltas[d][0];
						int nc = shark.c + deltas[d][1];
						if (isIn(nr, nc) && !visited[nr][nc]) {// 범위안에 있으면 물고기의 레벨을 살펴보자.
							visited[nr][nc] = true;
							if (arr[nr][nc] == 0 || arr[nr][nc] == shark.level) {// 0이거나 나랑 같은 사이즈면
								queue.offer(new Shark(nr, nc, shark.cnt, shark.level, shark.move + 1));
							} else if(arr[nr][nc] <shark.level) {
								list.add(new Shark(nr, nc, shark.cnt, shark.level, shark.move + 1));
								
							}
						}
					}
			}
			if (list.size() > 0) {// 물고기가 있으면
				// 리스트에 있는 물고기중에 가장 적합한 물고기를 선택하자.
				Collections.sort(list);
				if (arr[list.get(0).r][list.get(0).c] != 0 && arr[list.get(0).r][list.get(0).c] < list.get(0).level) {// 먹을수
																														// 있는
																														// 물고기면
					// 1. 그 자리를 0 처리한다.
					arr[list.get(0).r][list.get(0).c] = 0;
					// 2. 지금까지의 move를 total에 더해준다. 또한 cnt를 하나 늘려주고 레벨업 가능하면 레벨업하자.
					totalCnt += list.get(0).move;
					list.get(0).cnt++;
					list.get(0).isLevelUp();
					// 3.큐를 클리어해준다.
					queue.clear();
					// 4.샤크를 처음부터 다시 시작하자.
					queue.offer(new Shark(list.get(0).r, list.get(0).c, list.get(0).cnt, list.get(0).level, 0));
					// 5. visited 클리어해준다.
					for (int i = 0; i < N; i++) {
						Arrays.fill(visited[i], false);
					}
					visited[list.get(0).r][list.get(0).c] = true;
					list.clear();
				}
			}
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
