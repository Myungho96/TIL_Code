import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static TreeSet<Integer> ResultSet = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i, visited, arr);
            visited[i] = false;
        }
        bw.write(ResultSet.size() + "\n");
        while (!ResultSet.isEmpty()) {
            bw.write(ResultSet.pollFirst() + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void dfs(int start, int target, boolean[] visited, int[] arr) {
        if (visited[arr[start]] == false) {
            visited[arr[start]] = true;
            dfs(arr[start], target, visited, arr);
            visited[arr[start]] = false;
        }
        if (arr[start] == target) ResultSet.add(target);
    }
}

