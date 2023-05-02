

import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
		public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		public static int N,cnt;
   	    public static void main(String[] args) throws IOException {
   	    	N = Integer.parseInt(br.readLine());
   	    	String []start = br.readLine().split("");
   	    	String []end = br.readLine().split("");
   	    	cnt=987654321;
   	    	sol(0,start,end);
   	    	sol(1,start,end);
   	    	if(cnt==987654321) {
   	    		bw.write("-1\n");
   	    	}	
   	    	else{
   	    		bw.write(cnt+"\n");
   	    	}
   	    	bw.flush();
   	    	bw.close();
   	    	
   	    	
   	    }
   	    
   	    public static boolean sol(int type, String []start,String []end) {
   	    	String []arr = start.clone();
   	    	int currentCnt=0;
   	    	if(type==1) {//첫번째 스위치 킨 경우
   	    		arr[0] = arr[0].equals("0") ?"1":"0";
				arr[1] = arr[1].equals("0") ?"1":"0";
				currentCnt++;
   	    	}
   	    	
   	    	for(int i=1;i<N;i++) {
	    			//i-1이 end와 같지 않은 경우 switch한다.
	    			if(!arr[i-1].equals(end[i-1])) {
	    				arr[i-1] = arr[i-1].equals("0") ?"1":"0";
	    				arr[i] = arr[i].equals("0") ?"1":"0";
	    				if(i+1<N) {
	    					arr[i+1] = arr[i+1].equals("0") ?"1":"0";
	    				}
	    				currentCnt++;
	    			}
	    		}
	    		if(!arr[N-1].equals(end[N-1]))
	    			return false;
   	    	cnt = Math.min(currentCnt,cnt);
   	    	return true;
   	    }
}
