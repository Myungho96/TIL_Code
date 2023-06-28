import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static String Result = "NO";
    public static String[][] Arr;
    public static List<int[]> Lists;
    public static List<int[]> Teachers;
    public static int[][] Deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        String[] temp;
        N = Integer.parseInt(br.readLine());
        Lists = new ArrayList<>();
        Teachers = new ArrayList<>();
        Arr = new String[N][N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                Arr[i][j] = temp[j];
                if (Arr[i][j].equals("X")) {
                    Lists.add(new int[]{i, j});
                } else if (Arr[i][j].equals("T")) {
                    Teachers.add(new int[]{i, j});
                }
            }
        }

        loop:
        for (int i = 0; i < Lists.size(); i++) {
            int[] num1 = Lists.get(i);
            Arr[num1[0]][num1[1]] = "O";
            for (int j = i + 1; j < Lists.size(); j++) {
                int[] num2 = Lists.get(j);
                Arr[num2[0]][num2[1]] = "O";
                for (int k = j + 1; k < Lists.size(); k++) {
                    int[] num3 = Lists.get(k);
                    Arr[num3[0]][num3[1]] = "O";
                    if (bfs()) {
                        Result = "YES";
                        break loop;
                    }

                    Arr[num3[0]][num3[1]] = "X";
                }
                Arr[num2[0]][num2[1]] = "X";
            }
            Arr[num1[0]][num1[1]] = "X";
        }

        bw.write(Result + "\n");
        bw.flush();
        bw.close();


    }

    public static boolean bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < Teachers.size(); i++) {
            int[] temp = Teachers.get(i);
            queue.offer(new int[]{temp[0], temp[1]});
            visited[temp[0]][temp[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cr = temp[0];
                int cc = temp[1];
                while (true) {
                    cr += Deltas[i][0];
                    cc+= Deltas[i][1];
                    if (cr >= 0 && cc >= 0 && cr < N && cc < N) {
                        if(Arr[cr][cc].equals("S"))
                            return false;
                        else if(Arr[cr][cc].equals("O"))
                            break;
                    } else {
                        break;
                    }

                }
            }
        }
        return true;
    }


}

