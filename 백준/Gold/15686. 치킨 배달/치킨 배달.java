import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int[][] arr, compare;
	public static int[] numbers;
	public static StringTokenizer st;
	public static int N, M, min = Integer.MAX_VALUE;
	public static List<Integer[]> house = new ArrayList<>();
	public static List<Integer[]> chicken = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					house.add(new Integer[] { i, j });
				} else if (arr[i][j] == 2) {
					chicken.add(new Integer[] { i, j });
				}
			}
		}
		compare = new int[house.size()][chicken.size()];

		for (int i = 0; i < house.size(); i++) {
			for (int j = 0; j < chicken.size(); j++) {
				compare[i][j] = Math.abs(house.get(i)[0] - chicken.get(j)[0])
						+ Math.abs(house.get(i)[1] - chicken.get(j)[1]);
			}
		}
		// 재귀, 조합
		int[] datas = new int[house.size()];
		for (int i = 0; i < house.size(); i++) {
			datas[i] = Integer.MAX_VALUE;
		}
		bfs(0, 0, datas);// 모든 집을 다 순회하면서 최소를 구해보자.
		bw.write(min + "\n");
		bw.flush();
		bw.close();
	}

	public static void bfs(int cnt, int start, int[] datas) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < house.size(); i++) {
				sum += datas[i];
			}
			min = Integer.min(min, sum);
			return;
		}
		 
		for (int i = start; i < chicken.size(); i++) {
			int[] temp = Arrays.copyOf(datas, datas.length);
			for (int j = 0; j < house.size(); j++) {
				temp[j] = Integer.min(temp[j], compare[j][i]);
			}
			bfs(cnt + 1, i+1, temp);
		}
	}

}