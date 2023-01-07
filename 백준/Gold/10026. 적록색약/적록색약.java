import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int N, totalA = 0, totalB = 0;
	public static String[][] arrA;
	public static String[][] arrB;
	public static boolean[][] visitedA,visitedB;
	public static int[][] deltas = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arrA = new String[N][N];
		arrB = new String[N][N];
		visitedA = new boolean[N][N];
		visitedB = new boolean[N][N];
		String[] temp;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arrA[i][j] = temp[j];
				arrB[i][j] = temp[j];
				if(arrB[i][j].equals("R")) {
					arrB[i][j] = "G";
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visitedA[i][j]) {
					bfsA(i, j);
					totalA++;
				}
				if(!visitedB[i][j]) {
					bfsB(i, j);
					totalB++;
				}
				
			}
		}
		bw.write(totalA + " " + totalB+"\n");
		bw.flush();
		bw.close();

	}


	private static void bfsA(int r, int c) {
		Queue <int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {r,c});
		visitedA[r][c] = true;
		String color = arrA[r][c];
		while(!queue.isEmpty()) {
			int []node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = node[0] + deltas[d][0];
				int nc = node[1] + deltas[d][1];
				if(isIn(nr,nc) && !visitedA[nr][nc] && arrA[nr][nc].equals(color)) {
					visitedA[nr][nc] = true;
					queue.offer(new int[] {nr,nc});
				}
			}
		}
		
	}
	
	private static void bfsB(int r, int c) {
		Queue <int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {r,c});
		visitedB[r][c] = true;
		String color = arrB[r][c];
		while(!queue.isEmpty()) {
			int []node = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = node[0] + deltas[d][0];
				int nc = node[1] + deltas[d][1];
				if(isIn(nr,nc) && !visitedB[nr][nc] && arrB[nr][nc].equals(color)) {
					visitedB[nr][nc] = true;
					queue.offer(new int[] {nr,nc});
				}
			}
		}
		
	}


	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
