

import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
		public static int N;
		public static boolean flag;
		public static boolean []visited;
		public static List<Node> []Maze;
		public static class Node{
			String str;
			int cash;
			List<Integer> roomNum;
			public Node(String str,int cash, List<Integer> roomNum) {
				this.str = str;
				this.cash = cash;
				this.roomNum = roomNum;
			}
		}
		public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   	    public static void main(String[] args) throws IOException {
   	    	while(true) {
   	    		flag = false;
   	    		N = Integer.parseInt(br.readLine());
   	    		if(N==0) {
   	    			bw.flush();
   	    	    	bw.close();
   	    	    	return;
   	    		}
   	   	    	Maze = new List[N+1];
   	   	    	visited = new boolean[N+1];
   	   	    	for(int i=1;i<N+1;i++) {
   	   	    		Maze[i] = new ArrayList<>();
   	   	    		String []temp = br.readLine().split(" ");
   	   	    		List<Integer> roomNum = new ArrayList<>();
   	   	    		for(int j=2;j<temp.length-1;j++) {
   	   	    			roomNum.add(Integer.parseInt(temp[j]));
   	   	    		}
   	   	    		Maze[i].add(new Node(temp[0],Integer.parseInt(temp[1]),roomNum));
//   	   	    		System.out.println(Maze[i].get(0).size());
   	   	    	}
   	   	    	dfs(1,0);
   	   	    	if(flag)
   	   	    		bw.write("Yes\n");
   	   	    	else
   	   	    	bw.write("No\n");
   	    	}
   	    	
   	    }
   	    public static boolean dfs(int currentRoom,int money) {
   	    	int currentMoney = money;
   	    	//방의 조건에 따라서 금액 조정하기
			if(Maze[currentRoom].get(0).str.equals("T")) {
				//통행료 못내면 더 못가야할듯?
				if(money-Maze[currentRoom].get(0).cash>=0) {
					//낼수 있으면, 차감한다.
					currentMoney-=Maze[currentRoom].get(0).cash;
				}else
					return false;
			}else if(Maze[currentRoom].get(0).str.equals("L")) {
				if(money<Maze[currentRoom].get(0).cash) {
					//낼수 있으면, 차감한다.
					currentMoney=Maze[currentRoom].get(0).cash;
				}
			}
			if(currentRoom==N) {
   	    		flag=true;
   	    		return true;
   	    	}
			//다른 방 방문하기
			for(int temp:Maze[currentRoom].get(0).roomNum) {
				if(!visited[temp]) {
					visited[temp]=true;
					if(dfs(temp,currentMoney)) {
						return true;
					}
					visited[temp]=false;
				}
			}
				
			
   	    	
   	    	
   	    	
   	    	
   	    	
   	    	return false;
   	    }
}
