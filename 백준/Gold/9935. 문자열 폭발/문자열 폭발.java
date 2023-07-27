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
        for (int i = 0; i < s.length; i++) {
            stack.add(s[i]);

            if (stack.size() >= t.length) {
                boolean flag = false;
                for (int j = 0; j < t.length; j++) {
                    if(!stack.get(stack.size()-t.length+j).equals(t[j])){
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    int cnt=0;
                    while(cnt++!=t.length)
                        stack.pop();
                }
            }
        }

        bw.write((stack.size() > 0 ? mergeStr(stack) : "FRULA") + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static String mergeStr(Stack<String> stack){
        StringBuilder sb = new StringBuilder("");
        while(!stack.isEmpty())
            sb.append(stack.pop());
        return sb.reverse().toString();
    }
}