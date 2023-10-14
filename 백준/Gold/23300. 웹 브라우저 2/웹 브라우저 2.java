import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int q = Integer.parseInt(temp[1]);
        Stack<Integer> back = new Stack<>();
        Stack<Integer> front = new Stack<>();
        int current = -1;
        int cnt = 0;
        while (cnt++ < q) {
            temp = br.readLine().split(" ");
            if (temp[0].equals("B")) {
                if (back.size() > 0) {
                    front.push(current);
                    current = back.pop();
                }
            } else if (temp[0].equals("F")) {
                if (front.size() > 0){
                    back.push(current);
                    current = front.pop();
                }


            } else if (temp[0].equals("C")) {
                if (back.size() > 0) {
                    Stack<Integer> tempStack = new Stack<>();
                    tempStack.push(back.pop());
                    while (!back.isEmpty()) {
                        int currentBack = back.pop();
                        if (currentBack != tempStack.peek()) {
                            tempStack.push(currentBack);
                        }
                    }

                    while (!tempStack.isEmpty()) {
                        back.push(tempStack.pop());
                    }
                }
            } else {
                front.clear();
                if (current != -1) back.push(current);
                current = Integer.parseInt(temp[1]);

            }
        }
        bw.write(current+"\n");
        if(!back.isEmpty()){
            while (!back.isEmpty()){
                bw.write(back.pop()+" ");
            }
            bw.write("\n");
        }else{
            bw.write("-1\n");
        }

        if(!front.isEmpty()){
            while (!front.isEmpty()){
                bw.write(front.pop()+" ");
            }
            bw.write("\n");
        }else{
            bw.write("-1\n");
        }

        bw.flush();
        bw.close();

    }


}