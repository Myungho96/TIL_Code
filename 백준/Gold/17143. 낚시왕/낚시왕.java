
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static String[] temp;
	static int N, M, sharkNum, sum;
//	static List<Shark> shark = new ArrayList<>();
	static Shark[][] shark;

	public static class Shark {
		int r, c, speed, dir, size;

		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shark other = (Shark) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		sharkNum = Integer.parseInt(temp[2]);
		shark = new Shark[N + 1][M + 1];
		for (int i = 0; i < sharkNum; i++) {
			temp = br.readLine().split(" ");
			shark[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = new Shark(Integer.parseInt(temp[0]),
					Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
					Integer.parseInt(temp[4]));
		}
		sum = 0;
		for (int i = 1; i <= M; i++) {
			sum += fishing(i);
			moveShark();
		}
		System.out.println(sum);
	}

	private static void moveShark() {
		Queue<Shark> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (shark[i][j] != null) {
					queue.offer(shark[i][j]);
				}

			}
		}
		shark = new Shark[N + 1][M + 1];
		while (!queue.isEmpty()) {
			Shark Node = queue.poll();

			switch (Node.dir) {
			case 1:
				int distance = (Node.speed) % (N * 2 - 2);
				if (Node.r - distance > 0) {
					Node.r -= distance;
				} else if (N + Node.r - 2 - distance > 0) {
					Node.r = distance - Node.r + 2;
					Node.dir = 2;
				} else {
					Node.r = N - (distance - (Node.r + N - 2));
				}
				logMap(Node);
				break;
			case 2:
				int distance2 = (Node.speed) % (N * 2 - 2);
				if (N - Node.r - distance2 + 1 > 0) {
					Node.r += distance2;
				} else if (2 * N - Node.r - distance2 > 0) {
					Node.r = N - (distance2 - (N - Node.r));
					Node.dir = 1;
				} else {
					Node.r = distance2 - (-Node.r + N * 2 - 2);
				}
				logMap(Node);
				break;
			case 3:
				int distance3 = (Node.speed) % (M * 2 - 2);
				if (M - Node.c - distance3 + 1 > 0) {
					Node.c += distance3;
				} else if (2 * M - Node.c - distance3 > 0) {
					Node.c = M - (distance3 - (M - Node.c));
					Node.dir = 4;
				} else {
					Node.c = distance3 - (-Node.c + M * 2 - 2);
				}
				logMap(Node);
				break;
			case 4:
				int distance4 = (Node.speed) % (M * 2 - 2);
				if (Node.c - distance4 > 0) {
					Node.c -= distance4;
				} else if (M + Node.c - 2 - distance4 > 0) {
					Node.c = distance4 - Node.c + 2;
					Node.dir = 3;
				} else {
					Node.c = M - (distance4 - (Node.c + M - 2));
				}
				logMap(Node);
				break;

			}

		}

	}

	private static void logMap(Shark Node) {
		if (shark[Node.r][Node.c] != null) {
			if (shark[Node.r][Node.c].size < Node.size) {
				shark[Node.r][Node.c] = new Shark(Node.r, Node.c, Node.speed, Node.dir, Node.size);
			}
		} else {
			shark[Node.r][Node.c] = new Shark(Node.r, Node.c, Node.speed, Node.dir, Node.size);
		}

	}

	private static int fishing(int i) {
		int result = 0;
		for (int r = 1; r <= N; r++) {
			if (shark[r][i] != null) {
				result = shark[r][i].size;
				shark[r][i] = null;
				break;
			}
		}

		return result;
	}

	public static boolean isIn(int r, int c) {
		return r > 0 && c > 0 && r <= N && c <= M;
	}

}