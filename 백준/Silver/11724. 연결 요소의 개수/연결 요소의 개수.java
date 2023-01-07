import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static boolean[] visited;
	public static int[][] arr;
	public static int N, M, total = 0;

	public static void main(String[] args) throws IOException {
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		arr = new int[M][2];
		visited = new boolean[N];
		for (int i = 0; i < arr.length; i++) {
			temp = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(temp[0]);
			arr[i][1] = Integer.parseInt(temp[1]);
			
		}
		
//		Arrays.sort(arr,new Comparator<int[]>() {
//
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				if(o1[0]==o2[0]) {
//					return Integer.compare(o1[1], o2[1]);
//				}
//				return Integer.compare(o1[0], o2[0]);
//			}
//			
//		});
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		for (int i = 0; i < N; i++) {
			if((!visited[i])) {
				dfs(i);
				total++;
			}
		}
		bw.write(total+"\n");
		bw.flush();
		bw.close();

	}
	
	public static void dfs(int node) {
		visited[node]=true;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][0]-1 == node) {
				if(!visited[arr[i][1]-1]) {
					dfs(arr[i][1]-1);
				}
			}else if(arr[i][1]-1 == node) {
				if(!visited[arr[i][0]-1]) {
					dfs(arr[i][0]-1);
				}
			}
		}
		
	}

}
