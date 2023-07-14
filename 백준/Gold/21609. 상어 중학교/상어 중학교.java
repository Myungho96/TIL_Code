import java.lang.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, TempCnt, Result = 0, rainbowBlock;
    public static Node maxNode;
    static boolean[][] visited;
    static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] arr;

    public static class Node {
        int r, c, cnt, rainbowBlock;

        public Node(int r, int c, int cnt, int rainbowBlock) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.rainbowBlock = rainbowBlock;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(temp[j]);

        }

        while (true) {
            maxNode = new Node(-1, -1, -1, 0);
            visited = new boolean[N][N];
            //1. 가장 큰 블록을 찾는다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && arr[i][j] > 0) {

                        TempCnt = 1;
                        rainbowBlock = 0;
                        visited[i][j] = true;
                        dfs(i, j, arr[i][j]);

                        for (int k = 0; k < N; k++)
                            for (int l = 0; l < N; l++) {
                                if (arr[k][l] == 0 && visited[k][l]) {
                                    visited[k][l] = false;
                                }
                            }

                        if (TempCnt > 1 && TempCnt >= maxNode.cnt) {
                            if (TempCnt == maxNode.cnt) {
                                //무지개 색이 더 많은 쪽 선택
                                if (rainbowBlock == maxNode.rainbowBlock) {//무지개 색 블록이 같으면 행이랑 열 비교해서 더 큰걸 선택!
                                    if (maxNode.r == i)
                                        maxNode = j > maxNode.c ? new Node(i, j, TempCnt, rainbowBlock) : maxNode;
                                    else
                                        maxNode = i > maxNode.r ? new Node(i, j, TempCnt, rainbowBlock) : maxNode;
                                } else
                                    maxNode = rainbowBlock > maxNode.rainbowBlock ? new Node(i, j, TempCnt, rainbowBlock) : maxNode;

                            } else
                                maxNode = new Node(i, j, TempCnt, rainbowBlock);
                        }
                    }
                }
            }
            visited = new boolean[N][N];

            //2. 그룹이 있는지를 체크해서 점수를 획득한다.
            if (maxNode.cnt != -1) {
                change(maxNode, arr[maxNode.r][maxNode.c]);
                Result += maxNode.cnt * maxNode.cnt;
                //3. 중력 작용하게 하기
                move();
                //4. 격자를 90도 반시계방향으로 회전하기
                int[][] tempArr = new int[N][N];
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++) {
                        tempArr[N - 1 - j][i] = arr[i][j];
                    }
                arr = tempArr;
                //5. 중력 작용하게 하기
                move();
            } else {
                break;
            }
        }
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int r, int c, int num) {
        for (int i = 0; i < 4; i++) {
            int cr = r + Deltas[i][0];
            int cc = c + Deltas[i][1];
            if (cr >= 0 && cc >= 0 && cr < N && cc < N && !visited[cr][cc] && (arr[cr][cc] == num || arr[cr][cc] == 0)) {
                visited[cr][cc] = true;
                TempCnt++;
                if (arr[cr][cc] == 0)
                    rainbowBlock++;
//                arr[cr][cc] = num;
                dfs(cr, cc, num);
            }
        }
    }

    public static void move() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > 0; i--) {

                if (arr[i][j] == -2) {//빈 공간이면
                    int num = i;
                    while (num > 0) {
                        num--;
                        if (arr[num][j] == -1) {
                            break;
                        } else if (arr[num][j] != -2) {
                            arr[i][j] = arr[num][j];
                            arr[num][j] = -2;
                            break;
                        }
                    }


                }
            }
        }
    }

    public static void change(Node node, int num) {
        for (int i = 0; i < 4; i++) {
            int cr = node.r + Deltas[i][0];
            int cc = node.c + Deltas[i][1];
            if (cr >= 0 && cc >= 0 && cr < N && cc < N && !visited[cr][cc] && (arr[cr][cc] == num || arr[cr][cc] == 0)) {
                visited[cr][cc] = true;
                arr[cr][cc] = -2;
                change(new Node(cr, cc, 0, 0), num);
            }
        }
    }
}

