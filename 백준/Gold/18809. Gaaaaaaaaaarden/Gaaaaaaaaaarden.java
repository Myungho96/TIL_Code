import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int g = Integer.parseInt(temp[2]);
        int r = Integer.parseInt(temp[3]);
        arr = new int[n][m];
        List<Node> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if (arr[i][j] == 2)
                    lists.add(new Node(i, j));

            }
        }
        combG(0, -1, lists, new int[lists.size()], n, m, g, r);

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void combG(int cnt, int idx, List<Node> lists, int[] visited, int n, int m, int g, int r) {
        if (cnt == g) {
            combR(0, -1, lists, visited, n, m, g, r);
            return;
        }
        for (int i = idx + 1; i < lists.size(); i++) {
            visited[i] = 1;
            combG(cnt + 1, i, lists, visited, n, m, g, r);
            visited[i] = 0;
        }
    }

    static void combR(int cnt, int idx, List<Node> lists, int[] visited, int n, int m, int g, int r) {
        if (cnt == r) {
            bfs(lists, visited, n, m, g, r);
            return;
        }
        for (int i = idx + 1; i < lists.size(); i++) {
            if (visited[i] != 1) {
                visited[i] = 2;
                combR(cnt + 1, i, lists, visited, n, m, g, r);
                visited[i] = 0;
            }

        }
    }

//    private static void bfs(List<Node> lists, int[] check, int n, int m, int g, int r){
//        Queue<Node> q = new LinkedList<>();
//        Color[][] state = new Color[n][m];
//
//        // state 초기화
//        for(int i=0; i<n; i++)
//            for(int j=0; j<m; j++)
//                state[i][j] = new Color(0, 0);
//
//        // 배양지로 선택한 곳에 배양액 놓기
//        for(int i=0; i<check.length; i++){
//            if(check[i] == 1){
//                Node p = lists.get(i);
//                state[p.r][p.c] = new Color(3, 0);
//                q.offer(new Node(p.r, p.c));
//            }else if(check[i] == 2){
//                Node p = lists.get(i);
//                state[p.r][p.c] = new Color(4, 0);
//                q.offer(new Node(p.r, p.c));
//            }
//
//        }
//
//        int sum = 0;
//        // 위, 아래, 왼쪽, 오른쪽
//        int[] xdir = {-1,1,0,0};
//        int[] ydir = {0,0,-1,1};
//
//        while(!q.isEmpty()){
//            Node p =q.poll();
//            int x = p.r;
//            int y = p.c;
//            int curtime = state[x][y].cnt;
//            int curtype = state[x][y].color;
//            // 꽃이 핀 자리라면 퍼지지 않음
//            if(state[x][y].color == 5) continue;
//            // 4 방향으로 퍼트리기
//            for(int i=0; i<4; i++){
//                int dx = x + xdir[i];
//                int dy = y + ydir[i];
//                // 유효한 위치이고 호수가 아닌 경우
//                if(isValidPosition(dx, dy,n,m) && arr[dx][dy] != 0){
//                    // 아직 배양액이 퍼지지 않았다면 퍼트림
//                    if(state[dx][dy].color == 0){
//                        state[dx][dy] = new Color(curtype,curtime+1);
//                        q.offer(new Node(dx, dy));
//                    }
//                    // 빨간색이 있을때 초록색이 퍼지는 거라면 꽃을 피우고 count
//                    else if(state[dx][dy].color == 4){
//                        if(curtype == 3 && state[dx][dy].cnt == curtime + 1){
//                            sum++;
//                            state[dx][dy].color = 5;
//                        }
//                    }
//                    // 초록색이 있을때 빨간색이 퍼지는 거라면 꽃을 피우고 count
//                    else if(state[dx][dy].color == 3){
//                        if(curtype == 4 && state[dx][dy].cnt == curtime + 1){
//                            sum++;
//                            state[dx][dy].color = 5;
//                        }
//                    }
//                }
//            }
//        }
//        // max 값 update
//        Result = Result < sum ? sum : Result;
//    }
//    private static boolean isValidPosition(int x, int y, int row, int col){
//        if(x < 0 || y < 0 || x >= row || y >= col) return false;
//        return true;
//    }

    static void bfs(List<Node> lists, int[] check, int n, int m, int g, int r) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        Color[][] tempArr = new Color[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                tempArr[i][j] = new Color(0, 0);
        //3은 g, 4는 r
        for (int i = 0; i < lists.size(); i++) {
            if (check[i] != 0) {
                Node node = lists.get(i);
                visited[node.r][node.c] = true;
                queue.offer(new Node(node.r, node.c));
                tempArr[node.r][node.c].color += check[i] + 2;
            }
        }
        int total = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (tempArr[node.r][node.c].color == 5)
                continue;
            for (int i = 0; i < 4; i++) {
                int cr = node.r + Deltas[i][0];
                int cc = node.c + Deltas[i][1];

                if (cr >= 0 && cc >= 0 && cr < n && cc < m && arr[cr][cc] != 0) {
                    //다른 색 용액이 들어가 있고 cnt가 같은경우
                    if (((tempArr[cr][cc].color == 3 && tempArr[node.r][node.c].color == 4) || (tempArr[cr][cc].color == 4 && tempArr[node.r][node.c].color == 3)) && tempArr[cr][cc].cnt - 1 == tempArr[node.r][node.c].cnt) {
                        total++;
                        visited[cr][cc] = true;
                        tempArr[cr][cc].color = 5;
                    }
                    //배양가능한 공간일경우
                    if (!visited[cr][cc] && tempArr[cr][cc].color == 0) {
                        visited[cr][cc] = true;
                        tempArr[cr][cc] = new Color(tempArr[node.r][node.c].color, tempArr[node.r][node.c].cnt + 1);
                        queue.offer(new Node(cr, cc));
                    }
                }
            }
        }

        Result = Math.max(Result, total);

    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Color {
        int color, cnt;

        public Color(int color, int cnt) {
            this.color = color;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Color{" +
                    "color=" + color +
                    ", cnt=" + cnt +
                    '}';
        }
    }

}
