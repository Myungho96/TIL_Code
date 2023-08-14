import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result = 0;
    static int[][] Deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        Deque<Integer>[][] map = new Deque[n][n];
        int[][] arrColor = new int[n][n];
        int[][] arr = new int[k][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                map[i][j] = new LinkedList<>();
            }
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arrColor[i][j] = Integer.parseInt(temp[j]);
            }
        }
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(temp[j]) - 1;
            }
            map[arr[i][0]][arr[i][1]].offer(i);
        }
        int cnt = 0;
        loop:
        while (true) {
            if (cnt > 1000) {
                Result = -1;
                break loop;
            }
            //4개 쌓인 칸이 있는지 체크 -> 있으면 종료
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].size() >= 4) {
                        Result = cnt;
                        break loop;
                    }
                }
            }
            cnt++;

            //0번 말부터 움직인다.
            for (int i = 0; i < k; i++) {
                //가야하는 칸이 뭔지 체크
                int cr = arr[i][0] + Deltas[arr[i][2]][0];
                int cc = arr[i][1] + Deltas[arr[i][2]][1];
                //가야하는 칸이 파란색이라면(또는 범위를 벗어났다면)
                if (cr < 0 || cc < 0 || cr >= n || cc >= n || arrColor[cr][cc] == 2) {
                    //방향 바꾼 후,
                    arr[i][2] = arr[i][2] % 2 == 1 ? arr[i][2] - 1 : arr[i][2] + 1;
                    cr = arr[i][0] + Deltas[arr[i][2]][0];
                    cc = arr[i][1] + Deltas[arr[i][2]][1];
                    //가야할 곳이 파란색이면 그냥 냅둔다.
                    if (cr < 0 || cc < 0 || cr >= n || cc >= n || arrColor[cr][cc] == 2)
                        continue;
                }
                if(arrColor[cr][cc] == 1){
                    while(true){
                        int node = map[arr[i][0]][arr[i][1]].pollLast();
                        map[cr][cc].offer(node);
                        arr[node][0] = cr;
                        arr[node][1] = cc;
                        if(node == i){
                            break;
                        }

                    }
                }else{
                    Stack<Integer> stack = new Stack<>();
                    while(true){
                        int node = map[arr[i][0]][arr[i][1]].pollLast();
                        stack.push(node);
                        arr[node][0] = cr;
                        arr[node][1] = cc;
                        if(node == i){
                            break;
                        }

                    }
                    while(!stack.isEmpty()){
                        map[cr][cc].offer(stack.pop());
                    }

                }
                if(map[cr][cc].size() >= 4){
                    Result = cnt;
                    break loop;
                }

            }

        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();

    }


}