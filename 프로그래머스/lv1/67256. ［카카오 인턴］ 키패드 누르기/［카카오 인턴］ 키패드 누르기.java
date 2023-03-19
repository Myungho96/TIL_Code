import java.lang.*;
import java.util.*;
import java.io.*;


class Solution {
    public static int [][]arr = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
    public static int [][]deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    public boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<4 && c<3;
    }
        
    public class Node{
        int r,c,cnt;
        public Node(int r,int c,int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    };
    public int bfs(int hand, int end){
        int cnt = 0;
        Queue<Node> queue = new ArrayDeque<>();
        boolean [][]visited = new boolean[4][3];
        if(hand==0)
            hand=11;
        if(end==0)
            end=11;
        // System.out.println(hand);
        //array에서 위치 찾아서 큐에 넣기
        for(int i=0;i<4;i++)
            for(int j=0;j<3;j++)
                if(arr[i][j]==hand){
                    queue.offer(new Node(i,j,0));
                    visited[i][j] = true;
                }
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(arr[node.r][node.c] == end){
                cnt= node.cnt;
                break;
            }
            //사방탐색
            for(int i=0; i<4; i++){
                int cr = node.r+deltas[i][0];
                int cc = node.c+deltas[i][1];
                if(isIn(cr,cc) && !visited[cr][cc]){
                    visited[cr][cc]=true;
                    queue.offer(new Node(cr,cc,node.cnt+1));
                }
            }
                
        }
                    
        
        return cnt;
    }
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int leftHand=10;
        int rightHand=12;
        // System.out.println("hello");
        if(hand.equals("right")){//오른손잡이
            // System.out.println("오른손잡이");
            for(int num:numbers){
                if(num==1 || num==4 || num==7){
                    answer+="L";
                    leftHand = num;
                }else if(num==3 || num==6 || num==9){
                    answer+="R";
                    rightHand=num;
                }else{//거리계산 해줘야함. bfs로 해줘야할듯.
                    int leftCnt = bfs(leftHand,num);
                    int rightCnt = bfs(rightHand,num);
                    if(leftCnt>=rightCnt){
                        answer+="R";
                        rightHand=num;
                    }else{
                        answer+="L";
                    leftHand = num;
                    }
                }
            }
        }else{//왼손잡이
            // System.out.println("왼손잡이");
            for(int num:numbers){
                if(num==1 || num==4 || num==7){
                    answer+="L";
                    leftHand = num;
                }else if(num==3 || num==6 || num==9){
                    answer+="R";
                    rightHand=num;
                }else{//거리계산 해줘야함. bfs로 해줘야할듯.
                    int leftCnt = bfs(leftHand,num);
                    int rightCnt = bfs(rightHand,num);
                    if(leftCnt>rightCnt){
                        answer+="R";
                        rightHand=num;
                    }else{
                        answer+="L";
                    leftHand = num;
                    }
                }
            }
        }
        return answer;
    }
}