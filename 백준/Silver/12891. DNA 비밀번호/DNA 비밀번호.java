import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int[] checked = new int[4];
	public static String[] arr;
	public static int[] num = new int[4];
	public static int total = 0, N, M;

	public static void main(String[] args) throws IOException {
		arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		M = Integer.parseInt(arr[1]);
		arr = br.readLine().split("");// 문자열 받음.
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			num[i] = Integer.parseInt(temp[i]);
		}
		int x = -1;
		int y = -1;
		for (int i = 0; i <= N-M; i++) {
			if(i==0) {
				x = i;//제일 처음에 넣은 것 저장
				for (int j = i; j < M; j++) {
					check(arr[j]);
					y = j;//마지막 인덱스 저장
				}
				cmp();
			}else {
				uncheck(arr[x]);//제일 처음에 넣은거 빼고
				x+=1;//그다음꺼가 이제 제일 처음 들어간거다.
				y+=1;
				check(arr[y]);//마지막에 들어간거 다음꺼를 넣어준다.
				cmp();
			}
		}
		bw.write(total+"\n");
		bw.flush();
		bw.close();
	}

	

	public static int check(String data) {
		if (data.equals("A")) {
			checked[0]++;
			return 0;
		} else if (data.equals("C")) {
			checked[1]++;
			return 1;
		} else if (data.equals("G")) {
			checked[2]++;
			return 2;
		} else if (data.equals("T")) {
			checked[3]++;
			return 3;
		}else {
			return -1;
		}
	}

	public static void uncheck(String data) {
		if (data.equals("A")) {
			checked[0]--;
		} else if (data.equals("C")) {
			checked[1]--;
		} else if (data.equals("G")) {
			checked[2]--;
		} else if (data.equals("T")) {
			checked[3]--;
		}
	}
	public static void cmp() {
		for (int i = 0; i < num.length; i++) {
			if(checked[i]-num[i]<0) {
				return;
			}
		}
		total++;
	}

}
