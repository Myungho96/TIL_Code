import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;

    public static void main(String[] args) throws IOException {
        int sum = 0;
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }
        if (N == 1)
            sum = 0;
        while (queue.size() > 1) {
            int temp = queue.poll() + queue.poll();
            sum = sum + temp;
            queue.offer(temp);
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
    }


}

