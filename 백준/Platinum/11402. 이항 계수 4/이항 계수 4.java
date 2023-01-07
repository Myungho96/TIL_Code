

import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static long N, R;
	static int TC, MOD, Up, Down;
	static String[] temp;
	static long result;

	public static void main(String[] args) throws IOException {
		result = 1;
		temp = br.readLine().split(" ");
		N = Long.parseLong(temp[0]);
		R = Long.parseLong(temp[1]);
		MOD = Integer.parseInt(temp[2]);
		long fac[] = new long[(int) (MOD + 1)];
		fac[0] = 1;
		for (int i = 1; i <= MOD; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
		// 뤼카의 정리
		while (N != 0 || R != 0) {
			Up = (int) (N % MOD);
			Down = (int) (R % MOD);
			if (Up < Down) {
				result = 0;
				break;
			}
			// 페르마의 소정리
			result = result * (fac[Up] % MOD * fermat((fac[Down] * fac[Up - Down]) % MOD, MOD - 2)) % MOD;
			N /= MOD;
			R /= MOD;
		}
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