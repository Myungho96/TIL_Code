import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split("");
        String[] t = br.readLine().split("");
        Stack<String> stack = new Stack<>();
        for (String str : t) {
            stack.add(str);
        }
        while (s.length != stack.size()) {
            String str = stack.pop();
            if(str.equals("B")){
                Stack<String> temp = new Stack<>();
                while(!stack.isEmpty()){
                    temp.add(stack.pop());
                }
                stack=temp;
            }
        }
        boolean flag = true;
        for (int i = s.length - 1; i >= 0; i--) {
            if (!s[i].equals(stack.pop())) {
                flag = false;
                break;
            }
        }
        bw.write((flag ? 1 : 0) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}