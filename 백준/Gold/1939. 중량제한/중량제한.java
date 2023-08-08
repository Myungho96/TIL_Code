import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int Result = 0;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        List<int[]>[] island = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            island[i] = new ArrayList<>();
        }
        int a, b, c, maxCnt = 0;
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            a = Integer.parseInt(temp[0]);
            b = Integer.parseInt(temp[1]);
            c = Integer.parseInt(temp[2]);
            island[a].add(new int[]{b, c});
            island[b].add(new int[]{a, c});
            maxCnt = Math.max(maxCnt, c);
        }
        temp = br.readLine().split(" ");
        //이진탐색
        int start = 0;
        int end = maxCnt;
        while (start <= end) {
            int mid = (start + end) / 2;
            //가는길에서 감당할 수 있는 무게 중 가장 작은 값이 옮길 수 있는 물품 중량의 최댓값이다.
            if (dfs(mid, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), new boolean[n + 1], island)
            || dfs(mid, Integer.parseInt(temp[1]), Integer.parseInt(temp[0]), new boolean[n + 1], island)) {
                //갈수 있으므로 start 증가
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        bw.write(end + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean dfs(int mid, int start, int end, boolean[] visited, List<int[]>[] island) {
        if (start == end) {
            return true;
        }
        visited[start] = true;
        for (int[] list : island[start]) {
            if (!visited[list[0]] && list[1]>=mid) {
                if(dfs(mid, list[0], end, visited, island))
                    return true;
            }
        }
        return false;
    }
}