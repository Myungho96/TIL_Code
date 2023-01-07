

import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int TC, N, R;
	static String[] temp;
	static int MOD = 10007;

	public static void main(String[] args) throws IOException {

		temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		R = Integer.parseInt(temp[1]);
		long fac[] = new long[N + 1];
		fac[0] = 1;
		for (int i = 1; i <= N; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
		long result = (fac[N] % MOD * fermat((fac[R] * fac[N - R]) % MOD, MOD - 2)) % MOD;
		bw.write(result + "\n");

		bw.flush();
		bw.close();

	}

	private static long fermat(long n, int x) {
		if (x == 0)
			return 1;
		long half = fermat(n, x / 2);
		if (x % 2 == 0)
			return (half * half) % MOD;
		else
			return ((half * half) % MOD * n) % MOD;
	}

}