import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String args[]) throws IOException{
      int n = Integer.parseInt(br.readLine());
      PriorityQueue<Long> pq = new PriorityQueue<>();
      Node []arr = new Node[n];
      for(int i=0;i<n;i++){
          String []temp = br.readLine().split(" ");
          arr[i] = new Node(Long.parseLong(temp[0]),Long.parseLong(temp[1]));
      }
      Arrays.sort(arr);
      pq.offer(arr[0].end);
      for (int i = 1; i < n; i++) {
			if (arr[i].start >= pq.peek())
				pq.poll();
	        pq.offer(arr[i].end);
	  }

	  bw.write(pq.size() + "\n");
      bw.flush();
      bw.close();
      
    }
    static class Node implements Comparable<Node>{
        long start, end;
        Node(long start, long end){
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Node o){
            if(this.start==o.start)
                return Long.compare(this.end,o.end);
            return Long.compare(this.start,o.start);
            
        }
    }
}