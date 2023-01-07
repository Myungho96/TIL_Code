
import java.util.*;
import java.lang.*;
import java.io.*;
public class Main {
	public static int []arr;
	public static int []sum;
	public static int answer = 0;
	public static Scanner sc = new Scanner(System.in);
	public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String temp = bf.readLine();
		StringTokenizer st = new StringTokenizer(temp," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		temp = bf.readLine();
		st = new StringTokenizer(temp," ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sum = new int[N+1];
		int temp2 = 0;
		for (int i = 1; i < N+1; i++) {
			temp2+=arr[i-1];
			sum[i] = temp2;
		}
		for (int i = 0; i < M; i++) {
			temp = bf.readLine();
			st = new StringTokenizer(temp," ");
			int X = Integer.parseInt(st.nextToken())-1;
			int Y = Integer.parseInt(st.nextToken());
			System.out.println(sum[Y]-sum[X]);
		}
		
	}

}
