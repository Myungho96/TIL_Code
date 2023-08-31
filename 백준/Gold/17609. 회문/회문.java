import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {

        //회문인 경우 앞뒤에서 하나씩 뺐을때 계속 같다 -> 안같을 때까지 빼면 사이즈가 0이 될듯
        //유사 회문인 경우 앞뒤애서 하나씩 빼다가 다른 경우 그 시점에 남은 사이즈가 홀수여야하고,
        //다른 문자의 다음 문자와 끝 문자가 같거나 반대의 경우가 같아야한다.
        //둘다 안되면 2
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            int result = 2;
            char[] temp = br.readLine().toCharArray();
            int size = temp.length;
            int start = 0;
            int end = temp.length - 1;
            while (size > 0 && start!=end) {
                if (temp[start] == temp[end]) {
                    size -= 2;
                    start++;
                    end--;
                } else {
                    break;
                }
            }
            if (size == 0 || size == 1)
                result = 0;
            else if (temp[start + 1] == temp[end] || temp[start] == temp[end - 1]) {
                if (solve(temp, size - 1, start + 1, end) || solve(temp, size - 1, start, end - 1)) {
                    result = 1;
                }
            }
            bw.write(result + "\n");
        }
        //pqbcbqpq

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean solve(char[] temp, int size, int start, int end) {
        while (size > 0) {
            if (temp[start] == temp[end]) {
                size -= 2;
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}


