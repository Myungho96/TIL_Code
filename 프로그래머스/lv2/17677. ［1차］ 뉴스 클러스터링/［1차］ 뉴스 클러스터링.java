import java.lang.*;
import java.util.*;
import java.io.*;

class Solution {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public int solution(String str1, String str2) throws IOException {
        int answer = 0;
        List<String> str1SetList = new ArrayList<>();
        List<String> str2SetList = new ArrayList<>();
        Map<String,Integer> str1Map = new HashMap<>();
        Map<String,Integer> str2Map = new HashMap<>();
        String []temp1 = str1.split("");
        String []temp2 = str2.split("");
        // System.out.println(Arrays.toString(temp1));
        // 다중집합 만들기
        for(int i=0;i<temp1.length-1;i++){
            //두개 다 알파벳인지 체크하고, 맞으면
            if(isApbBool(temp1[i]) && isApbBool(temp1[i+1])){
                str1SetList.add(isApb(temp1[i])+isApb(temp1[i+1]));    
            }
            
        }
        // System.out.println(str1SetList.toString());
        for(int i=0;i<temp2.length-1;i++){
            if(isApbBool(temp2[i]) && isApbBool(temp2[i+1])){
                str2SetList.add(isApb(temp2[i])+isApb(temp2[i+1]));    
            }
        }
        // System.out.println(str2SetList.toString());
        //Map에 넣으면서 숫자 세기 -> try-catch로 했던 것 같음
        for(String s : str1SetList){
            try{
               str1Map.put(s,str1Map.get(s)+1); 
            }catch(Exception e){
                str1Map.put(s,1);
            }
        }
        // System.out.println(str1Map.toString());
        for(String s : str2SetList){
            try{
               str2Map.put(s,str2Map.get(s)+1); 
            }catch(Exception e){
                str2Map.put(s,1);
            }
        }
        // System.out.println(str2Map.toString());
        //합집합,교집합 숫자 구하기
        //합집합 -> get이 되면 키 비교해서 더 큰거 넣어줘야할듯.
        int max = 0;
        int min = 0;
        for(String s : str1Map.keySet()){
            try{
                int s2 = str2Map.get(s); 
                int s1 = str1Map.get(s);
                max+=Math.max(s1,s2);
                min+=Math.min(s1,s2);
            }catch(Exception e){
                max+=str1Map.get(s);
            }
        }
        for(String s : str2Map.keySet()){
            try{
               int s1 = str1Map.get(s);
            }catch(Exception e){
                max+=str2Map.get(s);
            }
        }
        // System.out.println(max);
        // System.out.println(min);
        if(min==0 && max==0){
            return 65536;
        }
        
        answer = (int)(65536*((double)min/(double)max));
        return answer;
    }
    

    public static String isApb(String str){
        int value = (int)str.charAt(0);
        if(value>=97 && value<=122)
            value-=32;
        return String.valueOf((char)value);
    }
    //A:65 a: 97
    //Z:90 z:122
    public static Boolean isApbBool(String str){
        int value = (int)str.charAt(0);
        return (value>=97 && value<=122) || (value>=65 && value<=90);
    }
}