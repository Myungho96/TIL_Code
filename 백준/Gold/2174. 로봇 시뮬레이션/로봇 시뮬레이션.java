//package test;

import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
		public static class Node{
			int r,c,dir;
			public Node(int r, int c, int dir) {
				this.r = r;
				this.c = c;
				this.dir = dir;
			}
		}
//		public static int [][]Arr;
		public static List<Node> NodeLists;
		public static HashMap<String,Integer> Dir;
		public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		public static StringTokenizer st;
		public static int A,B,N,M,flag;
   	    public static void main(String[] args) throws IOException {
   	    	NodeLists = new ArrayList<>();
   	    	Dir = new HashMap<>();
   	    	Dir.put("N",1);
   	    	Dir.put("E",2);
   	    	Dir.put("S",3);
   	    	Dir.put("W",4);
   	       st = new StringTokenizer(br.readLine());
 	       A = Integer.parseInt(st.nextToken());
 	       B = Integer.parseInt(st.nextToken());
// 	       Arr = new int[A+1][B+1];
 	       
   	       st = new StringTokenizer(br.readLine());
	       N = Integer.parseInt(st.nextToken());
	       M = Integer.parseInt(st.nextToken());
	       
	       for(int i=0;i<N;i++) {
	    	   st = new StringTokenizer(br.readLine());
	    	   int a = Integer.parseInt(st.nextToken());
	 	       int b = Integer.parseInt(st.nextToken());
//	 	       Arr[a][b] = Dir.get(st.nextToken());
	 	       NodeLists.add(new Node(a,b,Dir.get(st.nextToken())));
	       }
loop:	       for(int i=0;i<M;i++) {
				flag=0;
	    	   st = new StringTokenizer(br.readLine());
	    	   int a = Integer.parseInt(st.nextToken());
	 	       String b = st.nextToken();
	    	   int c = Integer.parseInt(st.nextToken());
	    	   if("F".equals(b)) {
	    		   //벽에 부딫히는지 체크
	    		   switch(NodeLists.get(a-1).dir) {
	    		   case 1:
	    			   //벽에 부딫히는지 체크
	    			   int temp1=987654321;
	    			   int temp2=987654321;
	    			   int node = 987654321;
	    			   if(NodeLists.get(a-1).c+c>B) {
	    				   temp1=B-NodeLists.get(a-1).c+1;
	    				   flag=1;
	    			   }
	    			   //로봇에 부딫히는지 체크
	    			   for(int j=0;j<N;j++) {
	    				   if(j==a-1)
	    					   continue;
	    				   if(NodeLists.get(a-1).r==NodeLists.get(j).r && NodeLists.get(a-1).c<=NodeLists.get(j).c && NodeLists.get(a-1).c+c>=NodeLists.get(j).c) {
	    					   if(Math.abs(NodeLists.get(a-1).c-NodeLists.get(j).c)<temp2) {
	    						   temp2=Math.abs(NodeLists.get(a-1).c-NodeLists.get(j).c);
		    					   node = j+1; 
		    					   flag=1;
	    					   }
	    					   
	    				   }
	    			   }
	    			   if(flag!=1) {
	    				   NodeLists.get(a-1).c+=c;
	    			   }else {
	    				   if(temp1<temp2) {
	    					   bw.write("Robot "+a+" crashes into the wall"+"\n");
	    				   }else {
	    					   bw.write("Robot "+a+" crashes into robot "+node+"\n");
	    				   }
	    				   break loop;
	    			   }
	    			   break;
	    		   case 2:
	    			   temp1=987654321;
	    			   temp2=987654321;
	    			   node = 987654321;
	    			   if(NodeLists.get(a-1).r+c>A) {
	    				   temp1=A-NodeLists.get(a-1).r+1;
	    				   flag=1;
	    			   }
	    			   for(int j=0;j<N;j++) {
	    				   if(j==a-1)
	    					   continue;
	    				   if(NodeLists.get(a-1).c==NodeLists.get(j).c && NodeLists.get(a-1).r<=NodeLists.get(j).r && NodeLists.get(a-1).r+c>=NodeLists.get(j).r) {
	    					   if(Math.abs(NodeLists.get(a-1).r-NodeLists.get(j).r)<temp2) {
	    						   temp2=Math.abs(NodeLists.get(a-1).r-NodeLists.get(j).r);
		    					   node = j+1;
		    					   flag=1;
	    					   }
	    				
	    				   }
	    			   }
	    			   if(flag!=1) {
	    				   NodeLists.get(a-1).r+=c;
	    			   }else {
	    				   if(temp1<temp2) {
	    					   bw.write("Robot "+a+" crashes into the wall"+"\n");
	    				   }else {
	    					   bw.write("Robot "+a+" crashes into robot "+node+"\n");
	    				   }
	    				   break loop;
	    			   }
	    			   break;
	    		   case 3:
	    			   temp1=987654321;
	    			   temp2=987654321;
	    			   node = 987654321;
	    			   if(NodeLists.get(a-1).c-c<=0) {
	    				   temp1=NodeLists.get(a-1).c;
	    				   flag=1;
	    			   }
	    			   for(int j=0;j<N;j++) {
	    				   if(j==a-1)
	    					   continue;
	    				   if(NodeLists.get(a-1).r==NodeLists.get(j).r && NodeLists.get(a-1).c>=NodeLists.get(j).c && NodeLists.get(a-1).c-c<=NodeLists.get(j).c) {
	    					   if(Math.abs(NodeLists.get(a-1).c-NodeLists.get(j).c)<temp2) {
	    						   temp2=Math.abs(NodeLists.get(a-1).c-NodeLists.get(j).c);
		    					   node = j+1; 
		    					   flag=1;
	    					   }
	    				   }
	    			   }
	    			   if(flag!=1) {
	    				   NodeLists.get(a-1).c-=c;
	    			   }else {
	    				   if(temp1<temp2) {
	    					   bw.write("Robot "+a+" crashes into the wall"+"\n");
	    				   }else {
	    					   bw.write("Robot "+a+" crashes into robot "+node+"\n");
	    				   }
	    				   break loop;
	    			   }
	    			   break;
	    		   case 4:
	    			   temp1=987654321;
	    			   temp2=987654321;
	    			   node = 987654321;
	    			   if(NodeLists.get(a-1).r-c<=0) {
	    				   temp1=NodeLists.get(a-1).r;
	    				   flag=1;
	    			   }
	    			   for(int j=0;j<N;j++) {
	    				   if(j==a-1)
	    					   continue;
	    				   if(NodeLists.get(a-1).c==NodeLists.get(j).c && NodeLists.get(a-1).r>=NodeLists.get(j).r && NodeLists.get(a-1).r-c<=NodeLists.get(j).r) {
	    					   if(Math.abs(NodeLists.get(a-1).r-NodeLists.get(j).r)<temp2) {
	    						   temp2=Math.abs(NodeLists.get(a-1).r-NodeLists.get(j).r);
		    					   node = j+1;
		    					   flag=1;
	    					   }
	    				   }
	    			   }
	    			   if(flag!=1) {
	    				   NodeLists.get(a-1).r-=c;
	    			   }else {
	    				   if(temp1<temp2) {
	    					   bw.write("Robot "+a+" crashes into the wall"+"\n");
	    				   }else {
	    					   bw.write("Robot "+a+" crashes into robot "+node+"\n");
	    				   }
	    				   break loop;
	    			   }
	    			   break;
	    		   }
	    	   }else if("L".equals(b)) {
	    		   NodeLists.get(a-1).dir-=c%4;
	    		   if(NodeLists.get(a-1).dir <= 0) {
	    			   NodeLists.get(a-1).dir+=4;
	    		   }
	    	   }else {//R
	    		   NodeLists.get(a-1).dir+=c%4;
	    		   if(NodeLists.get(a-1).dir >= 5) {
	    			   NodeLists.get(a-1).dir-=4; 
	    		   }
	 	       
	    	   }
	       }
			if(flag!=1) {
				   bw.write("OK\n");
			}
	       bw.flush();
	       bw.close();
   	    }
}
