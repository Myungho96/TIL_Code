import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static Stack<Integer[]> stack1 = new Stack<>();// 주어진 탑을 순서대로 담는 스택
	public static Stack<Integer[]> stack2 = new Stack<>();// 더 큰 탑이 나올때까지 저장하는 스택
	public static int[] arr;
	public static int[] location;
	public static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		location = new int[N];

		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < temp.length; i++) {
			Integer[] tmppp = {Integer.parseInt(temp[i]),i};
			stack1.add(tmppp);
		}
		int cnt = N - 1;
		arr = new int[N];// 결과를 저장할 배열
		while (!stack1.empty()) {
			// 1. 스택1의 맨 위에있는 원소를 꺼낸다.
			Integer[] tower = stack1.pop();
			// 2. 스택2가 비어있지 않으면 맨위에 있는 원소와 비교해본다.
			// 맨 위의 원소의 값이 tower보다 작은 경우, pop한 뒤 스택1의 사이즈를 arr[]에 넣어준다.
			while (!stack2.empty()) {
				if (tower[0] > stack2.peek()[0]) {
					Integer[] temp2 = stack2.pop();
					arr[temp2[1]] = stack1.size()+1;
				}else {
					break;
				}
			}
			//3. stack2에 tower를 저장한다.
			stack2.add(tower);
		}
		for (int i = 0; i < arr.length; i++) {
			bw.write(arr[i] + " ");
		}
		bw.write("\n");
		bw.flush();
		bw.close();
	}

}
