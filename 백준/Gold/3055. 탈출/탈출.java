import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int[][] deltas = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	public static String[][] arr;
	public static boolean[][] visited;
	public static int[] start, end;
	public static List<int[]> waterList = new ArrayList<>();
	public static int N, M, cnt;
	public static boolean flag = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		visited = new boolean[N][M];
		String[] temp;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				if (temp[j].equals("S"))
					start = new int[] { i, j,0 };
				else if (temp[j].equals("D"))
					end = new int[] { i, j };
				else if (temp[j].equals("*"))
					waterList.add(new int[] { i, j });
				arr[i][j] = temp[j];
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		bfs(start);
		if(flag) {
			bw.write(cnt+"\n");
		}else {
			bw.write("KAKTUS"+"\n");
		}
		bw.flush();
		bw.close();

	}

	private static void bfs(int[] startLoc) {
		Queue<int[]> hedgehog = new LinkedList<>();
		Queue<int[]> water = new LinkedList<>();
		while (!waterList.isEmpty()) {// 물 다 담기
			water.offer(waterList.get(0));
			waterList.remove(0);
		}
		hedgehog.offer(startLoc);
		visited[startLoc[0]][startLoc[1]] = true;
		while (!(water.isEmpty() && hedgehog.isEmpty())) {
			// 1. 현재 사이즈의 물부터 확장한다.
			int sizeWater = water.size();
			while (sizeWater-- > 0) {
				int[] node = water.poll();// 사방탐색하여 물 추가
				for (int d = 0; d < 4; d++) {
					int nr = node[0] + deltas[d][0];
					int nc = node[1] + deltas[d][1];
					if (isIn(nr, nc) && !visited[nr][nc] && !arr[nr][nc].equals("X")&&!arr[nr][nc].equals("D")) {
						visited[nr][nc] = true;
						arr[nr][nc] = "*";
						water.offer(new int[] { nr, nc });
					}
				}
			}
			// 2. 물확장을 끝냈다면, 고슴도치를 이동시켜주자
			int sizehedgehog = hedgehog.size();
			while (sizehedgehog-- > 0) {
				int[] node = hedgehog.poll();// 사방탐색하여 물 추가
				for (int d = 0; d < 4; d++) {
					int nr = node[0] + deltas[d][0];
					int nc = node[1] + deltas[d][1];
					if (isIn(nr, nc) && !visited[nr][nc] && !arr[nr][nc].equals("X")&& !arr[nr][nc].equals("*")) {
						if(arr[nr][nc].equals("D")) {//비버의 굴을 만난 경우
							flag = true;
							cnt = node[2]+1;
							return;
						}
						visited[nr][nc] = true;
						arr[nr][nc] = "S";
						hedgehog.offer(new int[] { nr, nc,node[2]+1 });
					}
				}
			}
			
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}

}
