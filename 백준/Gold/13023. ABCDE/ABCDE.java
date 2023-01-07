import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int N, M;
	public static int a, b, c;
	public static List<Integer>[] list;
	public static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			if (dfs(i, 0)) {
				bw.flush();
				bw.close();
				return;
			}
		}
		bw.write("0" + "\n");
		bw.flush();
		bw.close();
		return;

	}

	private static boolean dfs(int index, int cnt) throws IOException {
		if (cnt == 4) {
			bw.write("1" + "\n");
			return true;
		}
		visited[index] = true;
		for (int data : list[index]) {
			if (!visited[data]) {
				boolean flag = dfs(data, cnt + 1);
				if(flag)
					return flag;
				visited[data] = false;
			}
		}

		return false;

	}

}