import java.lang.*;
import java.util.*;
import java.io.*;

class Solution {
    public int minX,minY,maxX,maxY;
    public int[] solution(String[] wallpaper) {
        int[] answer = {100,100,0,0};
        int num=wallpaper.length;
        for(int i=0; i<num;i++){
            String []temp = wallpaper[i].split("");
            int num2= temp.length;
            for(int j=0; j<num2;j++){
                if(temp[j].equals("#")){
                    answer[0]=Integer.min(answer[0],i);
                    answer[1]=Integer.min(answer[1],j);
                    answer[2]=Integer.max(answer[2],i+1);
                    answer[3]=Integer.max(answer[3],j+1);
                }
            }
        }
        
        
        
        return answer;
    }
}