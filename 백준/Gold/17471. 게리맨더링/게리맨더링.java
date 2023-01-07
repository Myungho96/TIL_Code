
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N;
	static int min = Integer.MAX_VALUE;
	static String[] temp;
	static boolean[] visited;
	static int ans = -1;
	static List<Integer>[] list;
	static List<Integer> team1;
	static List<Integer> team2;
	static int[] nodeNum;
	static int[] personNum;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		list = new List[N + 1];
		nodeNum = new int[N + 1];
		personNum = new int[N + 1];
		team1 = new ArrayList<>();
		team2 = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		temp = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			personNum[i] = Integer.parseInt(temp[i - 1]);
		}
		for (int i = 1; i <= N; i++) {
			temp = br.readLine().split(" ");
			nodeNum[i] = Integer.parseInt(temp[0]);
			for (int j = 1; j <= nodeNum[i]; j++) {
				list[i].add(Integer.parseInt(temp[j]));
			}
		}
		// test
//		for (int i = 0; i < list.length; i++) {
//			System.out.println(list[i].toString());
//		}
		comb(1, 0, new ArrayList<>());
		ans = min == Integer.MAX_VALUE ? -1 : min;
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}

	private static void comb(int start, int cnt, List<Integer> teamList) {
		if (cnt < N && cnt > 0) {
			team1.clear();
			team2.clear();
			for (int i = 1; i <= N; i++) {
				team2.add(i);
			}

			for (int i = 0; i < teamList.size(); i++) {
				int temp = teamList.get(i);
				team1.add(temp);
				team2.remove(team2.indexOf(temp));
			}
			int temp1 = check(team1);
			int temp2 = check(team2);
			if (temp1 > -1 && temp2 > -1) {// 조건 만족하면 인구 비교하기
//				System.out.println(team1.toString() + " and "+team2.toString());
				int result = Math.abs(temp1 - temp2);
				min = Math.min(min, result);
			}
		}
		if (cnt == N) {
			return;
		}
		for (int i = start; i <= N; i++) {
			teamList.add(i);
			comb(i + 1, cnt + 1, teamList);
			teamList.remove(teamList.indexOf(i));
		}

	}

	private static int check(List<Integer> team) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[team.size()];
		queue.offer(team.get(0));
		visited[0] = true;
		int total = 0;
		int num = 0;
		while (!queue.isEmpty()) {
			int node = queue.poll();
			total++;
			num += personNum[node];
			if (total == team.size()) {
				return num;
			}
			for (int E : list[node]) {
				if (team.indexOf(E) >= 0 && !visited[team.indexOf(E)]) {
					visited[team.indexOf(E)] = true;
					queue.offer(E);
				}
			}
		}

		return -1;
	}

}