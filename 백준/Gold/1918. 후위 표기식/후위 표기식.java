import java.io.*;
import java.util.Stack;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<Character> stack;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        String[] temp = br.readLine().split("");

        stack = new Stack<>();

        for (int i = 0; i < temp.length; i++) {
            char current = temp[i].charAt(0);

            switch (current) {
                case '(':
                    stack.add(current);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(current)) {
                        sb.append(stack.pop());
                    }
                    stack.add(current);
                    break;
                default:
                    sb.append(current);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        bw.write(sb + "\n");
        bw.flush();
        bw.close();

    }

    public static int priority(char operator) {

        if (operator == '(' || operator == ')') {
            return 0;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1;
    }

}