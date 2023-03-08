import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    public static HashMap<String,String> hashMap;
    public static List<Node> list;
    public static class Node{
        String id,act;

        public Node(String id, String act) {
            this.id = id;
            this.act = act;
        }
    }
    public String[] solution(String[] record) throws IOException{
        String[] answer = {};
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        for(String i : record){
            String []temp = i.split(" ");
            if(temp[0].equals("Enter")){
                hashMap.put(temp[1],temp[2]);
                list.add(new Node(temp[1],"님이 들어왔습니다."));
            }else if(temp[0].equals("Leave")){
                list.add(new Node(temp[1],"님이 나갔습니다."));
            }else{
                hashMap.put(temp[1],temp[2]);
            }
        }
        answer = new String[list.size()]; 
        for(int i=0; i<list.size(); i++){
            answer[i] = hashMap.get(list.get(i).id)+list.get(i).act;
        }
        return answer;
    }
}