

import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

public class Main {
	static int[] parents;

	//
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static void make() {// 크기가 1인 서로소 집합 생성
		parents = new int[landCnt];
		for (int i = 0; i < landCnt; i++) {// 모든 노드가 자신을 부모로 하는(대표자) 집합으로 만듬
			parents[i] = i;
		}
	}

	static int find(int a) {// a의 대표자 찾기
		if (parents[a] == a) {// 나의 부모가 나라면
			return a;
		}
		// 나의 부모가 내가 아닌경우
		return parents[a] = find(parents[a]);// 내 부모의 부모를 탐색하면서 받은 결과를 내 부모로한다.
	}

	static boolean union(int a, int b) {// 유니온이 잘 되면 true를, 안되면 false를 반환한다.
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {// 사이클이 발생한 경우. 같은 부모를 가지고 있다.
			return false;
		}
		parents[bRoot] = aRoot;// 사이클이 발생하지 않았으면, b루트의 부모를 a루트로 한다.
		return true;
	}
	//

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, landCnt;
	static int[][] arr;
	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static String[] temp;
	static boolean[][] visited;
	static List<Node>[] list = new List[7];
	static List<Edge> edge = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		arr = new int[N][M];
		for (int i = 0; i < 7; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(temp[j]) == 1)
					arr[i][j] = -Integer.parseInt(temp[j]);
				else
					arr[i][j] = Integer.parseInt(temp[j]);
			}
		}

		visited = new boolean[N][M];
		landCnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && arr[i][j] == -1) {
					dfs(landCnt, i, j);
					landCnt++;
				}

			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		for (int i = 1; i < landCnt; i++) {
			for (Node node : list[i]) {
				findBridge(i, node.r, node.c);
			}
		}
		make();
		Collections.sort(edge);
//		System.out.println(edge.toString());
		int result = 0;
		int count = 0;
		for (Edge E : edge) {
			if (union(E.from, E.to)) {
				result += E.weight;
				if (++count == landCnt - 1)
					break;
			}
		}
		int repre = find(1);
//		System.out.println(repre);
		boolean check = true;
		for (int i = 2; i < landCnt; i++) {
			if(repre!=find(i)) {
				check = false;
			}
		}
		
		if (!check)
			result = -1;
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}

	private static void findBridge(int i, int r, int c) {
		Queue<Node> queue = new ArrayDeque<>();
		visited = new boolean[N][M];
		queue.offer(new Node(r, c));
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int d = 0; d < deltas.length; d++) {
				int count = 0;
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 0) {
					wh: while (true) {
						count++;
						nr += deltas[d][0];
						nc += deltas[d][1];
						if (isIn(nr, nc) && !visited[nr][nc]) {
							if (arr[nr][nc] == 0) {
								visited[nr][nc] = true;
								continue;
							} else if (arr[nr][nc] != 0 && arr[nr][nc] != i && count <= 1) {
								break wh;
							}else if (arr[nr][nc] != 0 && arr[nr][nc] == i) {
								break wh;
							} else if (arr[nr][nc] != 0 && arr[nr][nc] != i && count > 1) {
								for (int j = 0; j < edge.size(); j++) {
									if (edge.get(j).from == i && edge.get(j).to == arr[nr][nc]
											&& edge.get(j).weight <= count) {
										break wh;
									} else if (edge.get(j).from == i && edge.get(j).to == arr[nr][nc]
											&& edge.get(j).weight > count) {
										edge.get(j).weight = count;
										break wh;
									}
								}
								edge.add(new Edge(i, arr[nr][nc], count));
								break;
							}
						} else {
							break;
						}

					}
				}
			}
		}

	}

	private static void dfs(int cnt, int r, int c) {
		visited[r][c] = true;
		arr[r][c] = cnt;
		list[cnt].add(new Node(r, c));
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] == -1) {
				dfs(cnt, nr, nc);
			}
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}

	public static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}
}