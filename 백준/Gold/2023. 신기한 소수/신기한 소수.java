import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int[] checked;
	public static String[] arr;
	public static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		solution(0, 0);
		bw.flush();
		bw.close();
	}

	public static void solution(int cnt, int result) throws IOException {
		if (cnt == N) {

			bw.write(result+"\n");
			return;
		}
		for (int i = 1; i < num.length; i++) {
			if (isPrime(result * 10 + i)) {
				solution(cnt + 1, result * 10 + i);
			}
		}
	}

	public static boolean isPrime(int n) {
		if (n == 1)
			return false;
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
