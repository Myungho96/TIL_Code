import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, C;
    public static List<Integer>[] Lists;
    public static List<Node>[] Cards;
    public static TreeSet<String> Result;

    public static class Node {
        String order, str;

        public Node(String order, String str) {
            this.order = order;
            this.str = str;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "order='" + order + '\'' +
                    ", str='" + str + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        Result = new TreeSet<>();
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        Lists = new List[N];
        Cards = new List[C];
        for (int i = 0; i < N; i++) {
            Lists[i] = new ArrayList<>();
            temp = br.readLine().split(" ");
            for (int j = 0; j < Integer.parseInt(temp[0]); j++) {
                Lists[i].add(Integer.parseInt(temp[j + 1]));
            }
        }
        for (int i = 0; i < C; i++) {
            Cards[i] = new ArrayList<>();
            temp = br.readLine().split(",");
            for (int j = 0; j < temp.length; j++) {
                String[] card = temp[j].split(" ");
                Cards[i].add(new Node(card[0], card[1]));
            }
        }
        dfs(0, new ArrayList<Integer>());
        for (String result : Result) {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void makeSentence(List<Integer> lists) {
        String answer = "";
        for (int i : lists) {
            List<Node> cards = Cards[i - 1];
            for (Node node : cards) {
                if (node.order.equals("ADD")) {
                    answer += node.str;
                } else {
                    //제거할 단어의 위치에 단어가 있는지 먼저 체크.
                    if (answer.length() <= Integer.parseInt(node.str)) {
                        Result.add("ERROR");
                        return;
                    } else {
                        //단어 제거
                        answer = answer.substring(0, Integer.parseInt(node.str)) + answer.substring(Integer.parseInt(node.str) + 1);
                    }
                }
            }
        }
        if(answer.length() == 0){
            Result.add("EMPTY");
            return;
        }
        Result.add(answer);
    }

    public static void dfs(int cnt, List<Integer> lists) {
        if (cnt == C) {
            //list에 쌓인 카드 순서대로 단어 만들기 수행하기 -> 결과값을 Set에 저장하기
            makeSentence(lists);
            return;
        }
        //유저를 순회하면서 맨 앞에 있는 카드를 제거하고 그 카드를 lists에 추가해 재귀해주기, 재귀 이후에는 다시 제거한 카드를 추가해주어야함
        for (int i = 0; i < N; i++) {
            if (Lists[i].size() != 0) {
                int temp = Lists[i].get(0);
                lists.add(temp);
                Lists[i].remove(0);
                dfs(cnt + 1, lists);
                lists.remove(Integer.valueOf(temp));
                Lists[i].add(0, temp);
            }


        }
    }

}

