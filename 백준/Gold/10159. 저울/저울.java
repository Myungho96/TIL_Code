import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
		public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		public static int N,M;
		public static int [][]Arr;
   	    public static void main(String[] args) throws IOException {
	        N = Integer.parseInt(br.readLine());
	        M = Integer.parseInt(br.readLine());
	        Arr = new int[M][2];
	        for(int i=0;i<M;i++) {
	        	String []temp = br.readLine().split(" ");
	        	Arr[i][0] = Integer.parseInt(temp[0]);
	        	Arr[i][1] = Integer.parseInt(temp[1]);
	        }
	        for(int i=1;i<=N;i++) {
	        	int result = bfs(i);
	        	bw.write(result-1+"\n");
	        }
	        bw.flush();
	        bw.close();
	        
	    }
   	    public static int bfs(int num) {
   	    	int cnt = N;
   	    	boolean []visited = new boolean[N+1];
   	    	Queue<Node> queue = new ArrayDeque<>();
   	    	queue.offer(new Node(num,0));
   	    	queue.offer(new Node(num,1));
   	    	visited[num] = true;
   	    	while(!queue.isEmpty()) {
   	    		Node node = queue.poll();
   	    		
   	    		for(int i=0;i<M;i++) {
   	    			if(Arr[i][0]==node.num && node.flag==1 && !visited[Arr[i][1]]) {//큰경우
   	    				visited[Arr[i][1]] = true;
   	    				cnt--;
   	    				queue.offer(new Node(Arr[i][1],1));
   	    			}
   	    			if(Arr[i][1]==node.num && node.flag==0 && !visited[Arr[i][0]]) {//작은경우
   	    				visited[Arr[i][0]] = true;
   	    				cnt--;
   	    				queue.offer(new Node(Arr[i][0],0));
   	    			}
   	    		}
   	    	}
   	    	
   	    	return cnt;
   	    }
   	    public static class Node{
   	    	int num,flag;
   	    	public Node(int num, int flag) {
   	    		this.num = num;
   	    		this.flag = flag;
   	    	}
   	    }
}
