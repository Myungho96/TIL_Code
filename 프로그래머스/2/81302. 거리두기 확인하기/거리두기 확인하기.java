import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int[][]deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    public int[] solution(String[][] places) {
        //맨해튼 거리 체크한 뒤, 2이하이면 파티션이 있는지 추가로 체크해서 앉을 수 있는지 없는지 체크
        //파티션 체크 때 대각선으로 앉았는지를 체크해줘야할듯
        int []answer = {1,1,1,1,1};
        for(int i=0;i<5;i++){
            List<int[]> lists = new ArrayList<>();
            
            for(int j=0;j<5;j++)
                for(int k=0;k<5;k++)
                    if(places[i][j].charAt(k) == 'P'){
                        lists.add(new int[]{j,k});
                    }
loop:            for(int x = 0; x< lists.size();x++){
                for(int y=x+1;y<lists.size();y++){
                    if(Math.abs(lists.get(x)[0]-lists.get(y)[0]) + 
                       Math.abs(lists.get(x)[1]-lists.get(y)[1])==1){
                        answer[i]=0;
                        break loop;
                        
                    }
                    else if(Math.abs(lists.get(x)[0]-lists.get(y)[0]) + 
                       Math.abs(lists.get(x)[1]-lists.get(y)[1])==2){
                        //벽 체크
                        if(Math.abs(lists.get(x)[0]-lists.get(y)[0]) == 2 
                           && places[i][(lists.get(x)[0]+lists.get(y)[0])/2].charAt(lists.get(x)[1]) != 'X'){
                            //사이에 벽이 있으면 끝
                            answer[i]=0;
                            break loop;
                        }else if(Math.abs(lists.get(x)[1]-lists.get(y)[1]) == 2
                                && places[i][lists.get(x)[0]].charAt((lists.get(x)[1]+lists.get(y)[1])/2) != 'X'){
                            //사이에 벽이 있으면 끝
                            answer[i]=0;
                            break loop;
                        }else if(Math.abs(lists.get(x)[0]-lists.get(y)[0]) == 1 
                            && Math.abs(lists.get(x)[1]-lists.get(y)[1])==1){
                            if(places[i][lists.get(x)[0]].charAt(lists.get(y)[1]) != 'X' ||
                              places[i][lists.get(y)[0]].charAt(lists.get(x)[1]) != 'X'){
                                answer[i]=0;
                                break loop;
                            }
                            
                        
                        }
                    }
                }
            }
        }
        
        
        
        return answer;
    }
}