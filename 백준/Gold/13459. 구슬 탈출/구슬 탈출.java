import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, Result = 0;
    public static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static String[][] Arr;

    public static Node R, B, Goal;

    public static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static class Stage {
        Node red, blue;

        public Stage(Node red, Node blue) {
            this.red = red;
            this.blue = blue;
        }
    }


    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        Arr = new String[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                Arr[i][j] = temp[j];
                if (Arr[i][j].equals("R")) {
                    Arr[i][j] = ".";
                    R = new Node(i, j);
                } else if (Arr[i][j].equals("B")) {
                    Arr[i][j] = ".";
                    B = new Node(i, j);
                } else if (Arr[i][j].equals("O")) {
                    Goal = new Node(i, j);
                }
            }
        }
        //dfs로 접근하면 시간초과 나나보다
        //visited는 3차원으로 관리해야할듯
        bfs();
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        int cnt = 0;
        Queue<Stage> queue = new ArrayDeque<>();
        queue.offer(new Stage(R, B));
        while (!queue.isEmpty()) {
            if (cnt >= 10)
                return;
            cnt++;
            int size = queue.size();
            while (size-- > 0) {
                Stage current = queue.poll();

                //갈수있는 곳까지 사방탐색을 수행한다.
                for (int i = 0; i < 4; i++) {
                    if (Result == 1)
                        return;
                    int rr = current.red.i;
                    int rc = current.red.j;
                    int br = current.blue.i;
                    int bc = current.blue.j;
                    boolean flag = true;
                    while (flag) {
                        //1칸씩 가면서 가는길에 구멍을 만나면 R이면 return, B면 그 방향으로의 이동을 중단한다.
                        flag = false;
                        rr += Deltas[i][0];
                        rc += Deltas[i][1];
                        boolean checkR = false, checkB = false;
                        if (rr <= 0 || rc <= 0 || rr >= N - 1 || rc >= M - 1 || Arr[rr][rc].equals("#")) {
                            //더 못가는 상황!
                            rr -= Deltas[i][0];
                            rc -= Deltas[i][1];
                        } else {
                            flag = true;
                            checkR = true;

                        }
                        br += Deltas[i][0];
                        bc += Deltas[i][1];
                        if (br <= 0 || bc <= 0 || br >= N - 1 || bc >= M - 1 || Arr[br][bc].equals("#")) {
                            //더 못가는 상황!
                            br -= Deltas[i][0];
                            bc -= Deltas[i][1];
                        } else {
                            flag = true;
                            checkB = true;
                        }
                        //이동할 자리에 그 방향으로 한칸을 이동한다. 이동한 자리가 Goal이면 끝
                        if (checkR && !checkB) {
                            //B와 겹치는지 확인하고, 겹치면 롤백
                            if (br == rr && bc == rc) {
                                rr -= Deltas[i][0];
                                rc -= Deltas[i][1];
                                checkR = false;
                                flag = false;
                            }
                        } else if (checkB && !checkR) {
                            //R와 겹치는지 확인하고, 겹치면 롤백
                            if (br == rr && bc == rc) {
                                br -= Deltas[i][0];
                                bc -= Deltas[i][1];
                                checkB = false;
                                flag = false;
                            }
                        }
                        if (!checkB && !checkR && !(rr == current.red.i && rc == current.red.j && br == current.blue.i && bc == current.blue.j))
                            queue.offer(new Stage(new Node(rr, rc), new Node(br, bc)));
                        if (flag)
                            if (Arr[rr][rc].equals("O")) {
                                //B도 멈추기 전에 들어가는지 체크
                                if(!(rr - Deltas[i][0] == br && rc - Deltas[i][1] == bc))
                                    Result = 1;
//                                return;
                            } else if (Arr[br][bc].equals("O")) {
                                if (Result == 1)
                                    Result--;
                                break;
                            }
                    }


                }


            }
        }


    }

}

