import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String origin, target;
    static Stack<Character> stack;
    static String result;


    public static void main(String[] args) throws IOException {
        origin = br.readLine();
        target = br.readLine();
        stack = new Stack<>();
        for (int i = 0; i < origin.length(); i++) {
            stack.push(origin.charAt(i));

            if (stack.size() >= target.length()) {
                boolean flag = true;
                for (int j = 0; j < target.length(); j++) {
                    if (stack.get(stack.size() - target.length() + j) != target.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < target.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        result = "";
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        bw.write(sb.length() > 0 ? sb.toString() : "FRULA");
        bw.flush();
        bw.close();

    }

}