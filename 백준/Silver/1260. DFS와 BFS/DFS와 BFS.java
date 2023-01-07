import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, M, S;
	public static List<Integer> list[];
	public static boolean[] visited;
	public static StringTokenizer st;
	public static List<int[]> cctv = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken()) - 1;
		list = new List[N];
		visited = new boolean[N];
		int x, y;
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			list[x].add(y);
			list[y].add(x);
		}
		for (int i = 0; i < N; i++) {
			Collections.sort(list[i]);
		}
		dfs(S, 0);
		bw.write("\n");
		visited = new boolean[N];
		bfs(S, 0);
		bw.flush();
		bw.close();
	}

	private static void bfs(int start, int cnt) throws IOException {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			bw.write(node + 1 + " ");
			for (int i = 0; i < list[node].size(); i++) {
				if (!visited[list[node].get(i)]) {
					visited[list[node].get(i)] = true;
					queue.offer(list[node].get(i));
				}
			}
		}

	}

	private static void dfs(int start, int cnt) throws IOException {
		// TODO 자동 생성된 메소드 스텁
		visited[start] = true;

//		if (cnt == N - 1) {
//			bw.write(start + 1 + "\n");
//			return;
//		}
		bw.write(start + 1 + " ");
		for (int i = 0; i < list[start].size(); i++) {
			if (!visited[list[start].get(i)]) {
				dfs(list[start].get(i), cnt + 1);
			}
		}

	}

}
