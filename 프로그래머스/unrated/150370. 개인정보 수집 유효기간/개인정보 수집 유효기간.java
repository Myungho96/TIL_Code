import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        String[] todaySplit = today.split("\\.");
        for (int j = 0; j < todaySplit.length; j++) {
            todaySplit[j] = String.valueOf(Integer.parseInt(todaySplit[j]));
        }
        List<Integer> list = new ArrayList<>();
        HashMap<String, String> termsMap = new HashMap<>();
        for (String temp : terms) {
            StringTokenizer st = new StringTokenizer(temp);
            termsMap.put(st.nextToken(), st.nextToken());
        }
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            //날짜 분리하기
            String temp = st.nextToken();
            String[] dateSplit = temp.split("\\.");
            for (int j = 0; j < dateSplit.length; j++) {
                dateSplit[j] = String.valueOf(Integer.parseInt(dateSplit[j]));
            }
            //유효기간 받기
            String dateTerms = st.nextToken();
            //날짜에 유효기간 더하기
            int date = Integer.parseInt(termsMap.get(dateTerms));
            if (date >= 12) {
                dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) + date/12);
                date%=12;
                if(Integer.parseInt(dateSplit[1]) + date>12){
                    dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) + 1);
                    date-=12;
                }
                dateSplit[1] = String.valueOf(Integer.parseInt(dateSplit[1]) + date);
            }else{
                if(Integer.parseInt(dateSplit[1]) + date>12){
                    dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) + 1);
                    date-=12;
                }
                dateSplit[1] = String.valueOf(Integer.parseInt(dateSplit[1]) + date);
            }
            //현재 날짜와 비교해서 폐기 여부 판단
            //년도가 현재 날짜보다 작으면 무조건 파기
            if(Integer.parseInt(dateSplit[0])<Integer.parseInt(todaySplit[0])){
                list.add(i+1);
            }
            //년도가 같으면 달을 비교한다.
            else if (Integer.parseInt(dateSplit[0])==Integer.parseInt(todaySplit[0])) {
                if(Integer.parseInt(dateSplit[1])<Integer.parseInt(todaySplit[1])){
                    list.add(i+1);
                }
                //달도 같으면, 일을 비교한다.
                else if (Integer.parseInt(dateSplit[1])==Integer.parseInt(todaySplit[1])) {
                    if(Integer.parseInt(dateSplit[2])<=Integer.parseInt(todaySplit[2])){
                        list.add(i+1);
                    }
                }
            }
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}