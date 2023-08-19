import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int totalMax = 0;
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                totalMax = Math.max(totalMax, arr[i][j]);
            }
        }
        dfs(0, totalMax, n, arr);
        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt, int max, int n, int[][] arr) {
        //백트래킹 -> 내가 가진 max가 cnt에서 남은 횟수만큼 max를 제곱해도 totalMax보다 작다면 가능성 없음
        if (Math.pow(max, 5 - cnt + 1) <= Result)
            return;
        if (cnt == 5) {
            Result = Math.max(Result, max);
            return;
        }

        //arr 복사해서 쓰기.
        int[][] tempArr;
        //상하좌우 이동시키고 DFS
        for (int i = 0; i < 4; i++) {
            tempArr = new int[n][n];
//            copyArr(arr, tempArr, n);
            int temp = solve(i, arr, tempArr, n);
            dfs(cnt + 1, Math.max(temp, max), n, tempArr);
        }

    }

    private static int solve(int signal, int[][] arr, int[][] tempArr, int n) {
        int max = 0;//상하좌우
        Queue<Integer>[] queue = new Queue[n];
        boolean [][]visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            queue[i] = new LinkedList<>();
        if (signal == 0) {
            //상
            //큐 만들어서 각 열의 0행부터 n행까지 쫙 넣구 뽑아가면서 직전에 나온 수랑 같으면 합쳐주고, 공백이면 빼주고 아니면 채워준다.
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    queue[j].offer(arr[i][j]);
                }
            }
            for (int j = 0; j < n; j++) {
                int idx = 0;
                while (!queue[j].isEmpty()) {
                    int node = queue[j].poll();
                    if (node == 0)
                        continue;
                    if (idx == 0) {
                        tempArr[idx++][j] = node;
                    } else {
                        //직전께 합쳐지는지 체크
                        if (tempArr[idx - 1][j] == node && !visited[idx - 1][j]) {
                            tempArr[idx - 1][j] = tempArr[idx - 1][j] * 2;
                            visited[idx - 1][j] = true;
                        } else
                            tempArr[idx++][j] = node;
                    }
                }
            }
        } else if (signal == 1) {
            //하
            for (int j = 0; j < n; j++) {
                for (int i = n - 1; i >= 0; i--) {
                    queue[j].offer(arr[i][j]);
                }
            }//밑에 수정해야됨
            for (int j = 0; j < n; j++) {
                int idx = n - 1;
                while (!queue[j].isEmpty()) {
                    int node = queue[j].poll();
                    if (node == 0)
                        continue;
                    if (idx == n - 1) {
                        tempArr[idx--][j] = node;
                    } else {
                        //직전께 합쳐지는지 체크
                        if (tempArr[idx + 1][j] == node && !visited[idx + 1][j]) {
                            tempArr[idx + 1][j] = tempArr[idx + 1][j] * 2;
                            visited[idx + 1][j] = true;
                        } else
                            tempArr[idx--][j] = node;
                    }
                }
            }

        } else if (signal == 2) {
            //좌
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    queue[i].offer(arr[i][j]);
                }
            }
            for (int i = 0; i < n; i++) {
                int idx = 0;
                while (!queue[i].isEmpty()) {
                    int node = queue[i].poll();
                    if (node == 0)
                        continue;
                    if (idx == 0) {
                        tempArr[i][idx++] = node;
                    } else {
                        //직전께 합쳐지는지 체크
                        if (tempArr[i][idx - 1] == node && !visited[i][idx - 1]) {
                            tempArr[i][idx - 1] = tempArr[i][idx - 1] * 2;
                            visited[i][idx - 1] = true;
                        } else
                            tempArr[i][idx++] = node;
                    }
                }
            }

        } else {
            //우
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    queue[i].offer(arr[i][j]);
                }
            }//밑에 수정해야됨
            for (int i = 0; i < n; i++) {
                int idx = n - 1;
                while (!queue[i].isEmpty()) {
                    int node = queue[i].poll();
                    if (node == 0)
                        continue;
                    if (idx == n - 1) {
                        tempArr[i][idx--] = node;
                    } else {
                        //직전께 합쳐지는지 체크
                        if (tempArr[i][idx + 1] == node && !visited[i][idx + 1]) {
                            tempArr[i][idx + 1] = tempArr[i][idx + 1] * 2;
                            visited[i][idx + 1] = true;
                        } else
                            tempArr[i][idx--] = node;
                    }
                }
            }
        }
        //최댓값 찾아서 반환
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, tempArr[i][j]);
            }
        }
        return max;
    }

    private static void copyArr(int[][] arr, int[][] tempArr, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tempArr[i][j] = arr[i][j];
    }
}
