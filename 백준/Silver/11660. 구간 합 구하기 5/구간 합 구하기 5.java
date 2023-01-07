
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static int[][] arr;
	public static int[][] sum;
	public static int answer = 0;
	public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String temp = bf.readLine();
		StringTokenizer st = new StringTokenizer(temp," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp = bf.readLine();
			st = new StringTokenizer(temp," ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int tempp = 0;
		sum = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				tempp += arr[i-1][j-1];
				sum[i][j] = tempp;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//
//		for (int i = 0; i < N+1; i++) {
//			System.out.println(Arrays.toString(sum[i]));
//		}

		for (int i = 0; i < M; i++) {
			temp = bf.readLine();
			st = new StringTokenizer(temp," ");
			int X1 = Integer.parseInt(st.nextToken());
			int Y1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int Y2 = Integer.parseInt(st.nextToken());

			if (X1 == X2 && Y1 == Y2) {
				bw.write(arr[X1-1][Y1-1] + "\n");
			} else {
				int result = 0;
				if(Y1!=1) {
					for (int j = X1; j <=X2; j++) {
						result+=sum[j][Y2] - sum[j][Y1-1];
					}
				}else if(Y1==1) {
					for (int j = X1; j <=X2; j++) {
						result+=sum[j][Y2] - sum[j-1][N];
					}
				}
				bw.write(result+"\n");
			}

		}
		bw.flush();   //남아있는 데이터를 모두 출력시킴
		bw.close();   //스트림을 닫음
	}

}
