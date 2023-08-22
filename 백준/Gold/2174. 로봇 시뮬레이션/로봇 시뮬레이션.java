import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Map<String, Integer> dir = new HashMap<>();
    static int[][] Deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        dir.put("E", 0);
        dir.put("N", 1);
        dir.put("W", 2);
        dir.put("S", 3);
        String[] temp = br.readLine().split(" ");
        int m = Integer.parseInt(temp[0]);
        int n = Integer.parseInt(temp[1]);
        boolean[][] visited = new boolean[n + 1][m + 1];
        temp = br.readLine().split(" ");
        int robotNum = Integer.parseInt(temp[0]);
        int orderNum = Integer.parseInt(temp[1]);
        List<int[]> robots = new LinkedList<>();
        for (int i = 0; i < robotNum; i++) {
            temp = br.readLine().split(" ");
            visited[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = true;
            robots.add(new int[]{Integer.parseInt(temp[1]), Integer.parseInt(temp[0]), dir.get(temp[2])});
        }
        boolean flag = false;
        loop:
        for (int i = 0; i < orderNum; i++) {
            temp = br.readLine().split(" ");
            int target = Integer.parseInt(temp[0]) - 1;
            char command = temp[1].charAt(0);
            int repeat = Integer.parseInt(temp[2]);
            if (command == 'R') {
                robots.get(target)[2] = (robots.get(target)[2] - repeat % 4 + 4) % 4;
            } else if (command == 'L') {
                robots.get(target)[2] = (robots.get(target)[2] + repeat % 4) % 4;
            } else {
                int idx = 0;
                int[] robot = robots.get(target);
                int cr = robot[0];
                int cc = robot[1];

                while (idx++ < repeat) {
                    cr += Deltas[robot[2]][0];
                    cc += Deltas[robot[2]][1];
                    if (cr > 0 && cc > 0 && cr <= n && cc <= m) {
                        //다른 로봇이랑 충돌한 경우
                        if (visited[cr][cc]) {
                            for (int j = 0; j < robots.size(); j++) {
                                int[] find = robots.get(j);
                                if (find[0] == cr && find[1] == cc){
                                    flag = true;
                                    bw.write("Robot " + (target + 1) + " crashes into robot " + (j + 1) + "\n");
                                    break loop;
                                }

                            }


                        } else {
                            robots.get(target)[0]=cr;
                            robots.get(target)[1]=cc;
                            visited[cr][cc] = true;
                            visited[cr - Deltas[robot[2]][0]][cc - Deltas[robot[2]][1]] = false;
                        }
                    } else {
                        flag = true;
                        bw.write("Robot " + (target + 1) + " crashes into the wall\n");
                        break loop;
                    }
                }
            }
        }
        if(!flag)
            bw.write("OK\n");
        bw.flush();
        bw.close();
        br.close();
    }

}